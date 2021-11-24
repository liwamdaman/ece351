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

package ece351.v.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
import ece351.util.BaseTest351;
import ece351.util.CommandLine;
import ece351.util.ExaminableProperties;
import ece351.util.TestInputs351;
import ece351.v.DeSugarer;
import ece351.v.PostOrderVVisitor;
import ece351.v.VParser;
import ece351.v.ast.VProgram;


@RunWith(Parameterized.class)
public final class TestDeSugarer extends BaseTest351 {

	private final File f;

	public TestDeSugarer(final File f) {
		this.f = f;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> files() {
		return TestInputs351.vFiles();
	}

	@Test
	public void desugar() {
		final String inputSpec = f.getAbsolutePath();
		final CommandLine c = new CommandLine(inputSpec);
		System.out.println("processing " + inputSpec);
		// parse from the file to construct first AST
		final VProgram vp1 = DeSugarer.desugar(c);
		assertTrue(vp1.repOk());
		// pretty-print the first AST
		final String pp = vp1.toString();
		System.out.println("desugared program: ");
		System.out.println(pp);

		// check the desugared program does not contain any exotic operators
		final DesugarCheck dsc = new DesugarCheck();
		// this will thrown an exception if the tree is not desugared
		dsc.traverseVProgram(vp1);
		
		// find the appropriate solution file for comparison
		String solnSpec = "";
		for (final Object[] obj : TestInputs351.desugaredVFiles()) {
			if (obj[0] instanceof File) {
				final File soln = (File)obj[0];
				if (f.getName().equals(soln.getName())) {
					solnSpec = soln.getAbsolutePath();
					break;
				}
			}
		}
		
		assertTrue("no matching file found to compare the input file: " + inputSpec, solnSpec.length() > 0);
		System.out.println("checking desugared output against: " + solnSpec);
		final CommandLine sc = new CommandLine(solnSpec);
		final VProgram vp2 = VParser.parse(sc.readInputSpec());
		assertTrue(vp2.repOk());
		System.out.println("solution: ");
		System.out.println(vp2.toString());
		// check that the two ASTs are equivalent (logically the same)
		assertTrue("VPrograms differ " + inputSpec, vp1.equivalent(vp2));
		// check examinable sanity
		ExaminableProperties.checkAllUnary(vp1);
		ExaminableProperties.checkAllUnary(vp2);
		ExaminableProperties.checkAllBinary(vp1, vp2);
		// success!
		System.out.println("accepted, as expected:  " + inputSpec);
	}

	private final static class DesugarCheck extends PostOrderVVisitor {

		// a desugared tree should not contain these
		@Override public Expr visitXOr(XOrExpr e) { throw new IllegalStateException("xor not desugared"); } 
		@Override public Expr visitEqual(EqualExpr e) { throw new IllegalStateException("EqualExpr not desugared"); }
		@Override public Expr visitNAnd(NAndExpr e) { throw new IllegalStateException("nand not desugared"); }
		@Override public Expr visitNOr(NOrExpr e) { throw new IllegalStateException("nor not desugared"); }
		@Override public Expr visitXNOr(XNOrExpr e) { throw new IllegalStateException("xnor not desugared"); }
		
		// these are ok in the desugared tree
		@Override public Expr visitConstant(final ConstantExpr e) { return e; }
		@Override public Expr visitVar(final VarExpr e) { return e; }
		@Override public Expr visitNot(final NotExpr e) { return e; }
		@Override public Expr visitAnd(final AndExpr e) { return e; }
		@Override public Expr visitOr(final OrExpr e) { return e; }
		@Override public Expr visitNaryAnd(final NaryAndExpr e) { return e; }
		@Override public Expr visitNaryOr(final NaryOrExpr e) { return e; }
	}
}
