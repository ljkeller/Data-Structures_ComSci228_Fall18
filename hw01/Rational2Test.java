package edu.iastate.cs228.hw01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

/**
 * Test the Rational2 class.
 * 
 * @author Kyle J Vetsch
 * @version 1.0
 */
public class Rational2Test {
	/** Expect negligible difference when comparing floating point numbers. */
	static final double DELTA = 1e-18;

	/** Used throughout tests. */
	private static final BigInteger A = new BigInteger("3123121");
	private static final BigInteger B = new BigInteger("1239023");

	/** Fraction of C/D can be reduced. */
	private static final BigInteger C = new BigInteger("18193314");
	private static final BigInteger D = new BigInteger("31830");

	/** Numerator and denominator of C/D. */
	private static final BigInteger SC = new BigInteger("3032219");
	private static final BigInteger SD = new BigInteger("5305");

	/**
	 * Test empty constructor.
	 */
	@Test
	public void emptyRational2() {
		final Rational2 r = new Rational2();

		assertEquals(BigInteger.ZERO, r.getNumerator());
		assertEquals(BigInteger.ONE, r.getDenominator());
	}

	/**
	 * Test constructor with fields. Also tests getNumerator and getDenominator.
	 */
	@Test
	public void newRational2() {
		final Rational2 r = new Rational2(A, B);

		assertEquals(A, r.getNumerator());
		assertEquals(B, r.getDenominator());
	}

	/**
	 * Test reduction of rational in constructor. This also tests the internal gcd
	 * call.
	 */
	@Test
	public void reductRational2() {
		final Rational2 r = new Rational2(C, D);

		assertEquals(SC, r.getNumerator());
		assertEquals(SD, r.getDenominator());
	}

	/**
	 * Test addition of rationals.
	 */
	@Test
	public void add() {
		final Rational2 rA = new Rational2(A, B);
		final Rational2 rB = new Rational2(C, D);

		final Rational2 sum = rA.add(rB);

		// Result, once rational is simplified
		final BigInteger n = new BigInteger("3773557238942");
		final BigInteger d = new BigInteger("6573017015");

		assertEquals(n, sum.getNumerator());
		assertEquals(d, sum.getDenominator());
	}

	/**
	 * Test subtraction of rationals.
	 */
	@Test
	public void subtract() {
		final Rational2 rA = new Rational2(A, B);
		final Rational2 rB = new Rational2(C, D);

		final Rational2 diff = rA.subtract(rB);

		final BigInteger n = new BigInteger("-3740420925132");
		final BigInteger d = new BigInteger("6573017015");

		assertEquals(n, diff.getNumerator());
		assertEquals(d, diff.getDenominator());
	}

	/**
	 * Test multiplication of rationals.
	 */
	@Test
	public void multiply() {
		final Rational2 rA = new Rational2(A, B);
		final Rational2 rB = new Rational2(C, D);

		final Rational2 prod = rA.multiply(rB);

		final BigInteger n = new BigInteger("9469986835499");
		final BigInteger d = new BigInteger("6573017015");

		assertEquals(n, prod.getNumerator());
		assertEquals(d, prod.getDenominator());
	}

	/**
	 * Test division of rationals.
	 */
	@Test
	public void divide() {
		final Rational2 rA = new Rational2(A, B);
		final Rational2 rB = new Rational2(C, D);

		final Rational2 ratio = rA.divide(rB);

		final BigInteger n = new BigInteger("16568156905");
		final BigInteger d = new BigInteger("3756989082037");

		assertEquals(n, ratio.getNumerator());
		assertEquals(d, ratio.getDenominator());
	}

	/**
	 * Converting to string.
	 */
	@Test
	public void testToString() {
		final Rational2 rA = new Rational2(A, BigInteger.ONE);
		final Rational2 rB = new Rational2(A, B);

		assertEquals("3123121", rA.toString());
		assertEquals("3123121/1239023", rB.toString());
	}

	/**
	 * Comparing rational objects.
	 */
	@Test
	public void equals() {
		final Rational2 rA = new Rational2(A, B);
		final Rational2 rB = new Rational2(A, B);

		final Rational2 rC = rA;

		final Rational2 rD = new Rational2(new BigInteger("3123121"), new BigInteger("1239023"));

		final Rational2 rE = null;
		final Integer rF = new Integer(1);

		assertTrue(rA.equals(rB));
		assertTrue(rA.equals(rC));
		assertTrue(rA.equals(rD));

		assertFalse(rA.equals(rE));
		assertFalse(rA.equals(rF));
	}

	/**
	 * Get values in different types.
	 */
	@Test
	public void values() {
		final Rational2 r = new Rational2(A, B);

		assertEquals(2, r.intValue());

		assertEquals(2.5206319818114755, r.doubleValue(), DELTA);

		final double floatDelta = 1e-6;

		assertEquals(2.520632, r.floatValue(), floatDelta);

		assertEquals(2, r.longValue());
	}

	/**
	 * Spaceship comparison without the operator.
	 */
	@Test
	public void compareTo() {
		final Rational2 rA = new Rational2(A, B);
		final Rational2 rB = new Rational2(C, D);
		final Rational2 rC = new Rational2(new BigInteger("9369363"), new BigInteger("3717069"));

		assertEquals(-1, rA.compareTo(rB));
		assertEquals(1, rB.compareTo(rA));
		assertEquals(0, rA.compareTo(rC));
	}
}