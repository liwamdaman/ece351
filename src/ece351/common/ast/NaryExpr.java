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

package ece351.common.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.parboiled.common.ImmutableList;

import ece351.util.Examinable;
import ece351.util.Examiner;

/**
 * An expression with multiple children. Must be commutative.
 */
public abstract class NaryExpr extends Expr {

	public final ImmutableList<Expr> children;

	public NaryExpr(final Expr... exprs) {
		Arrays.sort(exprs);
		ImmutableList<Expr> c = ImmutableList.of();
		for (final Expr e : exprs) {
			c = c.append(e);
		}
    	this.children = c;
	}
	
	public NaryExpr(final List<Expr> children) {
		final ArrayList<Expr> a = new ArrayList<Expr>(children);
		Collections.sort(a);
		this.children = ImmutableList.copyOf(a);
	}

	/**
	 * Each subclass must implement this factory method to return
	 * a new object of its own type. 
	 */
	public abstract NaryExpr newNaryExpr(final List<Expr> children);

	/**
	 * Construct a new NaryExpr (of the appropriate subtype) with 
	 * one extra child.
	 * @param e the child to append
	 * @return a new NaryExpr
	 */
	public NaryExpr append(final Expr e) {
		return newNaryExpr(children.append(e));
	}

	/**
	 * Construct a new NaryExpr (of the appropriate subtype) with 
	 * the extra children.
	 * @param list the children to append
	 * @return a new NaryExpr
	 */
	public NaryExpr appendAll(final List<Expr> list) {
		final List<Expr> a = new ArrayList<Expr>(children.size() + list.size());
		a.addAll(children);
		a.addAll(list);
		return newNaryExpr(a);
	}

	/**
	 * Check the representation invariants.
	 */
	public boolean repOk() {
		// programming sanity
		assert this.children != null;
		// should not have a single child: indicates a bug in simplification
		assert this.children.size() > 1 : "should have more than one child, probably a bug in simplification";
		// check that children is sorted
		int i = 0;
		for (int j = 1; j < this.children.size(); i++, j++) {
			final Expr x = this.children.get(i);
			assert x != null : "null children not allowed in NaryExpr";
			final Expr y = this.children.get(j);
			assert y != null : "null children not allowed in NaryExpr";
			assert x.compareTo(y) <= 0 : "NaryExpr.children must be sorted";
		}
        // Note: children might contain duplicates --- not checking for that
        // ... maybe should check for duplicate children ...
		// no problems found
		return true;
	}

	/**
	 * The name of the operator represented by the subclass.
	 * To be implemented by each subclass.
	 */
	public abstract String operator();
	
	/**
	 * The complementary operation: NaryAnd returns NaryOr, and vice versa.
	 */
	abstract protected Class<? extends NaryExpr> getThatClass();
	

	/**
     * e op x = e for absorbing element e and operator op.
     * @return
     */
	public abstract ConstantExpr getAbsorbingElement();

    /**
     * e op x = x for identity element e and operator op.
     * @return
     */
	public abstract ConstantExpr getIdentityElement();


	@Override 
    public final String toString() {
    	final StringBuilder b = new StringBuilder();
    	b.append("(");
    	int count = 0;
    	for (final Expr c : children) {
    		b.append(c);
    		if (++count  < children.size()) {
    			b.append(" ");
    			b.append(operator());
    			b.append(" ");
    		}
    		
    	}
    	b.append(")");
    	return b.toString();
    }


	@Override
	public final int hashCode() {
		return 17 + children.hashCode();
	}

	@Override
	public final boolean equals(final Object obj) {
		if (!(obj instanceof Examinable)) return false;
		return examine(Examiner.Equals, (Examinable)obj);
	}
	
	@Override
	public final boolean isomorphic(final Examinable obj) {
		return examine(Examiner.Isomorphic, obj);
	}
	
	private boolean examine(final Examiner e, final Examinable obj) {
		// basics
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) return false;
		final NaryExpr that = (NaryExpr) obj;
		
		// if the number of children are different, consider them not equivalent
		// since the n-ary expressions have the same number of children and they are sorted, just iterate and check
		// supposed to be sorted, but might not be (because repOk might not pass)
		// if they are not the same elements in the same order return false
		// no significant differences found, return true
		
		if (this.children.size() != that.children.size()) {
			return false;
		}
		
		for (int i = 0; i< this.children.size(); i++) {
			if (!e.examine(this.children.get(i), that.children.get(i))) {
				return false;
			}
		}
		
