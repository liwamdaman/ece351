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
import ece351.util.ExaminableProperties;
import ece351.v.VParser;
import ece351.v.ast.VProgram;

public class TestVParser2 extends AbstractVTest {

	@Override
	protected void test(final String name, final VProgram vp1) {
		// pretty-print the input AST
		final String pp = vp1.toString();
		//System.out.println("pretty-print the input AS:  " + pp);
		// parse the pretty-print
		final VProgram vp2 = VParser.parse(pp);
		//final String pp1  = fp2.toString();
		//System.out.println("pretty-print the input AS:  " + pp1);
		// check that they are the same
		assertTrue("unexpectedly not isomorphic: " + name, vp1.isomorphic(vp2));
		// check object contract
		ExaminableProperties.checkAllUnary(vp1);
		ExaminableProperties.checkAllUnary(vp2);
		ExaminableProperties.checkAllBinary(vp1, vp2);
		// success!
		System.out.println("accepted, as expected:  " + name);
	}

}
