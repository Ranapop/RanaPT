package ro.utcn.pt.polynomialProject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import ro.utcn.pt.polynomialProject.model.DataRetriever;
import ro.utcn.pt.polynomialProject.model.Polynomial;
import ro.utcn.pt.polynomialProject.model.exceptions.DivideByZeroException;
import ro.utcn.pt.polynomialProject.model.exceptions.InputFormatException;
import ro.utcn.pt.polynomialProject.model.operations.PolynomialOperations;
import ro.utcn.pt.polynomialProject.utilities.Constants;
import ro.utcn.pt.polynomialProject.view.PolynomialFrame;

public class ButtonActionListener implements ActionListener {

	private PolynomialFrame polynomialFrame;
	private DataRetriever dataRetriever;

	public ButtonActionListener(PolynomialFrame polynomialFrame, DataRetriever dataRetriever) {
		this.polynomialFrame = polynomialFrame;
		this.dataRetriever = dataRetriever;
	}

	public void actionPerformed(ActionEvent e) {

		String operation = dataRetriever.retrieveOperation();
		String result = performOperation(operation);
		JLabel display = polynomialFrame.getDisplayResultTextLabel();
		display.setText(result);
	}

	private String performOperation(String operation) {
		String result;
		try {
			Polynomial firstPolynomial = dataRetriever.retrieveFirstPolynomial();
			Polynomial secondPolynomial = dataRetriever.retrieveSecondPolynomial();
			switch (operation) {
			case Constants.Operations.ADD:
				result = PolynomialOperations.add(firstPolynomial, secondPolynomial).toString();
				break;
			case Constants.Operations.DIFFERENTIATE:
			   result  = PolynomialOperations.differentiate(firstPolynomial).toString();
			   break;
			case Constants.Operations.DIVIDE:
				result = performDivision(firstPolynomial, secondPolynomial);
				break;
			case Constants.Operations.INTEGRATE:
				result  = PolynomialOperations.integrate(firstPolynomial).toString();
				break;
			case Constants.Operations.MULTILY:
				result  = PolynomialOperations.multiply(firstPolynomial, secondPolynomial).toString();
				break;
			case Constants.Operations.SUBTRACT:
				result  = PolynomialOperations.subtract(firstPolynomial, secondPolynomial).toString();
				break;
			default:
				result = "";
				break;
			}
		} catch (InputFormatException e) {
			result = "<html>Error! Wrong input format<br>Please make sure input is in the format anx^n+...+a1x^1+a0</html>";
			e.printStackTrace();
		}
		return result;
	}
	
	private String performDivision(Polynomial firstPolynomial, Polynomial secondPolynomial){
		String result;
		ArrayList<Polynomial> results;
		try {
			results = PolynomialOperations.divide(firstPolynomial, secondPolynomial);
			String quotient = results.get(0).toString();
			String remainder = results.get(1).toString();
			result = "<html>" + "Qoutient:     "+quotient + "<br>Remainder " + remainder + "</html>";
		} catch (DivideByZeroException e) {
			// TODO Auto-generated catch block
			result = "The divider cannot be the zero polynomial";
			e.printStackTrace();
		}
		return result;
	}
}