		return true;
	}

	
	@Override
	protected final Expr simplifyOnce() {
		assert repOk();
		final Expr result = 
				simplifyChildren().
				mergeGrandchildren().
				foldIdentityElements().
				foldAbsorbingElements().
				foldComplements().
				removeDuplicates().
				simpleAbsorption().
				subsetAbsorption().
				singletonify();
		assert result.repOk();
		return result;
	}
	
	/**
	 * Call simplify() on each of the children.
	 */
	private NaryExpr simplifyChildren() {
		// note: we do not assert repOk() here because the rep might not be ok
		// the result might contain duplicate children, and the children
		// might be out of order
		NaryExpr result = newNaryExpr(Arrays.asList());
		for (Expr child: this.children) {
			result = result.append(child.simplify());
		}
		return result;
	}

	
	private NaryExpr mergeGrandchildren() {
		// extract children to merge using filter (because they are the same type as us)
			// if no children to merge, then return this (i.e., no change)
			// use filter to get the other children, which will be kept in the result unchanged
			// merge in the grandchildren
			// assert result.repOk():  this operation should always leave the AST in a legal state
		
		NaryExpr filteredSameType = filter(this.getClass(), true);
		if (filteredSameType.children.isEmpty()) {
			return this;
		}
		
		NaryExpr result = filter(this.getClass(), false);
		
		for (Expr sameTypeChild: filteredSameType.children) {
			// Assume we can safely cast to NaryExpr since we have filtered children successfully
			NaryExpr child = (NaryExpr) sameTypeChild;
			result = result.appendAll(child.children);
		}		
		
		assert result.repOk();
		return result;
	}


    private NaryExpr foldIdentityElements() {
    	// if we have only one child stop now and return self
    	// we have multiple children, remove the identity elements
    		// all children were identity elements, so now our working list is empty
    		// return a new list with a single identity element
    		// normal return    	
    	
    	if (this.children.size() == 1) {
    		return this;
    	}
    	
    	NaryExpr result = newNaryExpr(Arrays.asList());
    	for (Expr child: this.children) {
    		if (!this.getIdentityElement().equals(child)) {
    			result = result.append(child);
    		}
		}
    	
    	if (result.children.isEmpty()) {
    		result = result.append(this.getIdentityElement());
    	}
    	
    	return result;
    	// do not assert repOk(): this fold might leave the AST in an illegal state (with only one child)
    }

    private NaryExpr foldAbsorbingElements() {
		// absorbing element: 0.x=0 and 1+x=1
			// absorbing element is present: return it
			// not so fast! what is the return type of this method? why does it have to be that way?
			// no absorbing element present, do nothing
    	
    	for (Expr child: this.children) {
    		if (this.getAbsorbingElement().equals(child)) {
    			NaryExpr result = newNaryExpr(Arrays.asList());
    			return result.append(this.getAbsorbingElement());
    		}
		}
    	    	
    	return this;
    	// do not assert repOk(): this fold might leave the AST in an illegal state (with only one child)
	}

	private NaryExpr foldComplements() {
		// collapse complements
		// !x . x . ... = 0 and !x + x + ... = 1
		// x op !x = absorbing element
		// find all negations
		// for each negation, see if we find its complement
				// found matching negation and its complement
				// return absorbing element
		// no complements to fold

		NaryExpr result = newNaryExpr(this.children);
		for (Expr child: result.children) {
			if (child.getClass() == NotExpr.class) {
				if (this.contains(((NotExpr)child).expr, Examiner.Equivalent)) {
					result = result.removeAll(Arrays.asList(child, ((NotExpr)child).expr), Examiner.Equivalent); // Hmmm... isn't it possible that this removes more than just the pair? Do we want this to be possible?
					result = result.append(getAbsorbingElement());
				}
			}
		}
		
		return result;
    	// do not assert repOk(): this fold might leave the AST in an illegal state (with only one child)
	}

	private NaryExpr removeDuplicates() {
		// remove duplicate children: x.x=x and x+x=x
		// since children are sorted this is fairly easy
			// no changes
			// removed some duplicates
		
		if (this.children.size() <= 1) {
			return this;
		}
		
		NaryExpr result = newNaryExpr(this.children);
		for (int i = 1; i < this.children.size(); i++) {
			if (this.children.get(i).equivalent(this.children.get(i-1))) {
				// Remove both x's, and append one x
				result = result.removeAll(Arrays.asList(this.children.get(i), this.children.get(i-1)), Examiner.Equals);
				result = result.append(this.children.get(i));
			}
		}
		
		return result;
    	// do not assert repOk(): this fold might leave the AST in an illegal state (with only one child)
	}

	private NaryExpr simpleAbsorption() {
		// (x.y) + x ... = x ...
		// check if there are any conjunctions that can be removed
		
		NaryExpr filteredOtherType = filter(getThatClass(), true);
		if (filteredOtherType.children.isEmpty()) {
			return this;
		}
		
		NaryExpr result = newNaryExpr(this.children);
		for (Expr child: this.children) {
			if (child.getClass() == VarExpr.class) {
				for (Expr otherTypeChildExpr: filteredOtherType.children) {
					if (((NaryExpr)otherTypeChildExpr).contains(child, Examiner.Equals)) {
						result = result.removeAll(Arrays.asList(otherTypeChildExpr), Examiner.Equals);
					}
				}
			}
		}
		
		return result;
    	// do not assert repOk(): this operation might leave the AST in an illegal state (with only one child)
	}

	private NaryExpr subsetAbsorption() {
		// check if there are any conjunctions that are supersets of others
		// e.g., ( a . b . c ) + ( a . b ) = a . b
		
		// From CampusWire thread:
		/*
		case 1:
		(a . b) + (a . b . c) = a . b
		(a + b) . (a + b + c) = a + b
		
		case 2:
		a . b . ((a . b) + c) = a . b
		a + b + ((a + b) . c) = a + b
		*/
		
		NaryExpr filteredOtherType = this.filter(getThatClass(), true);
		if (filteredOtherType.children.isEmpty()) {
			return this;
		}
		
		NaryExpr result = newNaryExpr(this.children);
		
		// Check case 1:
		for (Expr otherTypeChildExpr1: filteredOtherType.children) {
			for (Expr otherTypeChildExpr2: filteredOtherType.children) {
				if (((NaryExpr)otherTypeChildExpr1).containsNaryExprSubset((NaryExpr)otherTypeChildExpr2, Examiner.Equals)) {
					result = result.removeAll(Arrays.asList(otherTypeChildExpr1), Examiner.Equals);
				}
			}
		}
		
		// Refresh filteredOtherType
		filteredOtherType = result.filter(getThatClass(), true);
		
		// Check case 2:
		for (Expr otherTypeChildExpr: filteredOtherType.children) {
			for (Expr GrandChildExpr: ((NaryExpr)otherTypeChildExpr).children) {
				if (GrandChildExpr.getClass() == NaryAndExpr.class || GrandChildExpr.getClass() == NaryOrExpr.class) {
					// We can assume that this grand child expression is the same AND/OR type as this, or else it would have been merged.
					
					// Check if each great grand child of this grand child expression exists as an individual child of this.
					boolean canBeAbsorbed = true;
					for (Expr greatGrandChild: ((NaryExpr)GrandChildExpr).children) {
						if (!this.contains(greatGrandChild, Examiner.Equals)) {
							canBeAbsorbed = false;
						}
					}
					if (canBeAbsorbed) {
						result = result.removeAll(Arrays.asList(otherTypeChildExpr), Examiner.Equals);
						break;
					}
				}
			}
		}
		
		return result;
    	// do not assert repOk(): this operation might leave the AST in an illegal state (with only one child)
	}
	
	private boolean containsNaryExprSubset(final NaryExpr naryExpr, final Examiner examiner) {
		// Returns true: (a or b or c) contains (a or b)
		// Returns false: (a or b) contains (a or b)
		// we can generalize this to NaryExpr subsets, not just binary expression subsets.
		
		if (this.equals(naryExpr)) {
			// For our purposes, not subset if equal
			return false;
		}
		
		if (!this.getClass().equals(naryExpr.getClass())) return false; // Not sure if this check is needed
		for (Expr child: naryExpr.children) {
			if (!this.contains(child, Examiner.Equals)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * If there is only one child, return it (the containing NaryExpr is unnecessary).
	 */
	private Expr singletonify() {
		// if we have only one child, return it
		// having only one child is an illegal state for an NaryExpr
			// multiple children; nothing to do; return self
		if (this.children.size() == 1) {
			return this.children.get(0);
		}
		
		return this;
	}

	/**
	 * Return a new NaryExpr with only the children of a certain type, 
	 * or excluding children of a certain type.
	 * @param filter
	 * @param shouldMatchFilter
	 * @return
	 */
	public final NaryExpr filter(final Class<? extends Expr> filter, final boolean shouldMatchFilter) {
		ImmutableList<Expr> l = ImmutableList.of();
		for (final Expr child : children) {
			if (child.getClass().equals(filter)) {
				if (shouldMatchFilter) {
					l = l.append(child);
				}
			} else {
				if (!shouldMatchFilter) {
					l = l.append(child);
				}
			}
		}
		return newNaryExpr(l);
	}

	public final NaryExpr filter(final Expr filter, final Examiner examiner, final boolean shouldMatchFilter) {
		ImmutableList<Expr> l = ImmutableList.of();
		for (final Expr child : children) {
			if (examiner.examine(child, filter)) {
				if (shouldMatchFilter) {
					l = l.append(child);
				}
			} else {
				if (!shouldMatchFilter) {
					l = l.append(child);
				}
			}
		}
		return newNaryExpr(l);
	}

	public final NaryExpr removeAll(final List<Expr> toRemove, final Examiner examiner) {
		NaryExpr result = this;
		for (final Expr e : toRemove) {
			result = result.filter(e, examiner, false);
		}
		return result;
	}

	public final boolean contains(final Expr expr, final Examiner examiner) {
		for (final Expr child : children) {
			if (examiner.examine(child, expr)) {
				return true;
			}
		}
		return false;
	}

}
