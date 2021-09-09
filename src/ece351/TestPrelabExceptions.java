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

package ece351;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.parboiled.errors.ErrorUtils.printParseErrors;

import java.io.File;
import java.io.FileReader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.Test;
import org.parboiled.BaseParser;
import org.parboiled.Parboiled;
import org.parboiled.Rule;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;

import ece351.util.Todo351Exception;

/**
 * These tests are for you to understand exceptions. 
 * They are expected to throw exceptions.
 * You do not have to fix them.
 * The point is to learn to how read the stack traces and to be familiar with exceptions.
 * 
 * @author drayside
 *
 */
public class TestPrelabExceptions {

	/**
	 * This test intended to error/fail. The point is to learn how to read
	 * the nested stack trace. When you see the message "this is not the 
	 * exception you are looking for" in the stack trace, you should keep
	 * looking until you do find the exception you are looking for.
	 * Understand how the stack trace relates to this code snippet.
	 */
    @Test
    public void testNestedException() {
        try {
        	final File f = new File("non-existant-file");
        	final FileReader r = new FileReader(f);
        	r.close();
        } catch (final Exception e) {
            throw new RuntimeException("this is not the exception that you are looking for", e);
        }
    }

    /**
     * Wherever you see a Todo351Exception being thrown in later labs,
     * you should replace it with working code. For now just observe
     * the existence of this exception.
     */
    @Test
    public void testTodo351Exception() {
    	throw new Todo351Exception("replace these exceptions with working code");
    }
}
