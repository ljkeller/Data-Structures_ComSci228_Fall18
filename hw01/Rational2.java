package edu.iastate.cs228.hw01;

import java.math.BigInteger;

/**
 * @author Lucas Keller
 * 
 *         Changes applied for cs228.hw01: => The original equals method
 *         implementation is changed. => Removed one of the methods. => Check:
 *         https://www.mathsisfun.com/algebra/Rational2-numbers-operations.html
 *         https://www.mathsisfun.com/greatest-common-factor.html
 */

/*
 * Rational2.java: Define a Rational2 number and its associated operations such
 * as add, subtract, multiply, and divide.
 */

@SuppressWarnings("serial")
public class Rational2 extends Number implements Comparable<Rational2> {
	// Data fields for numerator and denominator
	private BigInteger numerator;
	private BigInteger denominator;

	/** Default constructor */
	public Rational2() {
		this(BigInteger.ZERO, BigInteger.ONE);
	}

	/** Construct a Rational2 with specified numerator and denominator */
	public Rational2(BigInteger numerator, BigInteger denominator) {
		BigInteger gcd = gcd(numerator, denominator);
		// If denominator is greater than or equal to zero, multiples numerator by 1.
		// Otherwise, multiplies numerator by negative one (only numerator can be neg)
		this.numerator = ((denominator.compareTo(BigInteger.ONE)) >= 0 ? numerator.multiply(BigInteger.ONE)
						: numerator.multiply(new BigInteger("-1")))
				.divide(gcd);
		this.denominator = denominator.abs().divide(gcd);
	}

	/** Find GCD of two numbers */
	private BigInteger gcd(BigInteger n, BigInteger d) {
		return n.gcd(d);

	}

	/** Return numerator */
	public BigInteger getNumerator() {
		return numerator;
	}

	/** Return denominator */
	public BigInteger getDenominator() {
		return denominator;
	}

	/** Add a Rational2 number to this Rational2 */
	public Rational2 add(Rational2 secondRational2) {
		BigInteger n = numerator.multiply(secondRational2.getDenominator())
				.add(denominator.multiply(secondRational2.getNumerator()));
		BigInteger d = denominator.multiply(secondRational2.getDenominator());
		return new Rational2(n, d);
	}

	/** Subtract a Rational2 number from this Rational2 */
	public Rational2 subtract(Rational2 secondRational2) {
		BigInteger n = numerator.multiply(secondRational2.getDenominator())
				.subtract(denominator.multiply(secondRational2.getNumerator()));
		BigInteger d = denominator.multiply(secondRational2.getDenominator());
		return new Rational2(n, d);
	}

	/** Multiply a Rational2 number to this Rational2 */
	public Rational2 multiply(Rational2 secondRational2) {
		BigInteger n = numerator.multiply(secondRational2.getNumerator());
		BigInteger d = denominator.multiply(secondRational2.getDenominator());
		return new Rational2(n, d);
	}

	/** Divide a Rational2 number from this Rational2 */
	public Rational2 divide(Rational2 secondRational2) {
		BigInteger n = numerator.multiply(secondRational2.getDenominator());
		BigInteger d = denominator.multiply(secondRational2.numerator);
		return new Rational2(n, d);
	}

	@Override
	public String toString() {
		if (denominator.compareTo(BigInteger.ONE) == 0)
			return numerator + "";
		else
			return numerator + "/" + denominator;
	}

	/** Override the equals method in the Object class */
	public boolean equals(Object parm1) {
		if (this == parm1)
			return true;
		if ((parm1 == null) || (parm1.getClass() != this.getClass()))
			return false;

		if ((this.subtract((Rational2) (parm1))).getNumerator().equals(BigInteger.ZERO))
			return true;
		else
			return false;
	}

	/** Override the abstract intValue method in java.lang.Number */
	public int intValue() {
		return (int) doubleValue();
	}

	/** Override the abstract floatValue method in java.lang.Number */
	public float floatValue() {
		return (float) doubleValue();
	}

	/** Override the doubleValue method in java.lang.Number */
	public double doubleValue() {
		return numerator.doubleValue() / denominator.doubleValue();
	}

	/** Override the abstract longValue method in java.lang.Number */
	public long longValue() {
		return (long) doubleValue();
	}

	@Override
	public int compareTo(Rational2 o) {
		if (this.subtract(o).getNumerator().compareTo(BigInteger.ONE) > 0)
			return 1;
		else if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) < 0)
			return -1;
		else
			return 0;
	}
}
