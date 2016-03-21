package ro.utcn.pt.polynomialProject.view;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import ro.utcn.pt.polynomialProject.model.DataRetriever;
import ro.utcn.pt.polynomialProject.model.Polynomial;
import ro.utcn.pt.polynomialProject.model.exceptions.InputFormatException;

public class FrameDataRetriever implements DataRetriever{

	private PolynomialFrame polynomialFrame;
	public FrameDataRetriever(PolynomialFrame polynomialFrame){
		this.polynomialFrame = polynomialFrame;
	}
	@Override
	public Polynomial retrieveFirstPolynomial() throws InputFormatException {
		JTextField firstPolyTextField = polynomialFrame.getFirstPolyTextField();
		String firstPolyStr = firstPolyTextField.getText();
		if(firstPolyStr.equals("")){
			throw new InputFormatException();
		}else{
			firstPolyStr = firstPolyStr.replace(" ", "");
			return new Polynomial(firstPolyStr);
		}
	}
	@Override
	public Polynomial retrieveSecondPolynomial() throws InputFormatException {
		JTextField secondPolyTextField = polynomialFrame.getSecondPolyTextField();
		String secondPolyStr = secondPolyTextField.getText();
		if(secondPolyStr.equals("")){
			throw new InputFormatException();
		}else{
			secondPolyStr = secondPolyStr.replace(" ", "");
			return new Polynomial(secondPolyStr);
		}
	}
	@Override
	public String retrieveOperation() {
		JComboBox<String> comboBox = polynomialFrame.getComboBoxForOperations();
		String operation = (String) comboBox.getSelectedItem();
		return operation;
	}


}
