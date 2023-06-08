package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Monom;
import model.Polinom;
import view.UserInterface;

public class AppControl {

	private UserInterface myInterface;

	private Polinom p1;
	private Polinom p2;

	public Polinom getP1() {
		return p1;
	}

	public void setP1(Polinom p1) {
		this.p1 = p1;
	}

	public Polinom getP2() {
		return p2;
	}

	public void setP2(Polinom p2) {
		this.p2 = p2;
	}

	public void start() {
		myInterface = new UserInterface();
		myInterface.setVisible(true);

		prepareButton();
	}

	public Polinom createPolinomFromInput(String stringField) {
		Polinom p = new Polinom();
		String pString = stringField;
		try {
			pString = pString.replaceAll("\\s", "");
			if (pString.charAt(0) == '+') {
				pString = pString.substring(1);
			}
			for (String s : pString.split("\\+")) {
				int coefficient;
				int power;
				int posX;
				int posPower;
				coefficient = 0;
				power = 0;
				posX = s.indexOf("x");
				posPower = s.indexOf("^");
				try {
					power = Integer.parseInt(s.substring(posPower + 1));
					coefficient = Integer.parseInt(s.substring(0, posX));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "The input doesn't respect the input form!!!");
					return null;
				}
				Monom m = new Monom(coefficient, power);
				p.getPolinom().add(m);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The input doesn't respect the input form!!!");
		}
		return p;
	}

	public void prepareButton() {

		myInterface.addAdditionButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom sumPolinom = new Polinom();

				try {
					p1 = new Polinom();
					p1 = createPolinomFromInput(myInterface.getOperandOne());
					System.out.println("\nPolinom 1: ");
					p1.displayPolinom(p1);
					System.out.println(" ");
					p2 = new Polinom();
					p2 = createPolinomFromInput(myInterface.getOperandTwo());
					System.out.println("Polinom 2: ");
					p2.displayPolinom(p2);
					System.out.println(" ");

					sumPolinom = p1.sum(p1, p2);
					myInterface.setResult(sumPolinom.getPolinomString());
					sumPolinom.displayPolinom(sumPolinom);
				} catch (Exception e5) {

				}

			}
		});

		myInterface.addSubstractionButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom differencePolinom = new Polinom();

				try {
					p1 = new Polinom();
					p1 = createPolinomFromInput(myInterface.getOperandOne());
					System.out.println("\nPolinom 1: ");
					p1.displayPolinom(p1);
					System.out.println(" ");
					p2 = new Polinom();
					p2 = createPolinomFromInput(myInterface.getOperandTwo());
					System.out.println("Polinom 2: ");
					p2.displayPolinom(p2);
					System.out.println(" ");
					differencePolinom = p1.dif(p1, p2);
					myInterface.setResult(differencePolinom.getPolinomString());
					differencePolinom.displayPolinom(differencePolinom);
				} catch (Exception e6) {

				}

			}
		});

		myInterface.addMultiplicationButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Polinom multipliedPolinom = new Polinom();

				try {
					p1 = new Polinom();
					p1 = createPolinomFromInput(myInterface.getOperandOne());
					System.out.println("\nPolinom 1: ");
					p1.displayPolinom(p1);
					System.out.println(" ");
					p2 = new Polinom();
					p2 = createPolinomFromInput(myInterface.getOperandTwo());
					System.out.println("Polinom 2: ");
					p2.displayPolinom(p2);
					System.out.println(" ");
					multipliedPolinom = p1.multiplication(p1, p2);
					myInterface.setResult(multipliedPolinom.getPolinomString());
					multipliedPolinom.displayPolinom(multipliedPolinom);
				} catch (Exception e7) {

				}

			}
		});

		myInterface.addDivisionButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Polinom quotient = new Polinom();
				Polinom remainder = new Polinom();
				try {
					p1 = new Polinom();
					p1 = createPolinomFromInput(myInterface.getOperandOne());
					System.out.println("\nPolinom 1: ");
					p1.displayPolinom(p1);
					System.out.println(" ");
					p2 = new Polinom();
					p2 = createPolinomFromInput(myInterface.getOperandTwo());
					System.out.println("Polinom 2: ");
					p2.displayPolinom(p2);
					System.out.println(" ");
					quotient = p1.division(p1, p2)[0];
					remainder = p1.division(p1, p2)[1];

					myInterface.setResult("Quotient: " + quotient.getPolinomString() + "         Remainder: " + remainder.getPolinomString());
					quotient.displayPolinom(quotient);
					remainder.displayPolinom(remainder);
				} catch (Exception e1) {

				}

			}
		});

		myInterface.addDerivationButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Polinom derivatedPolinom = new Polinom();

				try {
					p1 = new Polinom();
					p1 = createPolinomFromInput(myInterface.getOperandOne());
					System.out.println("\nPolinom 1: ");
					p1.displayPolinom(p1);
					System.out.println(" ");

					derivatedPolinom = p1.derivata(p1);

					myInterface.setResult(derivatedPolinom.getPolinomString());
					derivatedPolinom.displayPolinom(derivatedPolinom);

				} catch (Exception e7) {

				}

			}
		});

		myInterface.addIntegrationButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					p1 = new Polinom();
					p1 = createPolinomFromInput(myInterface.getOperandOne());
					System.out.println("\nPolinom 1: ");
					p1.displayPolinom(p1);
					System.out.println(" ");
					myInterface.setResult(p1.getIntegratedPolinomString(p1));
					p1.displayIntegratedPolinom(p1);
				} catch (Exception e8) {
				}
			}

		});

	}

}
