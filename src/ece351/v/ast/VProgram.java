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

import ece351.util.Examinable;
import ece351.util.Examiner;

public final class VProgram implements Examinable {
	//may not need to make AST for these nodes because we do not need to do 
	//anything with it, we COULD check if they are using things that are defined in the libraries
	//but that is too much work
	
	public final ImmutableList<DesignUnit> designUnits;
	
	/**
	 * Constructs a VProgram with an empty list of design units
	 */
	public VProgram() {
		this.designUnits = ImmutableList.of();
	}
	
	/**
	 * Constucts a VProgram with a list of design units given by parameter designUnits
	 * @param designUnits
	 */
	public VProgram(final ImmutableList<DesignUnit> designUnits) {
		this.designUnits = designUnits;
	}

	public boolean repOk() {
		assert designUnits != null;
		for (final DesignUnit du : designUnits) {
			assert du.repOk();
		}
		return true;
	}

	/**
	 * Construct a new VProgram with this VPrograms design unit list plus d.
	 * @param d
	 * @return new VProgram with parameter d appended to the original list
	 */
	public VProgram append(final DesignUnit d) {
		return new VProgram(designUnits.append(d));
	}
	
	public VProgram setDesignUnits(final ImmutableList<DesignUnit> list) {
		return new VProgram(list);
	}

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        for (final DesignUnit d : designUnits) {                 
                b.append(d.toString());
        }
        return b.toString();
    }

	@Override
	public int hashCode() {
		return 42;
	}
	
    @Override
	public boolean equals(final Object obj) {
		// basics
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		final VProgram that = (VProgram) obj;
		
		// compare field values using Examiner.orderedExamination()
		if (!Examiner.orderedExamination(Examiner.Equals, this.designUnits, that.designUnits)) return false;
		
		// no significant differences found, return true
		return true;
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
		final VProgram that = (VProgram) obj;
		
		// compare field values using Examiner.unorderedExamination()
		if (!Examiner.unorderedExamination(examiner, this.designUnits, that.designUnits)) return false;
		
		// no significant differences found, return true
		return true;
	}

}
