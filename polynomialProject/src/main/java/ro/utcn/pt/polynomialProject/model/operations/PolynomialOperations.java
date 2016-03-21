package ro.utcn.pt.polynomialProject.model.operations;

import java.util.ArrayList;

import ro.utcn.pt.polynomialProject.model.Polynomial;
import ro.utcn.pt.polynomialProject.model.Term;
import ro.utcn.pt.polynomialProject.model.exceptions.DivideByZeroException;

public class PolynomialOperations {
	
	/*** METHOD FOR ADDING TWO POLYNOMIALS ***/
	public static Polynomial add(Polynomial firstPoly, Polynomial secPoly){
		
		Polynomial result = new Polynomial();
		for(Term termOfFirstPoly: firstPoly.getTerms()){
			result.addTerm(termOfFirstPoly.clone());
		}
		for(Term termOfSecPoly: secPoly.getTerms()){
			result.addTerm(termOfSecPoly.clone());
		}
		result.order();
		return result;
	}
	
	/*** METHOD FOR SUBTRACTING TWO POLYNOMIALS ***/
	public static Polynomial subtract(Polynomial firstPoly, Polynomial secPoly){
		
		Polynomial result = new Polynomial();
		for(Term termOfFirstPoly: firstPoly.getTerms()){
			result.addTerm(termOfFirstPoly.clone());
		}
		for(Term termOfSecPoly: secPoly.getTerms()){
			result.addTerm(new Term(-termOfSecPoly.getCoefficient(),termOfSecPoly.getDegree()));
		}
		result.order();
		result.removeZeros();
		return result;
	}
	/*** METHOD FOR MULTIPLYING TWO POLYNOMIALS ***/
	public static Polynomial multiply(Polynomial firstPoly, Polynomial secPoly){
		
		Polynomial result = new Polynomial();
		
		for(Term termOfFirstPoly: firstPoly.getTerms()){
			for(Term termOfSecPoly: secPoly.getTerms()){
				result.addTerm(TermOperations.multiply(termOfFirstPoly, termOfSecPoly));
			}
		}
		result.order();
		result.removeZeros();
		return result;
	}
	
	/*** METHOD FOR DIVIDING TWO POLYNOMIALS 
	 * @throws DivideByZeroException ***/
	public static ArrayList<Polynomial> divide(Polynomial firstPoly, Polynomial secPoly) throws DivideByZeroException{
		
		if(secPoly.isZeroPolynomial()){
			throw new DivideByZeroException();
		}
		
		ArrayList<Polynomial> result = new ArrayList<Polynomial>();
		Polynomial quotient = new Polynomial();
		Polynomial remainder = new Polynomial();
		if(firstPoly.getDegree()<secPoly.getDegree()){
			quotient.addTerm(new Term());//adds a zero term
			remainder = firstPoly.clone();
			result.add(quotient);result.add(remainder);
			return result;
		}
		Polynomial dividend = firstPoly.clone();
		boolean remainderIsZero = false;//for dividing constants
		while(dividend.getDegree()>=secPoly.getDegree()&&!remainderIsZero){
			
			Term newTerm = TermOperations.divide(dividend.getLeadingTerm(),secPoly.getLeadingTerm());//this is part of the quotient
			quotient.addTerm(newTerm);
			Polynomial newTermAsPoly = new Polynomial();
			newTermAsPoly.addTerm(newTerm);
			Polynomial polyToBeSubtracted = PolynomialOperations.multiply(secPoly, newTermAsPoly);
			dividend = PolynomialOperations.subtract(dividend, polyToBeSubtracted);
			dividend.removeZeros();
			remainderIsZero = dividend.isZeroPolynomial();
		}
		remainder = dividend;
		result.add(quotient);result.add(remainder);
		return result;
	}
	
	/*** METHOD FOR INTEGRATING A POLYNOMIAL ***/
	public static Polynomial integrate(Polynomial poly){
		
		Polynomial result = new Polynomial();
		for(Term term: poly.getTerms()){
			result.addTerm(TermOperations.integrate(term));
		}
		return result;
	}
	
	/*** METHOD FOR DIFFERENTIATING A POLYNOMIAL ***/
    public static Polynomial differentiate(Polynomial poly){
		
		Polynomial result = new Polynomial();
		for(Term term: poly.getTerms()){
			result.addTerm(TermOperations.differentiate(term));
		}
		result.removeZeros();
		return result;
	}
}
