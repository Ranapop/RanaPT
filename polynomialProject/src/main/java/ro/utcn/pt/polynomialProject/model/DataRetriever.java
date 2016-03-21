package ro.utcn.pt.polynomialProject.model;

import ro.utcn.pt.polynomialProject.model.exceptions.InputFormatException;

public interface DataRetriever {

	public Polynomial retrieveFirstPolynomial() throws InputFormatException;
	public Polynomial retrieveSecondPolynomial() throws InputFormatException;
	public String retrieveOperation();
}
