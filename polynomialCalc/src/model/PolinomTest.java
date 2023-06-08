package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PolinomTest {

	@Test
	void test() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();

		Monom m1 = new Monom(1, 2);
		Monom m2 = new Monom(-1, 0);

		p1.getPolinom().add(m1);
		p1.getPolinom().add(m2);

		Monom m4 = new Monom(1, 3);
		Monom m5 = new Monom(-2, 2);
		Monom m6 = new Monom(6, 1);
		Monom m7 = new Monom(-5, 0);

		p2.getPolinom().add(m4);
		p2.getPolinom().add(m5);
		p2.getPolinom().add(m6);
		p2.getPolinom().add(m7);
		
		Polinom sumPolinom = new Polinom();
		sumPolinom=sumPolinom.sum(p1,p2);
		System.out.println("ADDITION: ");
		System.out.println("Sum: ");
		sumPolinom.displayPolinom(sumPolinom);
		assertTrue(sumPolinom.getPolinomString().equals("+ 1x^3 + -1x^2 + 6x^1 + -6x^0 "));
		System.out.println(" ");
		
		Polinom diffPolinom = new Polinom();
		diffPolinom=diffPolinom.dif(p1,p2);
		System.out.println("SUBTRACTION: ");
		System.out.println("Diff: ");
		diffPolinom.displayPolinom(diffPolinom);
		assertTrue(diffPolinom.getPolinomString().equals("+ -1x^3 + 3x^2 + -6x^1 + 4x^0 "));
		System.out.println(" ");

		
		Polinom mulPolinom = new Polinom();
		mulPolinom=mulPolinom.multiplication(p1,p2);
		System.out.println("MULTIPLICATION: ");
		System.out.println("Multiply: ");
		mulPolinom.displayPolinom(mulPolinom);
		assertTrue(mulPolinom.getPolinomString().equals("+ 1x^5 + -2x^4 + 5x^3 + -3x^2 + -6x^1 + 5x^0 "));
		System.out.println(" ");

		
		Polinom quotient = new Polinom();
		Polinom remainder = new Polinom();
		
		quotient= p1.division(p2,p1)[0];
		remainder=p1.division(p2,p1)[1];
		
		System.out.println("DIVISION :");
		System.out.print("Quotient : ");
		quotient.displayPolinom(quotient);
		assertTrue(quotient.getPolinomString().equals("+ 1x^1 + -2x^0 "));
		System.out.print("Remainder : ");
		remainder.displayPolinom(remainder);
		assertTrue(remainder.getPolinomString().equals("+ 7x^1 + -7x^0 "));
		System.out.println("");
		
		Polinom deriv = new Polinom();
		deriv=deriv.derivata(p2);
		System.out.println("DERIVATION : ");
		System.out.print("Deriv : ");
		deriv.displayPolinom(deriv);
		assertTrue(deriv.getPolinomString().equals("+ 3x^2 + -4x^1 + 6x^0 "));
		System.out.println("");
		

	}

}
