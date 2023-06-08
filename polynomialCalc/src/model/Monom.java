package model;

public class Monom {

	private int coefficient;
	private int power;

	public Monom(int coefficient, int power) {
		super();
		this.coefficient = coefficient;
		this.power = power;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getMonomString() {

		StringBuilder s = new StringBuilder();

		if (coefficient == 0) {
			return "";
		} else {
			return s.append("+ ").append(coefficient).append("x^").append(power).append(" ").toString();
		}
	}

	public void displayMonom() {

		if (coefficient > 0) {
			System.out.print("+" + coefficient + "x^" + power + " ");
		} else if (coefficient < 0) {
			System.out.print(coefficient + "x^" + power + " ");
		} else {
			System.out.print("+" + 0 + " ");
		}
	}

}
