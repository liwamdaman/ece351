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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ece351.util.BaseTest351;
import ece351.v.VParser;
import ece351.v.ast.VProgram;

public class TestArchitectureEquivalence extends BaseTest351 {

	@Test
	public void test1() {
		final VProgram v1 = VParser.parse(scholida_nxor_1_split1);
		final VProgram v2 = VParser.parse(scholida_nxor_1_split2);
		final VProgram v3 = VParser.parse(scholida_nxor_1_bogus1);
		final VProgram v4 = VParser.parse(scholida_nxor_1_bogus2);
		
		assertTrue(v1.equivalent(v2));
		assertFalse(v1.equivalent(v3));
		assertFalse(v1.equivalent(v4));
	}

	@Test
	public void test2() {
		final VProgram v1 = VParser.parse(dlyu_test_case_advanced_split1);
		final VProgram v2 = VParser.parse(dlyu_test_case_advanced_split2);
		
		assertTrue(v1.equivalent(v2));
	}

	final static String dlyu_test_case_advanced_split1 = "entity DanWuzHere is port(\n" + 
			"	J1, J0, I0, I1, I2, I3, S, T  : in bit;\n" + 
			"	O0, O1, O2, O3, O4, O5, O6, O7, O8  : out bit\n" + 
			");\n" + 
			"end DanWuzHere;\n" + 
			"architecture behv1 of DanWuzHere is\n" + 
			"\n" + 
			"begin\n" + 
			"	process ( S, T, I0, I1  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then\n" + 
			"				O0 <= I0;\n" + 
			"			else\n" + 
			"				O0 <= I1;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, T, I1, I2  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then\n" + 
			"				O1 <= I1;\n" + 
			"			else\n" + 
			"				O1 <= I2;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, T, I0, I3, I1  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then\n" + 
			"				O2 <= ( I0 and I3 );\n" + 
			"			else\n" + 
			"				O2 <= ( I0 or I1 );\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( J1, J0  ) \n" + 
			"		begin\n" + 
			"			O7 <= ( ( J0 and ( not ( J1 ) ) ) or ( ( not ( J0 ) ) and J1 ) );\n" + 
			"			O8 <= ( not ( ( J1 and J0 ) ) );\n" + 
			"		end process;\n" + 
			"	process ( S, I0, I1  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O3 <= I0;\n" + 
			"			else\n" + 
			"				O3 <= I1;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, I1, I2  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O4 <= I1;\n" + 
			"			else\n" + 
			"				O4 <= I2;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, I2, I3, T  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O5 <= ( I2 and I3 );\n" + 
			"			else\n" + 
			"				O5 <= ( S or T );\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O6 <= S;\n" + 
			"			else\n" + 
			"				O6 <= '0';\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"end behv1;\n" + 
			"\n" + 
			"";

	final static String dlyu_test_case_advanced_split2 = "entity DanWuzHere is port(\n" + 
			"	J1, J0, I0, I1, I2, I3, S, T  : in bit;\n" + 
			"	O0, O1, O2, O3, O4, O5, O6, O7, O8  : out bit\n" + 
			");\n" + 
			"end DanWuzHere;\n" + 
			"architecture behv1 of DanWuzHere is\n" + 
			"\n" + 
			"begin\n" + 
			"	process ( S, T, I0, I1  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then\n" + 
			"				O0 <= I0;\n" + 
			"			else\n" + 
			"				O0 <= I1;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, T, I1, I2  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then\n" + 
			"				O1 <= I1;\n" + 
			"			else\n" + 
			"				O1 <= I2;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, T, I0, I3, I1  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then\n" + 
			"				O2 <= ( I0 and I3 );\n" + 
			"			else\n" + 
			"				O2 <= ( I0 or I1 );\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( J1, J0  ) \n" + 
			"		begin\n" + 
			"			O7 <= ( ( J0 and ( not ( J1 ) ) ) or ( ( not ( J0 ) ) and J1 ) );\n" + 
			"		end process;\n" + 
			"	process ( J1, J0  ) \n" + 
			"		begin\n" + 
			"			O8 <= ( not ( ( J1 and J0 ) ) );\n" + 
			"		end process;\n" + 
			"	process ( S, I0, I1  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O3 <= I0;\n" + 
			"			else\n" + 
			"				O3 <= I1;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, I1, I2  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O4 <= I1;\n" + 
			"			else\n" + 
			"				O4 <= I2;\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S, I2, I3, T  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O5 <= ( I2 and I3 );\n" + 
			"			else\n" + 
			"				O5 <= ( S or T );\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"	process ( S  ) \n" + 
			"		begin\n" + 
			"			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then\n" + 
			"				O6 <= S;\n" + 
			"			else\n" + 
			"				O6 <= '0';\n" + 
			"			end if;\n" + 
			"		end process;\n" + 
			"end behv1;\n" + 
			"\n" + 
			"";
	
