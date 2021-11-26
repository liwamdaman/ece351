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

import ece351.common.ast.AndExpr;
import ece351.common.ast.ConstantExpr;
import ece351.common.ast.EqualExpr;
import ece351.common.ast.Expr;
import ece351.common.ast.NAndExpr;
import ece351.common.ast.NOrExpr;
import ece351.common.ast.NaryAndExpr;
import ece351.common.ast.NaryOrExpr;
import ece351.common.ast.NotExpr;
import ece351.common.ast.OrExpr;
import ece351.common.ast.VarExpr;
import ece351.common.ast.XNOrExpr;
import ece351.common.ast.XOrExpr;
import ece351.common.visitor.PostOrderExprVisitor;
import ece351.util.CommandLine;
import ece351.v.ast.VProgram;

public final class DeSugarer extends PostOrderVVisitor {

	public DeSugarer() { super(); }
	
	public static void main(final String[] args) {
		System.out.println(desugar(args));
    }
	
	public static VProgram desugar(final String[] args) {
		return desugar(new CommandLine(args));
	}
	
	public static VProgram desugar(final CommandLine c) {
        final VProgram program = VParser.parse(c.readInputSpec());
        return desugar(program);
	}
	
	public static VProgram desugar(final VProgram program) {
		final DeSugarer d = new DeSugarer();
		return d.traverseVProgram(program);
	}

	@Override
	public Expr visitXOr(final XOrExpr e) {
		// TODO: rewrite XOR and return new expression
// TODO: short code snippet
throw new ece351.util.Todo351Exception();
	}
	
	@Override
	public Expr visitNAnd(final NAndExpr e) {
		// TODO: rewrite NAND and return new expression
// TODO: short code snippet
throw new ece351.util.Todo351Exception();
	}
	
	@Override
	public Expr visitNOr(final NOrExpr e) {
		// TODO: rewrite NOR and return new expression
// TODO: short code snippet
throw new ece351.util.Todo351Exception();
	}
	
	@Override
	public Expr visitXNOr(final XNOrExpr e) {
		// TODO: rewrite XNOR and return new expression
//		return new NotExpr(new OrExpr(new AndExpr(e.left, new NotExpr(e.right)),
//				  			new AndExpr(new NotExpr(e.left), e.right)));
// TODO: short code snippet
throw new ece351.util.Todo351Exception();
	}

	@Override
	public Expr visitEqual(final EqualExpr e) {
		//TODO: equals operator has the same truth table as xnor
// TODO: short code snippet
throw new ece351.util.Todo351Exception();
	}

	// these stay the same, no desugaring
	@Override public Expr visitConstant(final ConstantExpr e) { return e; }
	@Override public Expr visitVar(final VarExpr e) { return e; }
	@Override public Expr visitNot(final NotExpr e) { return e; }
	@Override public Expr visitAnd(final AndExpr e) { return e; }
	@Override public Expr visitOr(final OrExpr e) { return e; }
	@Override public Expr visitNaryAnd(final NaryAndExpr e) { return e; }
	@Override public Expr visitNaryOr(final NaryOrExpr e) { return e; }
}
