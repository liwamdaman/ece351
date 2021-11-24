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
import ece351.util.Utils351;

public final class Entity implements Examinable {
	public final String identifier;
	public final ImmutableList<String> input;
	public final ImmutableList<String> output;
	
	public Entity(final String id) {
		this.identifier = id;
		this.input = ImmutableList.of();
		this.output = ImmutableList.of();
	}
	
	public Entity(final ImmutableList<String> out,
			final ImmutableList<String> in, final String id) {
		this.identifier = id;
		this.input = in;
		this.output = out;
	}
	
	public boolean repOk() {
		assert identifier != null;
		assert input != null;
		assert output != null;
		return true;
	}

	public Entity appendInput(final String i) {
		return new Entity(output, input.append(i), identifier);
	}
	
	public Entity appendOutput(final String o) {
		return new Entity(output.append(o), input, identifier);
	}
	
	public Entity setInput(final ImmutableList<String> list) {
		return new Entity(output, list, identifier);
	}
	
	public Entity setOutput(final ImmutableList<String> list) {
		return new Entity(list, input, identifier);
	}
	
    @Override
    public String toString() {
    	final String inputBits = Utils351.bitListToString(input);
    	final String outputBits = Utils351.bitListToString(output);
        return "entity " + identifier + " is port(\n" 
                + ((inputBits.length() > 0) ? "    " + inputBits + " : in bit;\n" : "")
                + ((outputBits.length() > 0) ? "    " + outputBits + " : out bit\n" : "")
                + ");\nend " + identifier + ";";
    }

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}
	
    @Override
	public boolean equals(final Object obj) {
		// basics
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		final Entity that = (Entity) obj;
		
		// compare field values using Examiner.orderedEquals()
		if (!this.identifier.equals(that.identifier)
			|| !Examiner.orderedEquals(this.input, that.input)
			|| !Examiner.orderedEquals(this.output, that.output)) return false;
		
		// no significant differences found, return true
		return true;
	}
	
	@Override
	public boolean isomorphic(final Examinable obj) {
		return equals(obj);
	}

	@Override
	public boolean equivalent(final Examinable obj) {
		return isomorphic(obj);
	}

}
