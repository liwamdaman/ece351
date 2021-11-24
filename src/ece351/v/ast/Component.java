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

import java.util.TreeSet;

import org.parboiled.common.ImmutableList;

import ece351.util.Examinable;
import ece351.util.Examiner;
import ece351.util.Utils351;

public final class Component implements Examinable {
	public final String entityName;
	public final String instanceName;
	public final ImmutableList<String> signalList;
	public Component(
			final String entityName , 
			final String instanceName) {
		this.entityName = entityName;
		this.instanceName = instanceName;
		this.signalList = ImmutableList.of();
	}
	public Component(
			final ImmutableList<String> signals,
			final String entityName , 
			final String instanceName) {
		this.entityName = entityName;
		this.instanceName = instanceName;
		this.signalList = signals;
	}
	
	public Component appendSignal(final String signal) {
		return new Component(signalList.append(signal), entityName, instanceName);
	}
	
	public Component varyEntityName(final String name) {
		return new Component(signalList, name, instanceName);
	}
	
	public Component varyInstanceName(final String name) {
		return new Component(signalList, entityName, name);
	}
	
	public Component varySignals(final ImmutableList<String> list) {
		return new Component(list, entityName, instanceName);
	}
	
    @Override
    public String toString() {
        return instanceName + " : entity work." + entityName + " port map( " + Utils351.bitListToString(signalList) + ");";
    }

	@Override
	public int hashCode() {
		return entityName.hashCode();
	}
	
    @Override
	public boolean equals(final Object obj) {
		// basics
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		final Component that = (Component) obj;
		
		// compare field values using Examiner.orderedEquals()
		if (!this.entityName.equals(that.entityName)
			|| !this.instanceName.equals(that.instanceName)
			|| !Examiner.orderedEquals(this.signalList, that.signalList)) return false;
		
		// no significant differences found, return true
		return true;
	}
	
	@Override
	public boolean isomorphic(final Examinable obj) {		
		// basics
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		final Component that = (Component) obj;
		
		// compare field values using Examiner.unorderedEquals()
		if (!this.entityName.equals(that.entityName)
			|| !this.instanceName.equals(that.instanceName)
			|| !Examiner.unorderedEquals(this.signalList, that.signalList)) return false;
		
		// no significant differences found, return true
		return true;
	}

	@Override
	public boolean equivalent(final Examinable obj) {
		return isomorphic(obj);
	}
	
	public boolean repOk() {
		assert entityName != null;
		assert instanceName != null;
		assert signalList != null;
		assert entityName.length() > 0;
		assert instanceName.length() > 0;
		// check there are no duplicates in the signal list
		final TreeSet<String> s = new TreeSet<String>(signalList);
		assert s.size() == signalList.size();
		return true;
	}

}
