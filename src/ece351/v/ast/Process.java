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
import ece351.common.ast.Statement;
import ece351.util.Examinable;
import ece351.util.Examiner;
import ece351.util.Utils351;

public final class Process extends Statement implements Examinable {
	public final ImmutableList<String> sensitivityList;
	public final ImmutableList<Statement> sequentialStatements;

	public Process() {
		this.sensitivityList = ImmutableList.of();
		this.sequentialStatements = ImmutableList.of();
	}
	
	public Process(
			final ImmutableList<Statement> statements,
			final ImmutableList<String> sensitivityList) {
		this.sensitivityList = sensitivityList;
		this.sequentialStatements = statements;
	}
	
	public boolean repOk() {
		assert sensitivityList != null;
		assert sequentialStatements != null;
		for (final Statement s : sequentialStatements) {
			assert s.repOk();
		}
		return true;
	}
	
	public Process appendSensitivity(final String s) {
		return new Process(sequentialStatements, sensitivityList.append(s));
	}
	
	public Process appendStatement(final Statement s) {
		return new Process(sequentialStatements.append(s), sensitivityList);
	}

	public Process setSensitivityList(final ImmutableList<String> list) {
		return new Process(sequentialStatements, list);
	}
	
	public Process setStatements(final ImmutableList<Statement> list) {
		return new Process(list, sensitivityList);
	}
	
    @Override
    public String toString() {
    	final StringBuilder output = new StringBuilder();
    	output.append("process ( ");
    	output.append(Utils351.bitListToString(sensitivityList));
        output.append(" ) \n        begin\n");
    	for (Statement stmt : sequentialStatements) {
            if (stmt instanceof AssignmentStatement) {
                output.append("            ");
            }
            output.append(stmt);
    	}
        output.append("        end process;\n");
    	return output.toString();
    }

	@Override
	public int hashCode() {
		return 42;
	}
	
    @Override
	public boolean equals(final Object obj) {
    	if (!(obj instanceof Examinable)) return false;
		return examine(Examiner.Equals, (Examinable)obj);
	}
	
	@Override
	public boolean isomorphic(final Examinable obj) {
		return examine(Examiner.Isomorphic, obj);
	}

	@Override
	public boolean equivalent(final Examinable obj) {
		return examine(Examiner.Equivalent, obj);
	}

	private boolean examine(final Examiner examiner, final Examinable obj) {
		// basics
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		final Process that = (Process) obj;
		
		// compare field values
		if (!Examiner.unorderedEquals(this.sensitivityList, that.sensitivityList)) return false;
		
		// the statements within each process should be ordered, since the statements execute in sequence (and not parallel)
		if (!Examiner.orderedExamination(examiner, this.sequentialStatements, that.sequentialStatements)) return false;
		
		// no significant differences found, return true
		return true;
	}

}
