package ro.utcn.pt.polynomialProject.model.operations.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ro.utcn.pt.polynomialProject.model.Polynomial;
import ro.utcn.pt.polynomialProject.model.Term;
import ro.utcn.pt.polynomialProject.model.exceptions.DivideByZeroException;
import ro.utcn.pt.polynomialProject.model.exceptions.InputFormatException;
import ro.utcn.pt.polynomialProject.model.operations.PolynomialOperations;

public class PolynomialOperationsTest {

	private Polynomial a;
	private Polynomial b;
	
	@Before
	public void initialise() throws InputFormatException{
	 a = new Polynomial("3x^3-5x^2+10x^1-3");
	 b = new Polynomial("3x^1+1");
	}
	
	@Test
	public void testAdd() throws InputFormatException{

		Polynomial result = PolynomialOperations.add(a, b);
		Polynomial expectedResult = new Polynomial("3x^3-5x^2+13x^1-2");
		assertEquals("polynomial add" , expectedResult,result);
	}
	
	@Test
	public void testSubtract()throws InputFormatException{

		Polynomial result = PolynomialOperations.subtract(a, b);
		Polynomial expectedResult = new Polynomial("3x^3-5x^2+7x^1-4");
		assertEquals("polynomial subtract" , expectedResult,result);
	}
	
	@Test
	public void testMultiply()throws InputFormatException{
		Polynomial result = PolynomialOperations.multiply(a, b);
		Polynomial expectedResult = new Polynomial("9x^4-12x^3+25x^2+x^1-3");
		assertEquals("polynomial subtract" , expectedResult,result);
	}
	
	@Test
	public void testDivide()throws InputFormatException, DivideByZeroException{
		
		ArrayList<Polynomial> result = PolynomialOperations.divide(a, b);
		Polynomial resultQuotient = result.get(0);
		Polynomial resultRemainder = result.get(1);
		Polynomial expectedResultQuotient = new Polynomial("x^2-2x^1+4");
		Polynomial expectedResultRemainder = new Polynomial("-7");
		assertEquals("polynomial divide quotient" , expectedResultQuotient,resultQuotient);
		assertEquals("polynomial divide remainder" , expectedResultRemainder,resultRemainder);
		

		Polynomial const1 = new Polynomial("3");
		Polynomial const2 = new Polynomial("1");
		ArrayList<Polynomial> result1 = PolynomialOperations.divide(const1, const2);
		Polynomial resultQuotient1 = result1.get(0);
		Polynomial resultRemainder1 = result1.get(1);
		Polynomial expectedResultQuotient1 = new Polynomial("3");
		Polynomial expectedResultRemainder1 = new Polynomial("0");
		assertEquals("polynomial divide quotient" , expectedResultQuotient1,resultQuotient1);
		assertEquals("polynomial divide remainder" , expectedResultRemainder1,resultRemainder1);
	}
	
	@Test
	public void testDifferentiate()throws InputFormatException{
		Polynomial result = PolynomialOperations.differentiate(a);
		Polynomial expectedResult = new Polynomial("9x^2-10x^1+10");
		assertEquals("polynomial differentiate" , expectedResult,result);
	}
	
	@Test
	public void testIntegrate(){
		
		Polynomial result = PolynomialOperations.integrate(a);
		Term term1 = new Term(3./4,4);
		Term term2 = new Term(-5./3,3);
		Term term3 = new Term(5.,2);
		Term term4 = new Term(-3.,1);
		Polynomial expectedResult = new Polynomial();
		expectedResult.addTerm(term1);
		expectedResult.addTerm(term2);
		expectedResult.addTerm(term3);
		expectedResult.addTerm(term4);
		assertEquals("polynomial integrate" , expectedResult,result);
	}
}
