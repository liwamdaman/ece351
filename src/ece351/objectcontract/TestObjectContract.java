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

package ece351.objectcontract;

public final class TestObjectContract extends TestObjectContractBase {

	/**
	 * Construct an object who's equals method always returns false.
	 */
	@Override
	Object constructAlwaysFalse() {
		// this is an anonymous inner class that is a subclass of Object
		return new Object() {
			public boolean equals(Object ob) { 
				return false;
			}
		};
	}
	
	/**
	 * Construct an object who's equals method always returns true.
	 */
	@Override
	Object constructAlwaysTrue() {
		// this is an anonymous inner class that is a subclass of Object
		return new Object() {
			public boolean equals(Object ob) {
				return true;
			}
		};
	}
	
	/**
	 * Construct an object who's equals method alternates between returning true and false.
	 */
	@Override
	Object constructToggler() {
		// this is an anonymous inner class that is a subclass of Object
		return new Object() {
			private boolean flag = true;
			public boolean equals(Object ob) {
				flag = !flag;
				return flag;
			}
		};
	}
	
	/**
	 * Construct two SymmetryBreaker objects to show that symmetry of equality is violated.
	 */
	@Override
	SymmetryBreaker[] constructSymmetryBreakers() {
		final SymmetryBreaker[] result = new SymmetryBreaker[2];
		result[0] = new SymmetryBreaker(0, null);
		result[1] = new SymmetryBreaker(0, result[0]);
		return result;
	}

	
	/**
	 * Construct three TransitivityBreaker objects to show that transitivity of equality is violated.
	 */
	@Override
	TransitivityBreaker[] constructTransitivityBreakers() {
		final double epsilon6 = TestObjectContractBase.TransitivityBreaker.epsilon * 0.6d;
		final TransitivityBreaker[] result = new TransitivityBreaker[3];
		result[0] = new TransitivityBreaker(0);
		result[1] = new TransitivityBreaker(TestObjectContractBase.TransitivityBreaker.epsilon * 0.5d);
		result[2] = new TransitivityBreaker(TestObjectContractBase.TransitivityBreaker.epsilon);
		return result;
	}

	/**
	 * Construct two objects that violate consistency of equals and hashcode.
	 * (In other words, two objects that are equals but have different hashcodes.)
	 * Hint: you can use some of the simple control objects if you understand
	 * what the default hashcode() implementation in Java is.
	 */
	@Override
	Object[] constructHashcodeConsistencyViolators() {
		final Object[] result = new Object[2];
		result[0] = constructAlwaysTrue();
		result[1] = constructAlwaysTrue();
		return result;
	}

	/**
	 * Returns false if obj.equals(null) is true.
	 * @param obj
	 */
	@Override
	boolean checkNotEqualsNull(final Object obj) {
		if (obj.equals(null)) return false;
		return true;
	}

	/**
	 * Returns true if obj.equals(obj) is true.
	 * @param obj
	 */
	@Override
	boolean checkEqualsIsReflexive(final Object obj) {
		return obj.equals(obj);
	}

	/**
	 * Returns true if both are equals, or if both are not equals.
	 * Returns false if one equals the other but not vice versa.
	 * @param o1
	 * @param o2
	 * @return
	 */
	@Override
	boolean checkEqualsIsSymmetric(final Object o1, final Object o2) {
		if (o1.equals(o2) && o2.equals(o1)) return true;
		if (!o1.equals(o2) && !o2.equals(o1)) return true;
		return false;
	}

	/**
	 * Returns true if all three objects are equals to each other.
	 * This isn't the most complete test of transitivity, but it will do.
	 * @param o1
	 * @param o2
	 * @param o3
	 */
	@Override
	boolean checkEqualsIsTransitive(final Object o1, final Object o2, final Object o3) {
		return (o1.equals(o2) && o1.equals(o3) && o2.equals(o3));
	}

	/**
	 * Check that hashCode() is consistent with equals(). 
	 * That is, check logical consistency (not temporal consistency).
	 * A temporal consistency check would examine whether an object 
	 * returns the same hash code value at different points in time.
	 * Immutable objects should always have temporally consistent hashcodes,
	 * whereas mutable objects might not. Therefore, use of mutable objects
	 * as hash keys is discouraged.
	 */
	@Override
	boolean checkHashcodeIsConsistent(final Object o1, final Object o2) {
		if (o1.equals(o2)) {
			return o1.hashCode() == o2.hashCode();
		} else {
			return o1.hashCode() != o2.hashCode();
		}
	}
}
