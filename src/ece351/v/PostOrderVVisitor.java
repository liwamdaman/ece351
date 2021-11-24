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
import ece351.v.ast.DesignUnit;
import ece351.v.ast.IfElseStatement;
import ece351.v.ast.Process;
import ece351.v.ast.VProgram;

public abstract class PostOrderVVisitor extends PostOrderExprVisitor {

	/**
	 * Visit/rewrite all of the exprs in this VProgram.
	 * @param v input VProgram
	 * @return a new VProgram with changes applied
	 */
	public VProgram traverseVProgram(final VProgram v) {
		assert v.repOk();
		VProgram result = new VProgram();
		
		for (final DesignUnit d : v.designUnits) {
			ImmutableList<Statement> architectureStatements = ImmutableList.of();
			
			for (final Statement i : d.arch.statements) {				
				if (i instanceof Process) {
					ImmutableList<Statement> sequentialStatements = ImmutableList.of();
					
					for (final Statement proc_stmt : ((Process) i).sequentialStatements) {
						if (proc_stmt instanceof IfElseStatement) {
							final IfElseStatement ifElseStmt = (IfElseStatement) proc_stmt;
							final Expr condition = traverseExpr(ifElseStmt.condition);
							ImmutableList<AssignmentStatement> ifBody = ImmutableList.of();
							ImmutableList<AssignmentStatement> elseBody = ImmutableList.of();
							
							for(final AssignmentStatement stmt : ifElseStmt.ifBody) {
								ifBody = ifBody.append(new AssignmentStatement(stmt.outputVar, traverseExpr(stmt.expr)));
							}
							for(final AssignmentStatement stmt : ifElseStmt.elseBody) {
								elseBody = elseBody.append(new AssignmentStatement(stmt.outputVar, traverseExpr(stmt.expr)));
							}				
							sequentialStatements = sequentialStatements.append(new IfElseStatement(elseBody, ifBody, condition));
						} else if (proc_stmt instanceof AssignmentStatement) {
							final AssignmentStatement stmt = (AssignmentStatement) proc_stmt;
							sequentialStatements = sequentialStatements.append(new AssignmentStatement(stmt.outputVar, traverseExpr(stmt.expr)));
						}
					}
					
					architectureStatements = architectureStatements.append(new Process(sequentialStatements, ((Process)i).sensitivityList));
					
				} else if (i instanceof AssignmentStatement) {
					final AssignmentStatement stmt = (AssignmentStatement) i;
					architectureStatements = architectureStatements.append(new AssignmentStatement(stmt.outputVar, traverseExpr(stmt.expr)));

				} else {
					throw new IllegalStateException("unknown statement type in VExprVisitor: " + i.getClass());
				}
			}
			
			result = result.append(d.setArchitecture(d.arch.varyStatements(architectureStatements)));
		}
		
		assert result.repOk();
		return result;
	}
	
}
