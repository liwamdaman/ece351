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

package ece351.w.svg;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ece351.util.BaseTest351;
import ece351.util.CommandLine;
import ece351.util.TestInputs351;
import ece351.w.ast.WProgram;
import ece351.w.rdescent.WRecursiveDescentParser;

/**
 * All this does is run your transformer and parse the output. This
 * is a basic sanity test, not a complete correctness test.
 */
@RunWith(Parameterized.class)
public final class TestLegalSVG extends BaseTest351 {

	private final File wave;

	public TestLegalSVG(final File wave) {
		this.wave = wave;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> waveFiles() {
		final Collection<Object[]> result = TestInputs351.waveFiles();
		if (result == null || result.isEmpty()) {
			System.err.println("Couldn't find any wave files. Where are they? No tests run.");
		}
		return result;
	}

	@Test
	public void process() throws Exception {

		final String inputSpec = wave.getAbsolutePath();
		
		final String studentOut = svgpath(inputSpec, "student.out");
		System.out.println("transforming " + inputSpec);

		// produce the student SVG
		final CommandLine c = (TransformW2SVG.USE_DOM_XML_PARSER) ? new CommandLine("-h", "-d", "-f", studentOut, inputSpec)
		                                                          : new CommandLine("-h", "-f", studentOut, inputSpec);
		final WProgram wp = WRecursiveDescentParser.parse(c.readInputSpec());
		final PrintWriter pw = c.resolveOutputSpec();
		TransformW2SVG.transform(wp, pw);
		pw.flush();

		
		// read student SVG file to see if it is structurally sensible
		System.out.println("reading      " + studentOut);
		final PinsLines studentwsvg = PinsLines.fromSVG(c.getOutputFile().toURI(), c.parseDOM);
		// didn't throw an exception will constructing PinsLines, so declare success
		System.out.println("parsed       " + studentOut);
	}

	private final static String FS = System.getProperty("file.separator");

	private static String svgpath(final String inputSpec, final String dir) {
		final int lastSlash = inputSpec.lastIndexOf(FS);
		return inputSpec.substring(0, lastSlash) + FS + dir
				+ inputSpec.substring(lastSlash).replace(".wave", ".svg");
	}

}
