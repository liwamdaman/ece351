/* *********************************************************************
 * ECE351 
 * Department of Electrical and Computer Engineering 
 * University of Waterloo 
 * Term: Fall 2021 (1219)
 *
 * The base version of this file is the intellectual property of the
 * University of Waterloo. Redistribution is prohibited.
 *
 * By pushing changes to this file I affirm that I am the author of
 * all changes. I affirm that I have complied with the course
 * collaboration policy and have not plagiarized my work. 
 *
 * I understand that redistributing this file might expose me to
 * disciplinary action under UW Policy 71. I understand that Policy 71
 * allows for retroactive modification of my final grade in a course.
 * For example, if I post my solutions to these labs on GitHub after I
 * finish ECE351, and a future student plagiarizes them, then I too
 * could be found guilty of plagiarism. Consequently, my final grade
 * in ECE351 could be retroactively lowered. This might require that I
 * repeat ECE351, which in turn might delay my graduation.
 *
 * https://uwaterloo.ca/secretariat-general-counsel/policies-procedures-guidelines/policy-71
 * 
 * ********************************************************************/

package ece351.v;

import java.util.LinkedHashSet;
import java.util.Set;

import org.parboiled.common.ImmutableList;

import ece351.common.ast.AndExpr;
import ece351.common.ast.AssignmentStatement;
import ece351.common.ast.ConstantExpr;
import ece351.common.ast.EqualExpr;
import ece351.common.ast.Expr;
import ece351.common.ast.NAndExpr;
import ece351.common.ast.NOrExpr;
import ece351.common.ast.NaryAndExpr;
import ece351.common.ast.NaryOrExpr;
import ece351.common.ast.NotExpr;
import ece351.common.ast.OrExpr;
import ece351.common.ast.Statement;
import ece351.common.ast.VarExpr;
import ece351.common.ast.XNOrExpr;
import ece351.common.ast.XOrExpr;
import ece351.common.visitor.PostOrderExprVisitor;
import ece351.util.CommandLine;
import ece351.v.ast.Architecture;
import ece351.v.ast.DesignUnit;
import ece351.v.ast.IfElseStatement;
import ece351.v.ast.Process;
import ece351.v.ast.VProgram;

/**
 * Process splitter.
 */
public final class Splitter extends PostOrderExprVisitor {
	private final Set<String> usedVarsInExpr = new LinkedHashSet<String>();

	public static void main(String[] args) {
		System.out.println(split(args));
	}
	
	public static VProgram split(final String[] args) {
		return split(new CommandLine(args));
	}
	
	public static VProgram split(final CommandLine c) {
		final VProgram program = DeSugarer.desugar(c);
        return split(program);
	}
	
	public static VProgram split(final VProgram program) {
		VProgram p = Elaborator.elaborate(program);
		final Splitter s = new Splitter();
		return s.splitit(p);
	}

	private VProgram splitit(final VProgram program) {
		VProgram result = new VProgram();
		for (DesignUnit designUnit : program.designUnits) {
			ImmutableList<Statement> statements = ImmutableList.of();
			for (Statement statement : designUnit.arch.statements) {
				if (statement.getClass() == Process.class) {
					// Determine if the process needs to be split into multiple processes
					// According to a campuswire thread, a process can either only contain ifElseStatements or AssignmentStatements
					if (!((Process)statement).sequentialStatements.isEmpty() && ((Process)statement).sequentialStatements.get(0).getClass() == IfElseStatement.class) {
						for (Statement ifElseStatement : ((Process)statement).sequentialStatements) {
							ImmutableList<Statement> processes = splitIfElseStatement((IfElseStatement)ifElseStatement);
							for (Statement process : processes) {
								statements = statements.append(process);
							}
						}
					} else {
						statements = statements.append(statement);
					}
				} else {
					statements = statements.append(statement);
				}
			}
			DesignUnit newDesignUnit = new DesignUnit(new Architecture(statements, designUnit.arch.components, designUnit.arch.signals, designUnit.arch.entityName, designUnit.arch.architectureName), designUnit.entity);
			result = result.append(newDesignUnit);
		}
		return result;
	}
	
	// You do not have to use this helper method, but we found it useful
	private ImmutableList<Statement> splitIfElseStatement(final IfElseStatement ifStmt) {
		ImmutableList<Statement> processes = ImmutableList.of();
		
		if (ifStmt.ifBody.size() <= 1) {
			// Simply create new process for this ifElseStatement
			this.usedVarsInExpr.clear();
			this.traverseExpr(ifStmt.ifBody.get(0).expr);
			this.traverseExpr(ifStmt.elseBody.get(0).expr);
			this.traverseExpr(ifStmt.condition);
			ImmutableList<String> sensitivityList = ImmutableList.of();
			for (String usedVar : this.usedVarsInExpr) {
				sensitivityList = sensitivityList.append(usedVar);
			}
			processes = processes.append(new Process(ImmutableList.of(ifStmt), sensitivityList));
		} else {
			// loop over each statement in the ifBody
			for (AssignmentStatement ifBodyStatement : ifStmt.ifBody) {
				// loop over each statement in the elseBody
				for (AssignmentStatement elseBodyStatement : ifStmt.elseBody) {
					// check if outputVars are the same
					if (ifBodyStatement.outputVar.equals(elseBodyStatement.outputVar)) {
						// initialize/clear this.usedVarsInExpr
						this.usedVarsInExpr.clear();
						// call traverse a few times to build up this.usedVarsInExpr
						this.traverseExpr(ifBodyStatement.expr);
						this.traverseExpr(elseBodyStatement.expr);
						this.traverseExpr(ifStmt.condition);
						// build sensitivity list from this.usedVarsInExpr
						ImmutableList<String> sensitivityList = ImmutableList.of();
						for (String usedVar : this.usedVarsInExpr) {
							sensitivityList = sensitivityList.append(usedVar);
						}
						// build the resulting list of split statements
						IfElseStatement ifElseStatement = new IfElseStatement(ImmutableList.of(elseBodyStatement), ImmutableList.of(ifBodyStatement), ifStmt.condition);
						processes = processes.append(new Process(ImmutableList.of(ifElseStatement), sensitivityList));
					}
				}
			}
		}
		// return result
		return processes;
	}

	@Override
	public Expr visitVar(final VarExpr e) {
		this.usedVarsInExpr.add(e.identifier);
		return e;
	}

	// no-ops
	@Override public Expr visitConstant(ConstantExpr e) { return e; }
	@Override public Expr visitNot(NotExpr e) { return e; }
	@Override public Expr visitAnd(AndExpr e) { return e; }
	@Override public Expr visitOr(OrExpr e) { return e; }
	@Override public Expr visitXOr(XOrExpr e) { return e; }
	@Override public Expr visitNAnd(NAndExpr e) { return e; }
	@Override public Expr visitNOr(NOrExpr e) { return e; }
	@Override public Expr visitXNOr(XNOrExpr e) { return e; }
	@Override public Expr visitEqual(EqualExpr e) { return e; }
	@Override public Expr visitNaryAnd(NaryAndExpr e) { return e; }
	@Override public Expr visitNaryOr(NaryOrExpr e) { return e; }

}
