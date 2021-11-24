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

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.parboiled.common.ImmutableList;

import ece351.common.ast.AndExpr;
import ece351.common.ast.AssignmentStatement;
import ece351.common.ast.ConstantExpr;
import ece351.common.ast.EqualExpr;
import ece351.common.ast.Expr;
import ece351.common.ast.NAndExpr;
import ece351.common.ast.NOrExpr;
import ece351.common.ast.NotExpr;
import ece351.common.ast.OrExpr;
import ece351.common.ast.Statement;
import ece351.common.ast.VarExpr;
import ece351.common.ast.XNOrExpr;
import ece351.common.ast.XOrExpr;
import ece351.util.BaseTest351;
import ece351.v.ast.Architecture;
import ece351.v.ast.Component;
import ece351.v.ast.DesignUnit;
import ece351.v.ast.Entity;
import ece351.v.ast.IfElseStatement;
import ece351.v.ast.Process;
import ece351.v.ast.VProgram;

public abstract class AbstractVTest extends BaseTest351 {
	protected abstract void test(final String name, final VProgram fp1);

	public final static Map<String, VProgram> VPROGRAMS;
	static {
		final Map<String, VProgram> m = new TreeMap<String, VProgram>();
		m.put("dlyu_test_case", make_dlyu_test_case());
		m.put("dlyu_test_case_advanced", make_dlyu_test_case_advanced());
		m.put("four_bit_ripple_carry_adder", make_four_bit_ripple_carry_adder());
		m.put("full_adder", make_full_adder());
		m.put("half_adder", make_half_adder());
		m.put("mux", make_mux());
		m.put("mux2", make_mux2());
		m.put("nor_1", make_nor_1());
		m.put("nor_2", make_nor_2());
		m.put("or_1", make_or_1());
		m.put("or_1_eight_port", make_or_1_eight_port());
		m.put("or_2", make_or_2());
		m.put("or_2_eight_port", make_or_2_eight_port());
		m.put("or_nor", make_or_nor());
		m.put("sbkedida_mux", make_sbkedida_mux());
		m.put("scholida_nxor_1", make_scholida_nxor_1());
		VPROGRAMS = Collections.unmodifiableMap(m);
	}

	@Test
	public void dlyu_test_case() {
		test("dlyu_test_case", VPROGRAMS.get("dlyu_test_case"));
	}

