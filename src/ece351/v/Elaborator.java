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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
import ece351.v.ast.Component;
import ece351.v.ast.DesignUnit;
import ece351.v.ast.IfElseStatement;
import ece351.v.ast.Process;
import ece351.v.ast.VProgram;

/**
 * Inlines logic in components to architecture body.
 */
public final class Elaborator extends PostOrderExprVisitor {

	private final Map<String, String> current_map = new LinkedHashMap<String, String>();
	
	public static void main(String[] args) {
		System.out.println(elaborate(args));
	}
	
	public static VProgram elaborate(final String[] args) {
		return elaborate(new CommandLine(args));
	}
	
	public static VProgram elaborate(final CommandLine c) {
        final VProgram program = DeSugarer.desugar(c);
        return elaborate(program);
	}
	
	public static VProgram elaborate(final VProgram program) {
		final Elaborator e = new Elaborator();
		return e.elaborateit(program);
	}

	private VProgram elaborateit(final VProgram root) {

		// our ASTs are immutable. so we cannot mutate root.
		// we need to construct a new AST that will be the return value.
		// it will be like the input (root), but different.
		VProgram result = new VProgram();
		int compCount = 1;

		// iterate over all of the designUnits in root.
		for (DesignUnit designUnit : root.designUnits) {
			// for each one, construct a new architecture.
			Architecture arch = designUnit.arch.varyComponents(ImmutableList.<Component>of());
			// this gives us a copy of the architecture with an empty list of components.
			
			// now we can build up this Architecture with new components.
			// In the elaborator, an architectures list of signals, and set of statements may change (grow)
			for (Component component : designUnit.arch.components) {
				// Assuming related design unit / entity already exists, find it
				DesignUnit relatedDesignUnit = result.designUnits.stream()
						.filter(du -> du.entity.identifier.equals(component.entityName))
						.collect(Collectors.toList())
						.get(0);
				
				//populate dictionary/map
				int signalIndex = 0;
				//add input signals, map to ports
				for (String input : relatedDesignUnit.entity.input) {
					current_map.put(input, component.signalList.get(signalIndex));
					signalIndex++;
				}
				//add output signals, map to ports
				for (String output : relatedDesignUnit.entity.output) {
					current_map.put(output, component.signalList.get(signalIndex));
					signalIndex++;
				}				
				//add local signals, add to signal list of current designUnit
				for (String signal : relatedDesignUnit.arch.signals) {
					String prefixedSignal = "comp" + compCount + "_" + signal;
					current_map.put(signal, prefixedSignal);
					arch = arch.appendSignal(prefixedSignal);
				}
				
				//loop through the statements in the architecture body
				for (Statement statement : relatedDesignUnit.arch.statements) {
					Statement substitutedStatement = statement;
					
					// make the appropriate variable substitutions for signal assignment statements
					// i.e., call changeStatementVars
					if (substitutedStatement.getClass() == AssignmentStatement.class) {
						substitutedStatement = changeStatementVars((AssignmentStatement)substitutedStatement);
					}
					
					// make the appropriate variable substitutions for processes (sensitivity list, if/else body statements)
					// i.e., call expandProcessComponent
					else if (substitutedStatement.getClass() == Process.class) {
						substitutedStatement = expandProcessComponent((Process)substitutedStatement);
					}
					
					arch = arch.appendStatement(substitutedStatement);
				}
				
				// increase compCount, clear current_map
				compCount++;
				current_map.clear();
			}
			
			// append this new architecture to result
			result = result.append(new DesignUnit(arch, designUnit.entity));
		}
		
		assert result.repOk();
		return result;
	}
	
	// you do not have to use these helper methods; we found them useful though
	private Process expandProcessComponent(final Process process) {
		ImmutableList<String> sensitivityList = ImmutableList.of();
		for (String input : process.sensitivityList) {
			sensitivityList = sensitivityList.append(current_map.get(input));
		}
		
		ImmutableList<Statement> sequentialStatements = ImmutableList.of();
		for (Statement statement : process.sequentialStatements) {
			if (statement.getClass() == AssignmentStatement.class) {
				sequentialStatements = sequentialStatements.append(changeStatementVars((AssignmentStatement)statement));
			} else if (statement.getClass() == IfElseStatement.class) {
				sequentialStatements = sequentialStatements.append(changeIfVars((IfElseStatement)statement));
			} else if (statement.getClass() == Process.class) {
				sequentialStatements = sequentialStatements.append(expandProcessComponent((Process)statement));
			}
			// Should be no other options
		}
		return new Process(sequentialStatements, sensitivityList);
	}
	
	// you do not have to use these helper methods; we found them useful though
	private  IfElseStatement changeIfVars(final IfElseStatement s) {
		Expr condition = traverseExpr(s.condition);
		ImmutableList<AssignmentStatement> ifBody = ImmutableList.of();
		for (AssignmentStatement stmt : s.ifBody) {
			ifBody = ifBody.append(changeStatementVars(stmt));
		}
		ImmutableList<AssignmentStatement> elseBody = ImmutableList.of();
		for (AssignmentStatement stmt : s.elseBody) {
			elseBody = elseBody.append(changeStatementVars(stmt));
		}
		return new IfElseStatement(elseBody, ifBody, condition);
	}

	// you do not have to use these helper methods; we found them useful though
	private AssignmentStatement changeStatementVars(final AssignmentStatement s){
		return new AssignmentStatement(current_map.get(s.outputVar.identifier), traverseExpr(s.expr));
	}
	
	
	@Override
	public Expr visitVar(VarExpr e) {
		// replace/substitute the variable found in the map
		return new VarExpr(current_map.get(e.identifier));
	}
	
	// do not rewrite these parts of the AST
	@Override public Expr visitConstant(ConstantExpr e) { return e; }
	@Override public Expr visitNot(NotExpr e) { return e; }
	@Override public Expr visitAnd(AndExpr e) { return e; }
	@Override public Expr visitOr(OrExpr e) { return e; }
	@Override public Expr visitXOr(XOrExpr e) { return e; }
	@Override public Expr visitEqual(EqualExpr e) { return e; }
	@Override public Expr visitNAnd(NAndExpr e) { return e; }
	@Override public Expr visitNOr(NOrExpr e) { return e; }
	@Override public Expr visitXNOr(XNOrExpr e) { return e; }
	@Override public Expr visitNaryAnd(NaryAndExpr e) { return e; }
	@Override public Expr visitNaryOr(NaryOrExpr e) { return e; }
}