	final static String scholida_nxor_1_split1 = "entity XNOR_test is port(\n" + 
			"    a, b, x, y: in bit;\n" + 
			"    F, Z: out bit\n" + 
			");\n" + 
			"end XNOR_test;  \n" + 
			"\n" + 
			"architecture XNOR_test_arch of XNOR_test is\n" + 
			"begin\n" + 
			"    process(a, b, x, y)\n" + 
			"    begin\n" + 
			"        F <= x xnor y;\n" + 
			"        Z <= (x and y) xnor (a xnor b);\n" + 
			"    end process;\n" + 
			"end XNOR_test_arch;\n" + 
			"";
	
	final static String scholida_nxor_1_split2 = "entity XNOR_test is port(\n" + 
			"    a, b, x, y: in bit;\n" + 
			"    F, Z: out bit\n" + 
			");\n" + 
			"end XNOR_test;  \n" + 
			"\n" + 
			"architecture XNOR_test_arch of XNOR_test is\n" + 
			"begin\n" + 
			"    process(x, y)\n" + 
			"    begin\n" + 
			"        F <= x xnor y;\n" + 
			"    end process;\n" + 
			"    process(a, b, x, y)\n" + 
			"    begin\n" + 
			"        Z <= (x and y) xnor (a xnor b);\n" + 
			"    end process;\n" + 
			"end XNOR_test_arch;\n" + 
			"";

	// differs in sensitivity list
	final static String scholida_nxor_1_bogus1 = "entity XNOR_test is port(\n" + 
			"    a, b, x, y: in bit;\n" + 
			"    F, Z: out bit\n" + 
			");\n" + 
			"end XNOR_test;  \n" + 
			"\n" + 
			"architecture XNOR_test_arch of XNOR_test is\n" + 
			"begin\n" + 
			"    process(x, y, c)\n" + 
			"    begin\n" + 
			"        F <= x xnor y;\n" + 
			"    end process;\n" + 
			"    process(a, b, x, y)\n" + 
			"    begin\n" + 
			"        Z <= (x and y) xnor (a xnor b);\n" + 
			"    end process;\n" + 
			"end XNOR_test_arch;\n" + 
			"";

	// differs in formula
	final static String scholida_nxor_1_bogus2 = "entity XNOR_test is port(\n" + 
			"    a, b, x, y: in bit;\n" + 
			"    F, Z: out bit\n" + 
			");\n" + 
			"end XNOR_test;  \n" + 
			"\n" + 
			"architecture XNOR_test_arch of XNOR_test is\n" + 
			"begin\n" + 
			"    process(a, x, y)\n" + 
			"    begin\n" + 
			"        F <= x xnor y or a;\n" + 
			"    end process;\n" + 
			"    process(a, b, x, y)\n" + 
			"    begin\n" + 
			"        Z <= (x and y) xnor (a xnor b);\n" + 
			"    end process;\n" + 
			"end XNOR_test_arch;\n" + 
			"";
}
