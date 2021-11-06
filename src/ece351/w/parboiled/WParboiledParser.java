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

package ece351.w.parboiled;
import java.io.File;

import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.common.FileUtils;
import org.parboiled.common.ImmutableList;

import ece351.util.BaseParser351;
import ece351.w.ast.WProgram;
import ece351.w.ast.Waveform;
import java.lang.invoke.MethodHandles;

@BuildParseTree
//Parboiled requires that this class not be final
public /*final*/ class WParboiledParser extends BaseParser351 {

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
	/**
	 * Run this parser, exit with error code 1 for malformed input.
	 * Called by wave/Makefile.
	 * @param args
	 */
	public static void main(final String[] args) {
    	process(WParboiledParser.class, FileUtils.readAllText(args[0]));
    }

	/**
	 * Construct an AST for a W program. Use this for debugging.
	 */
	public static WProgram parse(final String inputText) {
		return (WProgram) process(WParboiledParser.class, inputText).resultValue;
	}

	/**
	 * By convention we name the top production in the grammar "Program".
	 */
	@Override
    public Rule Program() {
		return Sequence(
				push(new WProgram()),
				OneOrMore(Sequence(Waveform(), W0())),
				EOI,
				checkType(peek(), WProgram.class)
				//debugmsg(peek())
				);
    }

	/**
	 * Each line of the input W file represents a "pin" in the circuit.
	 */
    public Rule Waveform() {
    	return Sequence(
    			push(new Waveform()),
    			Sequence(Name(), W0(), Ch(':'), W0(), BitString(), W0(), Ch(';')),
    			checkType(peek(), Waveform.class),
    			//debugmsg(peek()),
    			swap(),
    			checkType(peek(), WProgram.class),
    			push(((WProgram)pop()).append((Waveform)pop()))
    			);
    }

    /**
     * The first token on each line is the name of the pin that line represents.
     */
    public Rule Name() {
    	return Sequence(
    			OneOrMore(Letter()),
    			//debugmsg(match()),
    			checkType(peek(), Waveform.class),
    			push(((Waveform)pop()).rename(match()))
    			);
    }
    
    /**
     * A Name is composed of a sequence of Letters. 
     * Recall that PEGs incorporate lexing into the parser.
     */
    public Rule Letter() {
    	return Sequence(
    			FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'), CharRange('0', '9'), Ch('_')),
    			checkType(match(), String.class)
    			);
    }

    /**
     * A BitString is the sequence of values for a pin.
     */
    public Rule BitString() {
    	return OneOrMore(Sequence(Bit(), W0()));
    }
    
    /**
     * A BitString is composed of a sequence of Bits. 
     * Recall that PEGs incorporate lexing into the parser.
     */
    public Rule Bit() {
    	return Sequence(
    			AnyOf("01"),
    			checkType(match(), String.class),
    			checkType(peek(), Waveform.class),
    			push(((Waveform)pop()).append(match()))
    			);
    }

}
