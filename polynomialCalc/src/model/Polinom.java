package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Polinom {

	private ArrayList<Monom> polinom = new ArrayList<>();

	public ArrayList<Monom> getPolinom() {
		return polinom;
	}

	public void setPolinom(ArrayList<Monom> polinom) {
		this.polinom = polinom;
	}

	public void degreeSort() {

		Collections.sort(polinom, new Comparator<Monom>() {

			@Override
			public int compare(Monom m1, Monom m2) {
				if (m1.getPower() > m2.getPower())
					return -1;
				else if (m1.getPower() == m2.getPower())
					return 0;
				else
					return 1;
			}
		});
	}

	public Polinom sum(Polinom p1, Polinom p2) {

		Polinom result = new Polinom();
		int ok;
		for (Monom m1 : p1.polinom) {
			ok = 0;
			for (Monom m2 : p2.polinom) {
				if (m1.getPower() == m2.getPower()) {
					ok = 1;
					if (m1.getCoefficient() + m2.getCoefficient() != 0) {
						Monom r = new Monom(m1.getCoefficient() + m2.getCoefficient(), m1.getPower());
						result.polinom.add(r);
					}
				}

			}
			if (ok == 0) {
				Monom restP1 = new Monom(m1.getCoefficient(), m1.getPower());
				result.polinom.add(restP1);
			}

		}
		for (Monom m2 : p2.polinom) {
			ok = 0;
			for (Monom m1 : p1.polinom) {
				if (m2.getPower() == m1.getPower()) {
					ok = 1;
				}
			}
			if (ok == 0) {
				Monom restP2 = new Monom(m2.getCoefficient(), m2.getPower());
				result.polinom.add(restP2);
			}
		}

		result.degreeSort();
		return result;

	}

	public Polinom dif(Polinom p1, Polinom p2) {

		Polinom result = new Polinom();
		int ok;
		for (Monom m1 : p1.polinom) {
			ok = 0;
			for (Monom m2 : p2.polinom) {
				if (m1.getPower() == m2.getPower()) {
					ok = 1;
					if (m1.getCoefficient() - m2.getCoefficient() != 0) {
						Monom r = new Monom(m1.getCoefficient() - m2.getCoefficient(), m1.getPower());
						result.polinom.add(r);
					}
				}

			}
			if (ok == 0) {
				Monom restP1 = new Monom(m1.getCoefficient(), m1.getPower());
				result.polinom.add(restP1);
			}

		}
		for (Monom m2 : p2.polinom) {
			ok = 0;
			for (Monom m1 : p1.polinom) {
				if (m2.getPower() == m1.getPower()) {
					ok = 1;
				}
			}
			if (ok == 0) {
				Monom restP2 = new Monom(-m2.getCoefficient(), m2.getPower());
				result.polinom.add(restP2);
			}
		}

		result.degreeSort();
		return result;

	}

	public Polinom derivata(Polinom p1) {
		Polinom result = new Polinom();
		int newCoefficient;

		for (Monom m : p1.polinom) {

			if (m.getPower() > 0) {
				newCoefficient = m.getCoefficient() * m.getPower();
				Monom resultedMonom = new Monom(newCoefficient, m.getPower() - 1);
				result.polinom.add(resultedMonom);

			}
		}
		result.degreeSort();
		return result;

	}

	public Polinom multiplication(Polinom p1, Polinom p2) {
		Polinom result = new Polinom();
		Polinom tempResult = new Polinom();

		for (Monom m1 : p1.polinom) {
			for (Monom m2 : p2.polinom) {
				Monom prod = new Monom(m1.getCoefficient() * m2.getCoefficient(), m1.getPower() + m2.getPower());
				tempResult.polinom.add(prod);
			}
		}
		tempResult.degreeSort();
		int maxDegree;
		int sum;
		maxDegree = tempResult.polinom.get(0).getPower();
		while (maxDegree >= 0) {
			sum = 0;
			for (Monom m : tempResult.polinom) {

				if (m.getPower() == maxDegree) {
					sum = sum + m.getCoefficient();
				}
			}
			if (sum != 0) {
				Monom finalTerm = new Monom(sum, maxDegree);
				result.polinom.add(finalTerm);
			}
			maxDegree--;
		}

		result.degreeSort();
		return result;
	}

	public Polinom[] division(Polinom p1, Polinom p2) {

		Polinom remainder = new Polinom();
		Polinom quotient = new Polinom();
		Polinom finalResult[] = new Polinom[2];

		p1.degreeSort();
		p2.degreeSort();

		Monom quotientFirstTerm = new Monom(p1.polinom.get(0).getCoefficient() / p2.polinom.get(0).getCoefficient(),
				p1.polinom.get(0).getPower() - p2.polinom.get(0).getPower());
		quotient.polinom.add(quotientFirstTerm);
		remainder = multiplication(quotient, p2);
		for (Monom m : remainder.polinom) {
			m.setCoefficient(-m.getCoefficient());
		}

		remainder = sum(p1, remainder);

		while (remainder.polinom.get(0).getPower() > p2.polinom.get(0).getPower() - 1) {

			Monom quotientNewTerm = new Monom(
					remainder.polinom.get(0).getCoefficient() / p2.polinom.get(0).getCoefficient(),
					remainder.polinom.get(0).getPower() - p2.polinom.get(0).getPower());
			quotient.polinom.add(quotientNewTerm);
			remainder = multiplication(quotient, p2);
			for (Monom m : remainder.polinom) {
				m.setCoefficient(-m.getCoefficient());
			}
			remainder = sum(p1, remainder);

		}

		finalResult[0] = quotient;
		finalResult[1] = remainder;

		return finalResult;

	}

	public ArrayList<Double> integral(Polinom p) {

		ArrayList<Double> doubleCoefficients = new ArrayList<>();

		p.degreeSort();

		int maxPower = p.polinom.get(0).getPower();

		while (maxPower >= 0) {
			int ok;
			ok = 0;
			for (Monom m : polinom) {
				if (m.getPower() == maxPower) {
					ok = 1;
					if (maxPower != 0) {
						double b;
						b = m.getCoefficient() * 1.0 / (m.getPower() + 1);
						doubleCoefficients.add(b);
					} else {
						double c;
						c = m.getCoefficient();
						doubleCoefficients.add(c);
					}
				}

			}
			if (ok == 0) {
				doubleCoefficients.add(0.00);

			}
			maxPower--;
		}

		return doubleCoefficients;
	}

	public void displayPolinom(Polinom p) {

		for (Monom m : polinom) {
			m.displayMonom();
		}
	}

	public void displayIntegratedPolinom(Polinom p) {
		ArrayList<Double> doubleCoefficients = new ArrayList<>();

		doubleCoefficients = integral(p);
		int i;
		i = 0;

		for (Monom m : polinom) {

			if (doubleCoefficients.get(i) != 0.00) {
				System.out.println(doubleCoefficients.get(i) + "x^" + (m.getPower() + 1) + " ");

			}
			i++;

		}

	}

	public String getIntegratedPolinomString(Polinom p) {
		ArrayList<Double> doubleCoefficients = new ArrayList<>();
		doubleCoefficients = integral(p);
		StringBuilder s = new StringBuilder();
		int i = 0;
		for (Monom m : polinom) {
			if (doubleCoefficients.get(i) != 0.00) {
				s.append(" + ").append(doubleCoefficients.get(i)).append("x^").append(m.getPower() + 1).append(" ")
						.toString();

			}
			i++;

		}

		return s.toString();

	}

	public String getPolinomString() {
		StringBuilder s = new StringBuilder();
		for (Monom m : polinom) {
			s.append(m.getMonomString());
		}
		return s.toString();

	}
}
