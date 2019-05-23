package edu.iastate.cs228.hw01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test the triangle class.
 * 
 * @author Kyle Vetsch
 * @version 1.0
 */
public class TriangleTest {
	/** Expect negligible difference when comparing floating point numbers. */
	static final double DELTA = 1e-8;

	/** Verify empty constructor creates a unit triangle. */
	@Test
	public void unitTriangle() {
		Triangle t = new Triangle();

		// valid properties of a unit triangle
		assertEquals(1.0, t.getSide1(), DELTA);
		assertEquals(1.0, t.getSide2(), DELTA);
		assertEquals(1.0, t.getSide3(), DELTA);
	}

	/** Check getting first side length. */
	@Test
	public void getSide1() {
		Triangle t = new Triangle(1.3, 1.4, 1.5);
		assertEquals(1.3, t.getSide1(), DELTA);
	}

	/** Check getting second side length. */
	@Test
	public void getSide2() {
		Triangle t = new Triangle(1.3, 1.4, 1.5);
		assertEquals(1.4, t.getSide2(), DELTA);
	}

	/** Check getting third side length. */
	@Test
	public void getSide3() {
		Triangle t = new Triangle(1.3, 1.4, 1.5);
		assertEquals(1.5, t.getSide3(), DELTA);
	}

	/** Check getting triangle's area. */
	@Test
	public void getArea() {
		Triangle t = new Triangle(1.3, 1.4, 1.5);
		assertEquals(0.84, t.getArea(), DELTA);
	}

	/** Check getting triangle's perimeter. */
	@Test
	public void getPerimeter() {
		Triangle t = new Triangle(1.3, 1.4, 1.5);
		assertEquals(4.2, t.getPerimeter(), DELTA);
	}
}