	protected static VProgram make_dlyu_test_case() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("I5");
		sensitivityList1 = sensitivityList1.append("I4");
		sensitivityList1 = sensitivityList1.append("I3");
		sensitivityList1 = sensitivityList1.append("I2");
		sensitivityList1 = sensitivityList1.append("I1");
		sensitivityList1 = sensitivityList1.append("I0");
		sensitivityList1 = sensitivityList1.append("S");
		sensitivityList1 = sensitivityList1.append("T");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new AndExpr(new EqualExpr(new VarExpr("S"),
				ConstantExpr.FalseExpr), new EqualExpr(new VarExpr("T"),
				ConstantExpr.TrueExpr));
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("O0",
				new VarExpr("I0")));
		ifBody1 = ifBody1.append(new AssignmentStatement("O1",
				new VarExpr("I2")));
		ifBody1 = ifBody1.append(new AssignmentStatement("O2",
				new VarExpr("I4")));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("O0", new VarExpr(
				"I1")));
		elseBody1 = elseBody1.append(new AssignmentStatement("O2", new VarExpr(
				"I5")));
		elseBody1 = elseBody1.append(new AssignmentStatement("O1", new VarExpr(
				"I3")));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<String> sensitivityList2 = ImmutableList.of();
		sensitivityList2 = sensitivityList2.append("I11");
		sensitivityList2 = sensitivityList2.append("I10");
		sensitivityList2 = sensitivityList2.append("I9");
		sensitivityList2 = sensitivityList2.append("I8");
		sensitivityList2 = sensitivityList2.append("I7");
		sensitivityList2 = sensitivityList2.append("I6");
		sensitivityList2 = sensitivityList2.append("S");
		sensitivityList2 = sensitivityList2.append("T");
		ImmutableList<Statement> pStatements2 = ImmutableList.of();
		Expr ifCondition2 = new AndExpr(new EqualExpr(new VarExpr("S"),
				ConstantExpr.TrueExpr), new EqualExpr(new VarExpr("T"),
				ConstantExpr.TrueExpr));
		ImmutableList<AssignmentStatement> ifBody2 = ImmutableList.of();
		ifBody2 = ifBody2.append(new AssignmentStatement("O3",
				new VarExpr("I6")));
		ifBody2 = ifBody2.append(new AssignmentStatement("O4",
				new VarExpr("I8")));
		ifBody2 = ifBody2.append(new AssignmentStatement("O5", new VarExpr(
				"I10")));
		ImmutableList<AssignmentStatement> elseBody2 = ImmutableList.of();
		elseBody2 = elseBody2.append(new AssignmentStatement("O3", new VarExpr(
				"I7")));
		elseBody2 = elseBody2.append(new AssignmentStatement("O4", new VarExpr(
				"I9")));
		elseBody2 = elseBody2.append(new AssignmentStatement("O5", new VarExpr(
				"I11")));
		pStatements2 = pStatements2.append(new IfElseStatement(elseBody2,
				ifBody2, ifCondition2));
		statements1 = statements1.append(new Process(pStatements2,
				sensitivityList2));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("O0");
		outputs1 = outputs1.append("O1");
		outputs1 = outputs1.append("O2");
		outputs1 = outputs1.append("O3");
		outputs1 = outputs1.append("O4");
		outputs1 = outputs1.append("O5");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("I11");
		inputs1 = inputs1.append("I10");
		inputs1 = inputs1.append("I9");
		inputs1 = inputs1.append("I8");
		inputs1 = inputs1.append("I7");
		inputs1 = inputs1.append("I6");
		inputs1 = inputs1.append("I5");
		inputs1 = inputs1.append("I4");
		inputs1 = inputs1.append("I3");
		inputs1 = inputs1.append("I2");
		inputs1 = inputs1.append("I1");
		inputs1 = inputs1.append("I0");
		inputs1 = inputs1.append("S");
		inputs1 = inputs1.append("T");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "DanWuzHere", "behv1"), new Entity(
				outputs1, inputs1, "DanWuzHere")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void dlyu_test_case_advanced() {
		test("dlyu_test_case_advanced",
				VPROGRAMS.get("dlyu_test_case_advanced"));
	}

	protected static VProgram make_dlyu_test_case_advanced() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("I3");
		sensitivityList1 = sensitivityList1.append("I2");
		sensitivityList1 = sensitivityList1.append("I1");
		sensitivityList1 = sensitivityList1.append("I0");
		sensitivityList1 = sensitivityList1.append("S");
		sensitivityList1 = sensitivityList1.append("T");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new EqualExpr(new VarExpr("S"), new VarExpr("T"));
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("O0",
				new VarExpr("I0")));
		ifBody1 = ifBody1.append(new AssignmentStatement("O1",
				new VarExpr("I1")));
		ifBody1 = ifBody1.append(new AssignmentStatement("O2", new AndExpr(
				new VarExpr("I0"), new VarExpr("I3"))));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("O0", new VarExpr(
				"I1")));
		elseBody1 = elseBody1.append(new AssignmentStatement("O2", new OrExpr(
				new VarExpr("I0"), new VarExpr("I1"))));
		elseBody1 = elseBody1.append(new AssignmentStatement("O1", new VarExpr(
				"I2")));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<String> sensitivityList2 = ImmutableList.of();
		sensitivityList2 = sensitivityList2.append("J1");
		sensitivityList2 = sensitivityList2.append("J0");
		ImmutableList<Statement> pStatements2 = ImmutableList.of();
		pStatements2 = pStatements2.append(new AssignmentStatement("O7",
				new XOrExpr(new VarExpr("J0"), new VarExpr("J1"))));
		pStatements2 = pStatements2.append(new AssignmentStatement("O8",
				new NAndExpr(new VarExpr("J1"), new VarExpr("J0"))));
		statements1 = statements1.append(new Process(pStatements2,
				sensitivityList2));

		ImmutableList<String> sensitivityList3 = ImmutableList.of();
		sensitivityList3 = sensitivityList3.append("I3");
		sensitivityList3 = sensitivityList3.append("I2");
		sensitivityList3 = sensitivityList3.append("I1");
		sensitivityList3 = sensitivityList3.append("I0");
		sensitivityList3 = sensitivityList3.append("S");
		sensitivityList3 = sensitivityList3.append("T");
		ImmutableList<Statement> pStatements3 = ImmutableList.of();
		Expr ifCondition4 = new EqualExpr(new VarExpr("S"),
				ConstantExpr.TrueExpr);
		ImmutableList<AssignmentStatement> ifBody4 = ImmutableList.of();
		ifBody4 = ifBody4.append(new AssignmentStatement("O3",
				new VarExpr("I0")));
		ifBody4 = ifBody4.append(new AssignmentStatement("O4",
				new VarExpr("I1")));
		ifBody4 = ifBody4.append(new AssignmentStatement("O5", new AndExpr(
				new VarExpr("I2"), new VarExpr("I3"))));
		ifBody4 = ifBody4
				.append(new AssignmentStatement("O6", new VarExpr("S")));
		ImmutableList<AssignmentStatement> elseBody4 = ImmutableList.of();
		elseBody4 = elseBody4.append(new AssignmentStatement("O6",
				ConstantExpr.FalseExpr));
		elseBody4 = elseBody4.append(new AssignmentStatement("O3", new VarExpr(
				"I1")));
		elseBody4 = elseBody4.append(new AssignmentStatement("O4", new VarExpr(
				"I2")));
		elseBody4 = elseBody4.append(new AssignmentStatement("O5", new OrExpr(
				new VarExpr("S"), new VarExpr("T"))));
		pStatements3 = pStatements3.append(new IfElseStatement(elseBody4,
				ifBody4, ifCondition4));
		statements1 = statements1.append(new Process(pStatements3,
				sensitivityList3));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("O0");
		outputs1 = outputs1.append("O1");
		outputs1 = outputs1.append("O2");
		outputs1 = outputs1.append("O3");
		outputs1 = outputs1.append("O4");
		outputs1 = outputs1.append("O5");
		outputs1 = outputs1.append("O6");
		outputs1 = outputs1.append("O7");
		outputs1 = outputs1.append("O8");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("J1");
		inputs1 = inputs1.append("J0");
		inputs1 = inputs1.append("I0");
		inputs1 = inputs1.append("I1");
		inputs1 = inputs1.append("I2");
		inputs1 = inputs1.append("I3");
		inputs1 = inputs1.append("S");
		inputs1 = inputs1.append("T");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "DanWuzHere", "behv1"), new Entity(
				outputs1, inputs1, "DanWuzHere")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void four_bit_ripple_carry_adder() {
		test("four_bit_ripple_carry_adder",
				VPROGRAMS.get("four_bit_ripple_carry_adder"));
	}

	protected static VProgram make_four_bit_ripple_carry_adder() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		statements1 = statements1.append(new AssignmentStatement("S",
				new XOrExpr(new XOrExpr(new VarExpr("A"), new VarExpr("B")),
						new VarExpr("Cin"))));
		statements1 = statements1.append(new AssignmentStatement("Cout",
				new OrExpr(new AndExpr(new XOrExpr(new VarExpr("A"),
						new VarExpr("B")), new VarExpr("Cin")), new AndExpr(
						new VarExpr("A"), new VarExpr("B")))));
		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("S");
		outputs1 = outputs1.append("Cout");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("A");
		inputs1 = inputs1.append("B");
		inputs1 = inputs1.append("Cin");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "full_adder", "full_adder_arch"),
				new Entity(outputs1, inputs1, "full_adder")));
		ImmutableList<Statement> statements2 = ImmutableList.of();
		statements2 = statements2.append(new AssignmentStatement("S",
				new NotExpr(new OrExpr(new VarExpr("A"), new VarExpr("B")))));
		statements2 = statements2.append(new AssignmentStatement("C",
				new AndExpr(new VarExpr("A"), new VarExpr("B"))));
		ImmutableList<Component> components2 = ImmutableList.of();
		ImmutableList<String> signals2 = ImmutableList.of();
		ImmutableList<String> outputs2 = ImmutableList.of();
		outputs2 = outputs2.append("S");
		outputs2 = outputs2.append("C");
		ImmutableList<String> inputs2 = ImmutableList.of();
		inputs2 = inputs2.append("A");
		inputs2 = inputs2.append("B");
		vp = vp.append(new DesignUnit(new Architecture(statements2,
				components2, signals2, "half_adder", "half_adder_arch"),
				new Entity(outputs2, inputs2, "half_adder")));
		ImmutableList<Statement> statements3 = ImmutableList.of();
		statements3 = statements3.append(new AssignmentStatement("V",
				new XOrExpr(new VarExpr("c3"), new VarExpr("c4"))));
		statements3 = statements3.append(new AssignmentStatement("Cout",
				new VarExpr("c4")));
		ImmutableList<Component> components3 = ImmutableList.of();
		ImmutableList<String> componentSignals1 = ImmutableList.of();
		componentSignals1 = componentSignals1.append("a0");
		componentSignals1 = componentSignals1.append("b0");
		componentSignals1 = componentSignals1.append("Cin");
		componentSignals1 = componentSignals1.append("sum0");
		componentSignals1 = componentSignals1.append("c1");
		components3 = components3.append(new Component(componentSignals1,
				"full_adder", "FA0"));
		ImmutableList<String> componentSignals2 = ImmutableList.of();
		componentSignals2 = componentSignals2.append("a1");
		componentSignals2 = componentSignals2.append("b1");
		componentSignals2 = componentSignals2.append("c1");
		componentSignals2 = componentSignals2.append("sum1");
		componentSignals2 = componentSignals2.append("c2");
		components3 = components3.append(new Component(componentSignals2,
				"full_adder", "FA1"));
		ImmutableList<String> componentSignals3 = ImmutableList.of();
		componentSignals3 = componentSignals3.append("a2");
		componentSignals3 = componentSignals3.append("b2");
		componentSignals3 = componentSignals3.append("c2");
		componentSignals3 = componentSignals3.append("sum2");
		componentSignals3 = componentSignals3.append("c3");
		components3 = components3.append(new Component(componentSignals3,
				"full_adder", "FA2"));
		ImmutableList<String> componentSignals4 = ImmutableList.of();
		componentSignals4 = componentSignals4.append("a3");
		componentSignals4 = componentSignals4.append("b3");
		componentSignals4 = componentSignals4.append("c3");
		componentSignals4 = componentSignals4.append("sum3");
		componentSignals4 = componentSignals4.append("c4");
		components3 = components3.append(new Component(componentSignals4,
				"full_adder", "FA3"));
		ImmutableList<String> signals3 = ImmutableList.of();
