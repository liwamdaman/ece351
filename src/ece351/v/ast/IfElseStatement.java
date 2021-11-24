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

package ece351.v.ast;

import org.parboiled.common.ImmutableList;

import ece351.common.ast.AssignmentStatement;
import ece351.common.ast.Expr;
import ece351.common.ast.Statement;
import ece351.f.ast.FProgram;
import ece351.util.Examinable;
import ece351.util.Examiner;

public final class IfElseStatement extends Statement implements Examinable {
	public final Expr condition;
	public final ImmutableList<AssignmentStatement> ifBody;
	public final ImmutableList<AssignmentStatement> elseBody;

	public IfElseStatement(final Expr cond) {
		this.condition = cond;
		this.elseBody = ImmutableList.of();
		this.ifBody = ImmutableList.of();
	}

	public IfElseStatement(final ImmutableList<AssignmentStatement> elseBody,
			final ImmutableList<AssignmentStatement> ifBody, final Expr cond) {
		this.condition = cond;
		this.elseBody = elseBody;
		this.ifBody = ifBody;
	}

	public boolean repOk() {
		assert condition != null;
		assert ifBody != null;
		assert elseBody != null;
		assert condition.repOk();
		for (final AssignmentStatement a : ifBody) {
			assert a.repOk();
		}
		for (final AssignmentStatement a : elseBody) {
			assert a.repOk();
		}
		return true;
	}

	public IfElseStatement appendToTrueBlock(final AssignmentStatement s) {
		return new IfElseStatement(elseBody, ifBody.append(s), condition);
	}

	public IfElseStatement appendToElseBlock(final AssignmentStatement s) {
		return new IfElseStatement(elseBody.append(s), ifBody, condition);
	}

	public IfElseStatement setTrueBlock(final ImmutableList<AssignmentStatement> list) {
		return new IfElseStatement(elseBody, list, condition);
	}
	
	public IfElseStatement setElseBlock(final ImmutableList<AssignmentStatement> list) {
		return new IfElseStatement(list, ifBody, condition);
	}
	
	@Override
	public String toString() {
		final StringBuilder output = new StringBuilder();
		output.append("            if ( ");
		output.append(condition);
		output.append(" ) then\n");
		for (AssignmentStatement stmt : ifBody) {
			output.append("                ");
			output.append(stmt);
		}
		output.append("            else\n");
		for (AssignmentStatement stmt : elseBody) {
			output.append("                ");
			output.append(stmt);
		}
		output.append("            end if;\n");
		return output.toString();
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	public boolean equals(final Object obj) {
		// basics
		if (obj == null)
			return false;
		if (!obj.getClass().equals(this.getClass()))
			return false;
		final IfElseStatement that = (IfElseStatement) obj;

		// compare field values using Examiner.orderedExamination()
		if (!this.condition.equals(that.condition)
				|| !Examiner.orderedExamination(Examiner.Equals, this.ifBody,
						that.ifBody)
				|| !Examiner.orderedExamination(Examiner.Equals, this.elseBody,
						that.elseBody))
			return false;

		// no significant differences found, return true
		return true;
	}

	@Override
	public boolean isomorphic(final Examinable obj) {
		// basics
		if (obj == null)
			return false;
		if (!obj.getClass().equals(this.getClass()))
			return false;
		final IfElseStatement that = (IfElseStatement) obj;

		// compare field values using Examiner.orderedExamination()
		// the statements within each process should be ordered, since the
		// statements execute in sequence (and not parallel)
		// however, compare each statement isomorphically
		if (!this.condition.isomorphic(that.condition)
				|| !Examiner.orderedExamination(Examiner.Isomorphic,
						this.ifBody, that.ifBody)
				|| !Examiner.orderedExamination(Examiner.Isomorphic,
						this.elseBody, that.elseBody))
			return false;

		// no significant differences found, return true
		return true;
	}

	@Override
	public boolean equivalent(final Examinable obj) {
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		final IfElseStatement that = (IfElseStatement) obj;
		assert this.repOk();
		assert that.repOk();
		// condition
		if (!condition.equivalent(that.condition)) return false;
		// if body
		final FProgram ib1 = new FProgram(this.ifBody);
		final FProgram ib2 = new FProgram(that.ifBody);
		if (!ib1.equivalent(ib2)) return false;
		// else body
		final FProgram eb1 = new FProgram(this.elseBody);
		final FProgram eb2 = new FProgram(that.elseBody);
		if (!eb1.equivalent(eb2)) return false;
		// no significant differences found, return true
		return true;
	}
}
