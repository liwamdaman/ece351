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

package ece351.f.parboiled;

import java.lang.invoke.MethodHandles;

import org.parboiled.Rule;

import ece351.common.ast.Constants;
import ece351.util.CommandLine;

//Parboiled requires that this class not be final
public /*final*/ class FParboiledRecognizer extends FBase implements Constants {

	public static Class<?> findLoadedClass(String className) throws IllegalAccessException {
        try {
            return MethodHandles.lookup().findClass(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> loadClass(byte[] code) throws IllegalAccessException {
        return MethodHandles.lookup().defineClass(code);
    }
	
	public static void main(final String... args) {
		final CommandLine c = new CommandLine(args);
    	process(FParboiledRecognizer.class, c.readInputSpec());
    }

	@Override
	public Rule Program() {
		// For the grammar production Id, ensure that the Id does not match any of the keywords specified
		// in the rule, 'Keyword'
		return Sequence(
				OneOrMore(Sequence(Formula(), W0())),
				EOI
				);
	}
	
	public Rule Formula() {
		return Sequence(Var(), W0(), Ch('<'), Ch('='), W0(), Expr(), W0(), Ch(';'));
	}
	
	public Rule Expr() {
		return Sequence(
				Term(),
				ZeroOrMore(Sequence(W0(), OR(), W0(), Term(), W0()))
				);
	}
	
	public Rule Term() {
		return Sequence(
				Factor(),
				ZeroOrMore(Sequence(W0(), AND(), W0(), Factor(), W0()))
				);
	}
	
	public Rule Factor() {
		return FirstOf(Sequence(NOT(), W0(), Factor()), Sequence(Ch('('), W0(), Expr(), W0(), Ch(')')), Var(), Constant());
	}
	
	public Rule Constant() {
		return Sequence(Ch('\''), FirstOf("0 ", "1 "), Ch('\''));
	}
	
	public Rule Var() {
		return Sequence(
				TestNot(Keyword()),
				OneOrMore(Char())
				);
	}

}