//		signals3 = signals3.append("c0");
		signals3 = signals3.append("c1");
		signals3 = signals3.append("c2");
		signals3 = signals3.append("c3");
		signals3 = signals3.append("c4");
		ImmutableList<String> outputs3 = ImmutableList.of();
		outputs3 = outputs3.append("sum0");
		outputs3 = outputs3.append("sum1");
		outputs3 = outputs3.append("sum2");
		outputs3 = outputs3.append("sum3");
		outputs3 = outputs3.append("Cout");
		outputs3 = outputs3.append("V");
		ImmutableList<String> inputs3 = ImmutableList.of();
		inputs3 = inputs3.append("a0");
		inputs3 = inputs3.append("b0");
		inputs3 = inputs3.append("a1");
		inputs3 = inputs3.append("b1");
		inputs3 = inputs3.append("a2");
		inputs3 = inputs3.append("b2");
		inputs3 = inputs3.append("a3");
		inputs3 = inputs3.append("b3");
		inputs3 = inputs3.append("Cin");
		vp = vp.append(new DesignUnit(new Architecture(statements3,
				components3, signals3, "four_bit_ripple_carry_adder",
				"fouradder_structure"), new Entity(outputs3, inputs3,
				"four_bit_ripple_carry_adder")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void full_adder() {
		test("full_adder", VPROGRAMS.get("full_adder"));
	}

	protected static VProgram make_full_adder() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		statements1 = statements1.append(new AssignmentStatement("S",
				new XOrExpr(new XOrExpr(new VarExpr("A"), new VarExpr("B")),
						new VarExpr("Cin"))));
		statements1 = statements1.append(new AssignmentStatement("Cout",
				new OrExpr(new AndExpr(new XOrExpr(new VarExpr("A"),
						new VarExpr("B")), new VarExpr("Cin")), new AndExpr(
						new VarExpr("A"), new VarExpr("B")))));
		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("S");
		outputs1 = outputs1.append("Cout");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("A");
		inputs1 = inputs1.append("B");
		inputs1 = inputs1.append("Cin");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "full_adder", "full_adder_arch"),
				new Entity(outputs1, inputs1, "full_adder")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void half_adder() {
		test("half_adder", VPROGRAMS.get("half_adder"));
	}

	protected static VProgram make_half_adder() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		statements1 = statements1.append(new AssignmentStatement("S",
				new NotExpr(new OrExpr(new VarExpr("A"), new VarExpr("B")))));
		statements1 = statements1.append(new AssignmentStatement("C",
				new AndExpr(new VarExpr("A"), new VarExpr("B"))));
		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("S");
		outputs1 = outputs1.append("C");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("A");
		inputs1 = inputs1.append("B");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "half_adder", "half_adder_arch"),
				new Entity(outputs1, inputs1, "half_adder")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void mux() {
		test("mux", VPROGRAMS.get("mux"));
	}

	protected static VProgram make_mux() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("I3");
		sensitivityList1 = sensitivityList1.append("I2");
		sensitivityList1 = sensitivityList1.append("I1");
		sensitivityList1 = sensitivityList1.append("I0");
		sensitivityList1 = sensitivityList1.append("S");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new EqualExpr(new VarExpr("S"),
				ConstantExpr.FalseExpr);
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("O0",
				new VarExpr("I0")));
		ifBody1 = ifBody1.append(new AssignmentStatement("O1",
				new VarExpr("I2")));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("O0", new VarExpr(
				"I1")));
		elseBody1 = elseBody1.append(new AssignmentStatement("O1", new VarExpr(
				"I3")));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("O0");
		outputs1 = outputs1.append("O1");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("I3");
		inputs1 = inputs1.append("I2");
		inputs1 = inputs1.append("I1");
		inputs1 = inputs1.append("I0");
		inputs1 = inputs1.append("S");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "Mux", "behv1"), new Entity(outputs1,
				inputs1, "Mux")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void mux2() {
		test("mux2", VPROGRAMS.get("mux2"));
	}

	protected static VProgram make_mux2() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("I3");
		sensitivityList1 = sensitivityList1.append("I2");
		sensitivityList1 = sensitivityList1.append("I1");
		sensitivityList1 = sensitivityList1.append("I0");
		sensitivityList1 = sensitivityList1.append("S");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new EqualExpr(new VarExpr("S"),
				ConstantExpr.FalseExpr);
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("O0",
				new VarExpr("I0")));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("O0", new VarExpr(
				"I1")));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		Expr ifCondition2 = new EqualExpr(new VarExpr("S"),
				ConstantExpr.FalseExpr);
		ImmutableList<AssignmentStatement> ifBody2 = ImmutableList.of();
		ifBody2 = ifBody2.append(new AssignmentStatement("O1",
				new VarExpr("I2")));
		ImmutableList<AssignmentStatement> elseBody2 = ImmutableList.of();
		elseBody2 = elseBody2.append(new AssignmentStatement("O1", new VarExpr(
				"I3")));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody2,
				ifBody2, ifCondition2));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("O0");
		outputs1 = outputs1.append("O1");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("I3");
		inputs1 = inputs1.append("I2");
		inputs1 = inputs1.append("I1");
		inputs1 = inputs1.append("I0");
		inputs1 = inputs1.append("S");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "Mux", "behv1"), new Entity(outputs1,
				inputs1, "Mux")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void nor_1() {
		test("nor_1", VPROGRAMS.get("nor_1"));
	}

	protected static VProgram make_nor_1() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("x");
		sensitivityList1 = sensitivityList1.append("y");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new AndExpr(new EqualExpr(new VarExpr("x"),
				ConstantExpr.FalseExpr), new EqualExpr(new VarExpr("y"),
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("F",
				ConstantExpr.TrueExpr));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("F",
				ConstantExpr.FalseExpr));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("F");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "NOR_gate", "NOR_gate_arch"),
				new Entity(outputs1, inputs1, "NOR_gate")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void nor_2() {
		test("nor_2", VPROGRAMS.get("nor_2"));
	}

	protected static VProgram make_nor_2() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		statements1 = statements1.append(new AssignmentStatement("F",
				new NOrExpr(new VarExpr("x"), new VarExpr("y"))));
		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("F");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "NOR_gate", "NOR_gate_arch"),
				new Entity(outputs1, inputs1, "NOR_gate")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void or_1() {
		test("or_1", VPROGRAMS.get("or_1"));
	}

	protected static VProgram make_or_1() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("x");
		sensitivityList1 = sensitivityList1.append("y");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new AndExpr(new EqualExpr(new VarExpr("x"),
				ConstantExpr.FalseExpr), new EqualExpr(new VarExpr("y"),
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("z",
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("z",
				ConstantExpr.TrueExpr));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("z");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "OR_gate", "OR_gate_arch"), new Entity(
				outputs1, inputs1, "OR_gate")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void or_1_eight_port() {
		test("or_1_eight_port", VPROGRAMS.get("or_1_eight_port"));
	}

	protected static VProgram make_or_1_eight_port() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("x");
		sensitivityList1 = sensitivityList1.append("y");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new AndExpr(new EqualExpr(new VarExpr("x"),
				ConstantExpr.FalseExpr), new EqualExpr(new VarExpr("y"),
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("z",
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("z",
				ConstantExpr.TrueExpr));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("z");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "OR_gate", "OR_gate_arch"), new Entity(
				outputs1, inputs1, "OR_gate")));
		ImmutableList<Statement> statements2 = ImmutableList.of();
		ImmutableList<String> sensitivityList2 = ImmutableList.of();
		sensitivityList2 = sensitivityList2.append("e");
		sensitivityList2 = sensitivityList2.append("f");
		ImmutableList<Statement> pStatements2 = ImmutableList.of();
		pStatements2 = pStatements2.append(new AssignmentStatement("result",
				new OrExpr(new VarExpr("e"), new VarExpr("f"))));
		statements2 = statements2.append(new Process(pStatements2,
				sensitivityList2));

		ImmutableList<Component> components2 = ImmutableList.of();
		ImmutableList<String> componentSignals1 = ImmutableList.of();
		componentSignals1 = componentSignals1.append("a");
		componentSignals1 = componentSignals1.append("b");
		componentSignals1 = componentSignals1.append("e");
		components2 = components2.append(new Component(componentSignals1,
				"OR_gate", "OR1"));
		ImmutableList<String> componentSignals2 = ImmutableList.of();
		componentSignals2 = componentSignals2.append("c");
		componentSignals2 = componentSignals2.append("d");
		componentSignals2 = componentSignals2.append("f");
		components2 = components2.append(new Component(componentSignals2,
				"OR_gate", "OR2"));
		ImmutableList<String> signals2 = ImmutableList.of();
		signals2 = signals2.append("e");
		signals2 = signals2.append("f");
		ImmutableList<String> outputs2 = ImmutableList.of();
		outputs2 = outputs2.append("result");
		ImmutableList<String> inputs2 = ImmutableList.of();
		inputs2 = inputs2.append("a");
		inputs2 = inputs2.append("b");
		inputs2 = inputs2.append("c");
		inputs2 = inputs2.append("d");
		vp = vp.append(new DesignUnit(new Architecture(statements2,
				components2, signals2, "four_port_OR_gate",
				"four_port_structure"), new Entity(outputs2, inputs2,
				"four_port_OR_gate")));
		ImmutableList<Statement> statements3 = ImmutableList.of();
		ImmutableList<String> sensitivityList3 = ImmutableList.of();
		sensitivityList3 = sensitivityList3.append("result1");
		sensitivityList3 = sensitivityList3.append("result2");
		ImmutableList<Statement> pStatements3 = ImmutableList.of();
		pStatements3 = pStatements3.append(new AssignmentStatement("y",
				new OrExpr(new VarExpr("result1"), new VarExpr("result2"))));
		statements3 = statements3.append(new Process(pStatements3,
				sensitivityList3));

		ImmutableList<Component> components3 = ImmutableList.of();
		ImmutableList<String> componentSignals3 = ImmutableList.of();
		componentSignals3 = componentSignals3.append("x0");
		componentSignals3 = componentSignals3.append("x1");
		componentSignals3 = componentSignals3.append("x2");
		componentSignals3 = componentSignals3.append("x3");
		componentSignals3 = componentSignals3.append("result1");
		components3 = components3.append(new Component(componentSignals3,
				"four_port_OR_gate", "OR1"));
		ImmutableList<String> componentSignals4 = ImmutableList.of();
		componentSignals4 = componentSignals4.append("x4");
		componentSignals4 = componentSignals4.append("x5");
		componentSignals4 = componentSignals4.append("x6");
		componentSignals4 = componentSignals4.append("x7");
		componentSignals4 = componentSignals4.append("result2");
		components3 = components3.append(new Component(componentSignals4,
				"four_port_OR_gate", "OR2"));
		ImmutableList<String> signals3 = ImmutableList.of();
		signals3 = signals3.append("result1");
		signals3 = signals3.append("result2");
		ImmutableList<String> outputs3 = ImmutableList.of();
		outputs3 = outputs3.append("y");
		ImmutableList<String> inputs3 = ImmutableList.of();
		inputs3 = inputs3.append("x0");
		inputs3 = inputs3.append("x1");
		inputs3 = inputs3.append("x2");
		inputs3 = inputs3.append("x3");
		inputs3 = inputs3.append("x4");
		inputs3 = inputs3.append("x5");
		inputs3 = inputs3.append("x6");
		inputs3 = inputs3.append("x7");
		vp = vp.append(new DesignUnit(new Architecture(statements3,
				components3, signals3, "eight_port_OR_gate",
				"eight_port_structure"), new Entity(outputs3, inputs3,
				"eight_port_OR_gate")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void or_2() {
		test("or_2", VPROGRAMS.get("or_2"));
	}

	protected static VProgram make_or_2() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		statements1 = statements1.append(new AssignmentStatement("F",
				new OrExpr(new VarExpr("x"), new VarExpr("y"))));
		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("F");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "OR_gate_2", "OR_gate_arch"),
				new Entity(outputs1, inputs1, "OR_gate_2")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void or_2_eight_port() {
		test("or_2_eight_port", VPROGRAMS.get("or_2_eight_port"));
	}

	protected static VProgram make_or_2_eight_port() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		statements1 = statements1.append(new AssignmentStatement("F",
				new OrExpr(new VarExpr("x"), new VarExpr("y"))));
		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("F");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "OR_gate_2", "OR_gate_arch"),
				new Entity(outputs1, inputs1, "OR_gate_2")));
		ImmutableList<Statement> statements2 = ImmutableList.of();
		statements2 = statements2.append(new AssignmentStatement("result",
				new OrExpr(new VarExpr("e"), new VarExpr("f"))));
		ImmutableList<Component> components2 = ImmutableList.of();
		ImmutableList<String> componentSignals1 = ImmutableList.of();
		componentSignals1 = componentSignals1.append("a");
		componentSignals1 = componentSignals1.append("b");
		componentSignals1 = componentSignals1.append("e");
		components2 = components2.append(new Component(componentSignals1,
				"OR_gate_2", "OR1"));
		ImmutableList<String> componentSignals2 = ImmutableList.of();
		componentSignals2 = componentSignals2.append("c");
		componentSignals2 = componentSignals2.append("d");
		componentSignals2 = componentSignals2.append("f");
		components2 = components2.append(new Component(componentSignals2,
				"OR_gate_2", "OR2"));
		ImmutableList<String> signals2 = ImmutableList.of();
		signals2 = signals2.append("e");
		signals2 = signals2.append("f");
		ImmutableList<String> outputs2 = ImmutableList.of();
		outputs2 = outputs2.append("result");
		ImmutableList<String> inputs2 = ImmutableList.of();
		inputs2 = inputs2.append("a");
		inputs2 = inputs2.append("b");
		inputs2 = inputs2.append("c");
		inputs2 = inputs2.append("d");
		vp = vp.append(new DesignUnit(new Architecture(statements2,
				components2, signals2, "four_port_OR_gate_2",
				"four_port_structure"), new Entity(outputs2, inputs2,
				"four_port_OR_gate_2")));
		ImmutableList<Statement> statements3 = ImmutableList.of();
		statements3 = statements3.append(new AssignmentStatement("y",
				new OrExpr(new VarExpr("result1"), new VarExpr("result2"))));
		ImmutableList<Component> components3 = ImmutableList.of();
		ImmutableList<String> componentSignals3 = ImmutableList.of();
		componentSignals3 = componentSignals3.append("x0");
		componentSignals3 = componentSignals3.append("x1");
		componentSignals3 = componentSignals3.append("x2");
		componentSignals3 = componentSignals3.append("x3");
		componentSignals3 = componentSignals3.append("result1");
		components3 = components3.append(new Component(componentSignals3,
				"four_port_OR_gate_2", "OR1"));
		ImmutableList<String> componentSignals4 = ImmutableList.of();
		componentSignals4 = componentSignals4.append("x4");
		componentSignals4 = componentSignals4.append("x5");
		componentSignals4 = componentSignals4.append("x6");
		componentSignals4 = componentSignals4.append("x7");
		componentSignals4 = componentSignals4.append("result2");
		components3 = components3.append(new Component(componentSignals4,
				"four_port_OR_gate_2", "OR2"));
		ImmutableList<String> signals3 = ImmutableList.of();
		signals3 = signals3.append("result1");
		signals3 = signals3.append("result2");
		ImmutableList<String> outputs3 = ImmutableList.of();
		outputs3 = outputs3.append("y");
		ImmutableList<String> inputs3 = ImmutableList.of();
		inputs3 = inputs3.append("x0");
		inputs3 = inputs3.append("x1");
		inputs3 = inputs3.append("x2");
		inputs3 = inputs3.append("x3");
		inputs3 = inputs3.append("x4");
		inputs3 = inputs3.append("x5");
		inputs3 = inputs3.append("x6");
		inputs3 = inputs3.append("x7");
		vp = vp.append(new DesignUnit(new Architecture(statements3,
				components3, signals3, "eight_port_OR_gate_2",
				"eight_port_structure"), new Entity(outputs3, inputs3,
				"eight_port_OR_gate_2")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void or_nor() {
		test("or_nor", VPROGRAMS.get("or_nor"));
	}

	protected static VProgram make_or_nor() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("x");
		sensitivityList1 = sensitivityList1.append("y");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new AndExpr(new EqualExpr(new VarExpr("x"),
				ConstantExpr.FalseExpr), new EqualExpr(new VarExpr("y"),
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("F1",
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("F1",
				ConstantExpr.TrueExpr));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<String> sensitivityList2 = ImmutableList.of();
		sensitivityList2 = sensitivityList2.append("x");
		sensitivityList2 = sensitivityList2.append("y");
		ImmutableList<Statement> pStatements2 = ImmutableList.of();
		Expr ifCondition2 = new AndExpr(new EqualExpr(new VarExpr("x"),
				ConstantExpr.FalseExpr), new EqualExpr(new VarExpr("y"),
				ConstantExpr.FalseExpr));
		ImmutableList<AssignmentStatement> ifBody2 = ImmutableList.of();
		ifBody2 = ifBody2.append(new AssignmentStatement("F2",
				ConstantExpr.TrueExpr));
		ImmutableList<AssignmentStatement> elseBody2 = ImmutableList.of();
		elseBody2 = elseBody2.append(new AssignmentStatement("F2",
				ConstantExpr.FalseExpr));
		pStatements2 = pStatements2.append(new IfElseStatement(elseBody2,
				ifBody2, ifCondition2));
		statements1 = statements1.append(new Process(pStatements2,
				sensitivityList2));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("F1");
		outputs1 = outputs1.append("F2");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "OR_NOR", "OR_NOR_arch"), new Entity(
				outputs1, inputs1, "OR_NOR")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void sbkedida_mux() {
		test("sbkedida_mux", VPROGRAMS.get("sbkedida_mux"));
	}

	protected static VProgram make_sbkedida_mux() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("I5");
		sensitivityList1 = sensitivityList1.append("I4");
		sensitivityList1 = sensitivityList1.append("I3");
		sensitivityList1 = sensitivityList1.append("I2");
		sensitivityList1 = sensitivityList1.append("I1");
		sensitivityList1 = sensitivityList1.append("I0");
		sensitivityList1 = sensitivityList1.append("S");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		Expr ifCondition1 = new EqualExpr(new VarExpr("S"),
				ConstantExpr.FalseExpr);
		ImmutableList<AssignmentStatement> ifBody1 = ImmutableList.of();
		ifBody1 = ifBody1.append(new AssignmentStatement("O0",
				new VarExpr("I0")));
		ifBody1 = ifBody1.append(new AssignmentStatement("O1",
				new VarExpr("I2")));
		ifBody1 = ifBody1.append(new AssignmentStatement("O2",
				new VarExpr("I4")));
		ImmutableList<AssignmentStatement> elseBody1 = ImmutableList.of();
		elseBody1 = elseBody1.append(new AssignmentStatement("O2", new VarExpr(
				"I5")));
		elseBody1 = elseBody1.append(new AssignmentStatement("O0", new VarExpr(
				"I1")));
		elseBody1 = elseBody1.append(new AssignmentStatement("O1", new VarExpr(
				"I3")));
		pStatements1 = pStatements1.append(new IfElseStatement(elseBody1,
				ifBody1, ifCondition1));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("O0");
		outputs1 = outputs1.append("O1");
		outputs1 = outputs1.append("O2");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("I5");
		inputs1 = inputs1.append("I4");
		inputs1 = inputs1.append("I3");
		inputs1 = inputs1.append("I2");
		inputs1 = inputs1.append("I1");
		inputs1 = inputs1.append("I0");
		inputs1 = inputs1.append("S");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "Mux", "behv1"), new Entity(outputs1,
				inputs1, "Mux")));
		assert vp.repOk();
		return vp;
	}

	@Test
	public void scholida_nxor_1() {
		test("scholida_nxor_1", VPROGRAMS.get("scholida_nxor_1"));
	}

	protected static VProgram make_scholida_nxor_1() {
		VProgram vp = new VProgram();
		ImmutableList<Statement> statements1 = ImmutableList.of();
		ImmutableList<String> sensitivityList1 = ImmutableList.of();
		sensitivityList1 = sensitivityList1.append("x");
		sensitivityList1 = sensitivityList1.append("y");
		ImmutableList<Statement> pStatements1 = ImmutableList.of();
		pStatements1 = pStatements1.append(new AssignmentStatement("F",
				new XNOrExpr(new VarExpr("x"), new VarExpr("y"))));
		pStatements1 = pStatements1.append(new AssignmentStatement("Z",
				new XNOrExpr(new AndExpr(new VarExpr("x"), new VarExpr("y")),
						new XNOrExpr(new VarExpr("a"), new VarExpr("b")))));
		statements1 = statements1.append(new Process(pStatements1,
				sensitivityList1));

		ImmutableList<Component> components1 = ImmutableList.of();
		ImmutableList<String> signals1 = ImmutableList.of();
		ImmutableList<String> outputs1 = ImmutableList.of();
		outputs1 = outputs1.append("F");
		outputs1 = outputs1.append("Z");
		ImmutableList<String> inputs1 = ImmutableList.of();
		inputs1 = inputs1.append("a");
		inputs1 = inputs1.append("b");
		inputs1 = inputs1.append("x");
		inputs1 = inputs1.append("y");
		vp = vp.append(new DesignUnit(new Architecture(statements1,
				components1, signals1, "XNOR_test", "XNOR_test_arch"),
				new Entity(outputs1, inputs1, "XNOR_test")));
		assert vp.repOk();
		return vp;
	}

}
