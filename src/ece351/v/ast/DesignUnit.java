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

import ece351.util.Examinable;
import ece351.util.Examiner;


public final class DesignUnit implements Examinable {
	public final Architecture arch;
	public final Entity entity;
	public final String identifier;

	//An Entity and an Architecture make up a Design Unit
	public DesignUnit(final Architecture arch, final Entity entity) {
		this.arch = arch;
		this.entity = entity;
		this.identifier = entity.identifier;
	}

	public DesignUnit setArchitecture(final Architecture arch2) {
		return new DesignUnit(arch2, entity);
	}

	public DesignUnit setEntity(final Entity entity2) {
		return new DesignUnit(arch, entity2);
	}
	
	@Override
	public String toString() {
		return this.entity + "\n" + this.arch + "\n";
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
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
		final DesignUnit that = (DesignUnit) obj;

		assert this.repOk();
		assert that.repOk();
		
		// compare field values
		if (!this.identifier.equals(that.identifier) 
				|| !examiner.examine(this.arch, that.arch)
				|| !examiner.examine(this.entity, that.entity)) return false;

		// no significant differences found, return true
		return true;
	}

	public boolean repOk() {
		assert arch != null;
		assert entity != null;
		assert identifier != null;
		assert identifier.equals(entity.identifier);
		assert arch.repOk();
		assert entity.repOk();
		return true;
	}

}
