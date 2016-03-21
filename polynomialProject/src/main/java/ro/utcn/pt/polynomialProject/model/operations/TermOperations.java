package ro.utcn.pt.polynomialProject.model.operations;

import ro.utcn.pt.polynomialProject.model.Term;

public class TermOperations {


	// multiplies two terms 
	public static Term multiply(Term t1, Term t2) {

		Term result = new Term();
		result.setCoefficient(t1.getCoefficient() * t2.getCoefficient());
		result.setDegree(t1.getDegree() + t2.getDegree());
		return result;

	}

	// divides two terms 
	public static Term divide(Term t1, Term t2) {

		Term result = new Term();
		result.setCoefficient(t1.getCoefficient() / t2.getCoefficient());
		result.setDegree(t1.getDegree() - t2.getDegree());
		return result;

	}
	//differentiates a term
	public static Term differentiate(Term t) {

		Term result = new Term();
		result.setCoefficient(t.getCoefficient() * t.getDegree());
		result.setDegree(t.getDegree() - 1);
		return result;

	}
	//integrates a term
	public static Term integrate(Term t) {

		Term result = new Term();
		result.setCoefficient(t.getCoefficient() / (t.getDegree()+1.0));
		result.setDegree(t.getDegree() + 1);
		return result;

	}
}
