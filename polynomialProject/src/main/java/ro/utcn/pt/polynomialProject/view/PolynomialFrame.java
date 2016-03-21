package ro.utcn.pt.polynomialProject.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ro.utcn.pt.polynomialProject.utilities.Constants;

public class PolynomialFrame extends AbstractFrame {

	private JButton button;
	private JComboBox<String> comboBoxForOperations;
	private JTextField firstPolyTextField;
	private JTextField secondPolyTextField;
	private JLabel displayResultTextLabel;

	public PolynomialFrame() {
		SpringLayout topSpringLayout = new SpringLayout();
		topPanel.setLayout(topSpringLayout);
		SpringLayout centerSpringLayout = new SpringLayout();
		centerPanel.setLayout(centerSpringLayout);

		setTextForPolyInput(topSpringLayout);
		setEditableTextFieldsForPolyInput(topSpringLayout);
		setOperationsRelatedComponents(topSpringLayout);
		setResultTextLabel(centerSpringLayout);
		addButton(topSpringLayout);
		setVisible(true);
	}

	/*** PUBLIC METHODS ***/
	public JComboBox<String> getComboBoxForOperations() {
		return comboBoxForOperations;
	}

	public JTextField getFirstPolyTextField() {
		return firstPolyTextField;
	}

	public JTextField getSecondPolyTextField() {
		return secondPolyTextField;
	}
	public JLabel getDisplayResultTextLabel(){
		return displayResultTextLabel;
	}

	public void addButtonActionListener(ActionListener actionListener) {
		button.addActionListener(actionListener);
	}

	/*** PRIVATE METHODS ***/
	private void setTextForPolyInput(SpringLayout topSpringLayout) {
		// text display
		JLabel inputTextLabel = new JLabel("Please input the polynomial(s)");
		topSpringLayout.putConstraint(SpringLayout.NORTH, inputTextLabel, 10, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, inputTextLabel, 0, SpringLayout.WEST, topPanel);
		topPanel.add(inputTextLabel);
		// Text label for the first polynomial
		JLabel firstPolyTextLabel = new JLabel("First polynomial");
		topSpringLayout.putConstraint(SpringLayout.NORTH, firstPolyTextLabel, 40, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, firstPolyTextLabel, 0, SpringLayout.WEST, topPanel);
		topPanel.add(firstPolyTextLabel);
		// Text label for the second polynomial
		JLabel secondPolyTextLabel = new JLabel("Second polynomial");
		topSpringLayout.putConstraint(SpringLayout.NORTH, secondPolyTextLabel, 80, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, secondPolyTextLabel, 0, SpringLayout.WEST, topPanel);
		topPanel.add(secondPolyTextLabel);
	}

	private void setEditableTextFieldsForPolyInput(SpringLayout topSpringLayout) {

		// first poly
		firstPolyTextField = new JTextField(10);
		firstPolyTextField.setText("0");
		topSpringLayout.putConstraint(SpringLayout.NORTH, firstPolyTextField, 60, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, firstPolyTextField, 0, SpringLayout.WEST, topPanel);
		topPanel.add(firstPolyTextField);
		// seccond poly
		secondPolyTextField = new JTextField(10);
		secondPolyTextField.setText("0");
		topSpringLayout.putConstraint(SpringLayout.NORTH, secondPolyTextField, 100, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, secondPolyTextField, 0, SpringLayout.WEST, topPanel);
		topPanel.add(secondPolyTextField);
	}

	private void setOperationsRelatedComponents(SpringLayout topSpringLayout) {
		JLabel operationsTextLabel = new JLabel("Please choose operation");
		topSpringLayout.putConstraint(SpringLayout.NORTH, operationsTextLabel, 10, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, operationsTextLabel, 200, SpringLayout.WEST, topPanel);
		topPanel.add(operationsTextLabel);

		// For choosing the operation
		String[] operations = { Constants.Operations.ADD, Constants.Operations.DIFFERENTIATE,
				Constants.Operations.DIVIDE, Constants.Operations.INTEGRATE, Constants.Operations.MULTILY,
				Constants.Operations.SUBTRACT };
		comboBoxForOperations = new JComboBox<String>(operations);
		comboBoxForOperations.setSelectedIndex(0);
		topSpringLayout.putConstraint(SpringLayout.NORTH, comboBoxForOperations, 50, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, comboBoxForOperations, 200, SpringLayout.WEST, topPanel);
		topPanel.add(comboBoxForOperations);
	}

	private void setResultTextLabel(SpringLayout centerSpringLayout) {

		JLabel resultTextLabel = new JLabel("Result");
		centerSpringLayout.putConstraint(SpringLayout.NORTH, resultTextLabel, 0, SpringLayout.NORTH, centerPanel);
		centerSpringLayout.putConstraint(SpringLayout.WEST, resultTextLabel, 0, SpringLayout.WEST, centerPanel);
		centerPanel.add(resultTextLabel);

		displayResultTextLabel = new JLabel("");
		centerSpringLayout.putConstraint(SpringLayout.NORTH, displayResultTextLabel, 20, SpringLayout.NORTH, centerPanel);
		centerSpringLayout.putConstraint(SpringLayout.WEST, displayResultTextLabel, 0, SpringLayout.WEST, centerPanel);
		centerPanel.add(displayResultTextLabel);
	}

	private void addButton(SpringLayout topSpringLayout) {
		button = new JButton("Go!");
		topSpringLayout.putConstraint(SpringLayout.NORTH, button, 85, SpringLayout.NORTH, topPanel);
		topSpringLayout.putConstraint(SpringLayout.WEST, button, 200, SpringLayout.WEST, topPanel);
		topPanel.add(button);
	}
}
