package edu.iastate.cs228.hw01;

/**
 * @author Lucas Keller
 * 
 * 
 */

public class Triangle extends GeometricObject {
	/** For tracking side 1 */
	private double side1;

	/** for tracking side 2 */
	private double side2;

	/** for tracking side 3 */
	private double side3;

	/**
	 * Constructs a triangle with 3 default sides of 1
	 */
	protected Triangle() {
		side1 = 1;
		side2 = 1;
		side3 = 1;

	}

	/**
	 * Constructs a triangle with three sides of user-input length.
	 * 
	 * @param side1
	 *            the length of side 1
	 * @param side2
	 *            the length of side 2
	 * @param side3
	 *            the length of side 3
	 */
	protected Triangle(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	/**
	 * For getting length of sides
	 * 
	 * @return side1 of triangle
	 */
	public double getSide1() {
		return side1;
	}

	/**
	 * For getting length of side
	 * 
	 * @return side2 of triangle
	 */
	public double getSide2() {
		return side2;
	}

	/**
	 * For getting length of side
	 * 
	 * @return side3 of triangle
	 */
	public double getSide3() {
		return side3;
	}

	/**
	 * Getter method for area
	 * 
	 * @return area of triangle
	 */
	public double getArea() {
		double s = (side1 + side2 + side3)/2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

	/**
	 * Getter method for area
	 * 
	 * @return area of triangle
	 */
	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	@Override
	public String toString() {
		return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
	}
}
