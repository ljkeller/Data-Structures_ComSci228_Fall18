package edu.iastate.cs228.hw03;

/**
 * 
 * @author Lucas Keller
 *
 */
public class HW03_Part2 {
	/*
	 * Answers to short questions:
	 * 
	 * 1. O(n) is the upper bound because even though the function counts by 2, it
	 * is counting linearly
	 * 
	 * 2. O(log(n))
	 * 
	 * 3. O(n^2) due to two for loops based on n dataset each iterating by one.
	 * 
	 * 4. O(n^2)
	 * 
	 * 5. O(n^2)
	 * 
	 */

	/*
	 * In all of the following methods you can assume that array will always have
	 * elements (ints) in it. And will have proper integers as defined in the
	 * description of HW03, i.e., in first two it will be in the range, and in last
	 * two it will be composed of negative and positive values only.
	 */

	public static int findMissingInt_a_On2(int[] array) {
		boolean flag = false;
		int tracker = -1;
		for (int i = 1; i <= array.length + 1; i++) {
			tracker = i;
			flag = false;
			for (int k = 0; k < array.length; k++) {
				if (i == array[k])
					flag = true;
			}
			if (flag == false) {
				tracker = i;
				break;
			}
		}
		return tracker;
	}

	public static int findMissingInt_b_On1(int[] array) {
		int actual = 0;
		int theoretical = ((array.length + 1) * (array.length + 2)) / 2;
		for (int i = 0; i < array.length; i++) {
			actual += array[i];
		}

		return theoretical - actual;

	}

	public static void rearrange_a_On2(int[] array) {
		int temp = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0) {
				for (int k = 0; k < array.length; k++) {
					if (array[k] > 0) {
						temp = array[i];
						array[i] = array[k];
						array[k] = temp;
					}
				}
			}
		}
	}

	public static void rearrange_b_On1(int[] array) {
		int[] tempArray = new int[array.length];

		int ascendingCounter = 0;
		int descendingCounter = array.length - 1;

		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0) {
				tempArray[ascendingCounter] = array[i];
				ascendingCounter++;
			}
			if (array[i] > 0) {
				tempArray[descendingCounter] = array[i];
				descendingCounter--;
			}
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = tempArray[i];
		}
	}

}
