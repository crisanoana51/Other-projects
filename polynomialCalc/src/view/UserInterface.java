package view;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel operationLabel;
	private JButton additionButton;
	private JButton substractionButton;
	private JButton multiplicationButton;
	private JButton divisionButton;
	private JButton derivationButton;
	private JButton integrationButton;

	private JLabel operandOneLabel;
	private JTextField operandOneTextField;

	private JLabel operandTwoLabel;
	private JTextField operandTwoTextField;

	private JLabel resultPreLabel;
	private JLabel resultLabel;

	private JLabel observationLabel;

	public UserInterface() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 800, 500);
		getContentPane().setBackground(Color.PINK);
		this.getContentPane().setLayout(null);
		this.setTitle("Polynomial Calculator");

		Font font1 = new Font("Arial", Font.PLAIN, 22);
		Font font2 = new Font("Arial", Font.PLAIN, 40);

		operandOneLabel = new JLabel("Polinom 1: ");
		operandOneLabel.setBounds(50, 20, 120, 30);
		operandOneLabel.setFont(font1);
		getContentPane().add(operandOneLabel);

		operandOneTextField = new JTextField();
		operandOneTextField.setBounds(170, 20, 400, 30);
		operandOneTextField.setFont(font1);
		getContentPane().add(operandOneTextField);

		operandTwoLabel = new JLabel("Polinom 2: ");
		operandTwoLabel.setBounds(50, 70, 120, 30);
		operandTwoLabel.setFont(font1);
		getContentPane().add(operandTwoLabel);

		operandTwoTextField = new JTextField();
		operandTwoTextField.setBounds(170, 70, 400, 30);
		operandTwoTextField.setFont(font1);
		getContentPane().add(operandTwoTextField);

		resultPreLabel = new JLabel("Result: ");
		resultPreLabel.setFont(font1);
		resultPreLabel.setBounds(50, 120, 120, 30);
		getContentPane().add(resultPreLabel);

		resultLabel = new JLabel("Computing...");
		resultLabel.setFont(font1);
		resultLabel.setBounds(125, 160, 600, 30);
		getContentPane().add(resultLabel);

		observationLabel = new JLabel("Correct input form: +1x^3+-6x^2+3x^0");
		observationLabel.setFont(font1);
		observationLabel.setBounds(400, 400, 500, 30);
		getContentPane().add(observationLabel);

		operationLabel = new JLabel("Choose the operation: ");
		operationLabel.setFont(font1);
		operationLabel.setBounds(50, 200, 220, 30);
		getContentPane().add(operationLabel);

		additionButton = new JButton("+");
		additionButton.setFont(font2);
		additionButton.setBounds(170, 250, 150, 60);
		getContentPane().add(additionButton);

		substractionButton = new JButton("-");
		substractionButton.setFont(font2);
		substractionButton.setBounds(340, 250, 150, 60);
		getContentPane().add(substractionButton);

		multiplicationButton = new JButton("*");
		multiplicationButton.setFont(font2);
		multiplicationButton.setBounds(510, 250, 150, 60);
		getContentPane().add(multiplicationButton);

		divisionButton = new JButton("/");
		divisionButton.setFont(font2);
		divisionButton.setBounds(170, 330, 150, 60);
		getContentPane().add(divisionButton);

		derivationButton = new JButton("dx/d");
		derivationButton.setFont(font2);
		derivationButton.setBounds(340, 330, 150, 60);
		getContentPane().add(derivationButton);

		integrationButton = new JButton("\u222B");
		integrationButton.setFont(font2);
		integrationButton.setBounds(510, 330, 150, 60);
		getContentPane().add(integrationButton);

	}

	public String getResult() {
		return resultLabel.getText().toString();
	}

	public void setResult(String result) {
		resultLabel.setText(result);
	}

	public void setOperandOne(String operandOne) {
		operandOneTextField.setText(operandOne);
	}

	public String getOperandOne() {
		return operandOneTextField.getText().toString();
	}

	public void setOperandTwo(String operandTwo) {
		operandTwoTextField.setText(operandTwo);
	}

	public String getOperandTwo() {
		return operandTwoTextField.getText().toString();
	}

	public void addAdditionButtonActionListener(final ActionListener actionListener) {
		additionButton.addActionListener(actionListener);
	}

	public void addSubstractionButtonActionListener(final ActionListener actionListener) {
		substractionButton.addActionListener(actionListener);
	}

	public void addMultiplicationButtonActionListener(final ActionListener actionListener) {
		multiplicationButton.addActionListener(actionListener);
	}

	public void addDivisionButtonActionListener(final ActionListener actionListener) {
		divisionButton.addActionListener(actionListener);
	}

	public void addDerivationButtonActionListener(final ActionListener actionListener) {
		derivationButton.addActionListener(actionListener);
	}

	public void addIntegrationButtonActionListener(final ActionListener actionListener) {
		integrationButton.addActionListener(actionListener);

	}
}
