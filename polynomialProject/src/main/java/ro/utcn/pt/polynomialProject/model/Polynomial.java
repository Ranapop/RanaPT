package ro.utcn.pt.polynomialProject.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringJoiner;

import ro.utcn.pt.polynomialProject.model.comparators.MyComparator;
import ro.utcn.pt.polynomialProject.model.exceptions.InputFormatException;

public class Polynomial {

	private ArrayList<Term> terms;

	public Polynomial() {
		terms = new ArrayList<Term>();
	}

	public Polynomial(String polynomialAsString) throws InputFormatException {
		this();
		for (String termAsString : polynomialAsString.split("\\+")) {
			if (termAsString.contains("-")) {
				String sign = "";// the first term to be delimited is usually
									// positive
				if (termAsString.substring(0, 1).equals("-")) {
					sign = "-";
					termAsString = termAsString.substring(1);
				}
				;
				// has to be further split
				for (String negativeTerm : termAsString.split("\\-")) {
					Term newTerm = new Term(sign + negativeTerm);
					terms.add(newTerm);
					sign = "-";// negative term
				}
			} else {
				Term newTerm = new Term(termAsString);
				terms.add(newTerm);
			}
		}
		this.order();
	}

	public ArrayList<Term> getTerms() {
		return terms;
	}

	public int getDegree() {
		return terms.get(0).getDegree();
		// this function works on the assumption that the polynomial is ordered
		// decreasingly by degrees and there are no zero terms
	}
	
	/*** returns term of highest degree ***/
	public Term getLeadingTerm() {
		// works on the assumption the polynomial is ordered
		return terms.get(0);
	}

	public void addTerm(Term termToBeAdded) {

		boolean added = false;
		for (Term term : terms) {
			if (termToBeAdded.getDegree() == term.getDegree()) {
				term.setCoefficient(term.getCoefficient() + termToBeAdded.getCoefficient());
				added = true;
			}
		}
		if (!added) {
			terms.add(termToBeAdded);
		}

	}

	/*** orders the polynomial decreasingly by the degree of the terms ***/
	public void order() {
		Collections.sort(terms, new MyComparator());
	}

	/*** assures there are no zero-coefficient terms stored ***/
	public void removeZeros() {
		Iterator<Term> itr = terms.iterator();
		while (itr.hasNext()) {
			Term term = itr.next();
			if (term.getCoefficient() == 0.0) {
				itr.remove();
			}
		}
		if (terms.isEmpty()) {
			terms.add(new Term());// 0 polynomial
		}
	}

	/*** copies the content of a polynomial ***/
	public Polynomial clone() {
		Polynomial copy = new Polynomial();
		for (Term term : terms) {
			copy.addTerm(term.clone());
		}
		return copy;
	}

	/*** CHECKS FOR THE ZERO POLYNOMIAL ***/
	public boolean isZeroPolynomial() {
		return terms.size() == 1 && terms.get(0).getCoefficient().equals(new Double(0))
				&& terms.get(0).getDegree() == 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((terms == null) ? 0 : terms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynomial other = (Polynomial) obj;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}

	@Override
	public String toString() {

		StringJoiner polyStr = new StringJoiner("+");

		for (Term term : terms) {

			polyStr.add(term.toString());

		}
		String stringPolynomial = polyStr.toString();
		stringPolynomial = stringPolynomial.replace("+-", "-");
		return stringPolynomial;
	}
}
