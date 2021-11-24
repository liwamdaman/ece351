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

import org.parboiled.Rule;
import org.parboiled.annotations.MemoMismatches;

import ece351.common.ast.Constants;
import ece351.util.BaseParser351;

abstract class VBase extends BaseParser351 implements Constants {

	Rule Char() {
		return FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'));
	}

	Rule Digit() {
		return CharRange('0', '9');
	}

	// *************************************Keyword
	// Rules******************************************
	// You can use these rules to match keywords in the VHDL grammar rules.
	// Theses rules account
	// for VHDL's case-insensitivity and enforce whitespaces following the
	// keywords more carefully
	// than just using the rule, WhiteSpace, or adding a space at the end of
	// each keyword literal

	// Boolean Operators
	public Rule SingleKeyword(String keyword) {
		return Sequence(IgnoreCase(keyword),
				TestNot(FirstOf(Char(), Digit(), "_")));
	}

	Rule NOT() {
		return SingleKeyword(NOT);
	}

	Rule AND() {
		return SingleKeyword(AND);
	}

	Rule OR() {
		return SingleKeyword(OR);
	}

	Rule XOR() {
		return SingleKeyword(XOR);
	}

	Rule NAND() {
		return SingleKeyword(NAND);
	}

	Rule NOR() {
		return SingleKeyword(NOR);
	}

	Rule XNOR() {
		return SingleKeyword(XNOR);
	}

	// Constructs
	Rule IF() {
		return SingleKeyword("if");
	}

	Rule THEN() {
		return SingleKeyword("then");
	}

	Rule ELSE() {
		return SingleKeyword("else");
	}

	Rule ENDIF() {
		return Sequence(END(), W0(), IF());
	}

	Rule PROCESS() {
		return SingleKeyword("process");
	}

	Rule ENDPROCESS() {
		return Sequence(END(), W0(), PROCESS());
	}

	Rule ENTITY() {
		return SingleKeyword("entity");
	}

	Rule PORT() {
		return SingleKeyword("port");
	}

	Rule MAP() {
		return SingleKeyword("map");
	}

	Rule ARCHITECTURE() {
		return SingleKeyword("architecture");
	}

	Rule OF() {
		return SingleKeyword("of");
	}

	Rule IS() {
		return SingleKeyword("is");
	}

	Rule BEGIN() {
		return SingleKeyword("begin");
	}

	Rule END() {
		return SingleKeyword("end");
	}

	Rule SIGNAL() {
		return SingleKeyword("signal");
	}

	Rule BIT() {
		return SingleKeyword("bit");
	}

	Rule IN() {
		return SingleKeyword("in");
	}

	Rule OUT() {
		return SingleKeyword("out");
	}

	@MemoMismatches
	Rule Keyword() {
		return FirstOf(NOT(), AND(), OR(), XOR(), NAND(), NOR(), XNOR(), IF(),
				THEN(), ELSE(), PROCESS(), ENTITY(), PORT(), MAP(),
				ARCHITECTURE(), OF(), IS(), BEGIN(), END(), SIGNAL(), BIT(),
				IN(), OUT());
	}

}
