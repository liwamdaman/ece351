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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import ece351.f.analysis.InlineIntermediateVariables;
import ece351.f.ast.FProgram;
import ece351.f.simgen.TestSimulatorGenerator;
import ece351.f.techmapper.TechnologyMapper;
import ece351.util.BaseTest351;
import ece351.util.CommandLine;
import ece351.util.Utils351;
import ece351.v.DeSugarer;
import ece351.v.Elaborator;
import ece351.v.Splitter;
import ece351.v.Synthesizer;
import ece351.v.VParser;
import ece351.v.ast.VProgram;
import ece351.w.ast.WProgram;
import ece351.w.parboiled.WParboiledParser;
import ece351.w.svg.TransformW2SVG;

public class TestEnd2End extends BaseTest351 {

	final static File studentOut = newFile("tests/v/student.out/end2end");
	final static File staffOut = newFile("tests/v/staff.out/end2end/");

	private static File newFile(final String s) {
		return new File(s.replace("/", TestSimulatorGenerator.sep));
	}
	
	@Test 
	public void testFullAdder() throws IOException {
		doit("full_adder");
	}
	
	@Test
	public void testRippleCarryAdder() throws IOException {
		final String fbrca = "four_bit_ripple_carry_adder";
		doit(fbrca);
	}
	
	private void doit(final String fbrca) throws IOException {
		final File vhd = Utils351.files("tests/v", fbrca + ".vhd")[0];
		
		// parse and transform VHDL
		VProgram v = VParser.parse(new CommandLine(vhd.getAbsolutePath()).readInputSpec()); assert v.repOk();
		v = DeSugarer.desugar(v); assert v.repOk();
		v = Elaborator.elaborate(v); assert v.repOk();
		v = Splitter.split(v); assert v.repOk();
		// synthesize VHDL to F
		FProgram f = Synthesizer.synthesize(v); assert f.repOk();
		f = f.simplify(); assert f.repOk();
		f = InlineIntermediateVariables.inline(f); assert f.repOk();
		
		// render the circuit
		studentOut.mkdirs();
		final File dot = new File(studentOut, fbrca + ".dot");
		TechnologyMapper.render(f, new PrintWriter(new FileWriter(dot)));
		final File outF = new File(studentOut, fbrca + ".f");
		final PrintWriter outFpw = new PrintWriter(new FileWriter(outF));
		outFpw.print(f.toString());
		outFpw.close();
		
		final String outputWaveName_ = newPath(studentOut, fbrca + "_out.wave");
		
		// generate and test the simulator
		final TestSimulatorGenerator tsg = new TestSimulatorGenerator(outF){
			@Override
			protected void computeFileNames(final String inputSpec, final FProgram fp) {
				waveName = newPath(staffOut, fbrca + "_in.wave");
				outputWaveName = outputWaveName_;
				staffWavePath = newPath(staffOut, fbrca + "_out.wave");
				sourcePath = newPath(studentOut, "Simulator_" + fbrca + ".java");;
			}
		};
		tsg.simgen();
		
		// draw the output wave file in SVG
		final String svgName = newPath(studentOut, fbrca + ".svg");
		final CommandLine csvg = new CommandLine("-p", "-f", svgName, outputWaveName_);
		final WProgram w_out = WParboiledParser.parse(csvg.readInputSpec());
		TransformW2SVG.transform(w_out, csvg.resolveOutputSpec());

	}

	private static String newPath(final File dir, final String fname) {
		return (new File(dir, fname)).getAbsolutePath();
	}
}
