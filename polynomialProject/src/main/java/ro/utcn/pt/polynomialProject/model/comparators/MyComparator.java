package ro.utcn.pt.polynomialProject.model.comparators;

import java.util.Comparator;

import ro.utcn.pt.polynomialProject.model.Term;

//a comparator that compares two terms on their degree
public class MyComparator implements Comparator<Term> {

	public int compare(Term term1, Term term2) {
		return -Integer.valueOf(term1.getDegree()).compareTo(Integer.valueOf(term2.getDegree()));
	}

}
