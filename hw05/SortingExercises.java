package edu.iastate.cs228.hw05;

/**
 * 
 * @author Lucas Keller
 * 
 *         NOTE: 0. Put your Firstname and Lastname after above author tag. Make
 *         sure that in both cases the first letter is uppercase and all others
 *         are lowercase. 1. In all of these methods implementations you are
 *         allowed to use the StringBuilder class. 2. You are allowed to create
 *         and use your own private helper methods. 3. No data fields can be
 *         introduced. 4. No custom classes of your own can be introduced or
 *         used. 5. Import statements are not allowed. 6. Fully qualified class
 *         names usage is not allowed. 7. You are allowed to reuse any part of
 *         the source codes provided or shown under lecture notes.
 * 
 */

public class SortingExercises {
	/**
	 * Recursive implementation of selection sort.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void selectionSort_Rec(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		else {
			int minLocation = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[minLocation] > arr[i])
					minLocation = i;
			}
			if (minLocation > 0) {
				int temp = arr[minLocation];
				arr[minLocation] = arr[0];
				arr[0] = temp;
			}
			selectionSort_Rec(arr, 1);
		}

	}

	/**
	 * Overloaded selection sort recursive method that operates on original array,
	 * but runs through from startPoint. Recursively calls itself to go through
	 * list, reducing length each time
	 * 
	 * @param arr
	 * 			given array to manipulate
	 * @param startPoint
	 * 			starting point of comparation, reducing each time
	 */
	public static void selectionSort_Rec(int[] arr, int startPoint) {
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (startPoint == arr.length) // have gone all the way through
			return;
		else {
			int minIndex = startPoint;
			for (int i = startPoint; i < arr.length; i++) {
				if (arr[minIndex] > arr[i])
					minIndex = i;
			}
			if (minIndex > startPoint) {
				int temp = arr[startPoint];
				arr[startPoint] = arr[minIndex];
				arr[minIndex] = temp;
			}
			selectionSort_Rec(arr, 1 + startPoint);
		}
	}

	/**
	 * Recursive implementation of insertion sort.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void insertionSort_Rec(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;

		insertionSort_Rec(arr, arr[1], 0); // Overloaded helper
	}

	/**
	 * Overloaded helper that compares value to array elements before it, shifting
	 * them when needed
	 * 
	 * @param arr
	 *            the given array to be manipulated
	 * @param value
	 *            the value to compare to elements before it
	 * @param startPoint
	 *            the starting point, decreasing, comparing value to array elements
	 */
	public static void insertionSort_Rec(int[] arr, int value, int startPoint) {
		if (startPoint == arr.length)
			return;
		else {
			int counter = startPoint;
			int temp = arr[startPoint + 1];
			while (counter >= 0 && arr[counter] > value) {
				arr[counter + 1] = arr[counter];
				counter--;
			}
			arr[counter + 1] = temp;
			if (arr.length - startPoint > 2)
				insertionSort_Rec(arr, arr[startPoint + 2], startPoint + 1);

		}
	}

	/**
	 * Iterative implementation of selection sort with modifications as follows. On
	 * each pass in this case the method finds both the largest and smallest values
	 * in the unsorted portion of the array, and places them in the correct
	 * locations.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void selectionSort_Itr(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		else {
			int minLocation = 0;
			int maxLocation = 0;
			int adjustedLength = arr.length;
			for (int i = 0; i < adjustedLength && adjustedLength - i > 0; i++) {

				minLocation = i;
				maxLocation = i;
				for (int k = i + 1; k < adjustedLength; k++) {
					if (arr[minLocation] > arr[k])
						minLocation = k;
					if (arr[maxLocation] < arr[k])
						maxLocation = k;
				}
				int endTemp = arr[adjustedLength - 1];
				int minTemp = arr[i];
				// Does swaps, but must check to see if overlap
				// between end points
				arr[adjustedLength - 1] = arr[maxLocation];
				arr[i] = arr[minLocation];
				if (maxLocation != i)
					arr[maxLocation] = endTemp;
				if (minLocation != adjustedLength)
					arr[minLocation] = minTemp;
			}
		}
	}

	/**
	 * A bubble sort can sort an array of n entries into ascending order by makeing
	 * n-1 passes through the array. On each pass, it compares adjacent entries and
	 * swaps them if they are out or order. For example, on the first pass, it
	 * compares the first and second entries, then the second and third entries, and
	 * so on. At the end of the first pass, the largest entry is in its proper
	 * position at the end of the array. We say that it has bubbled to its correct
	 * spot. Each subsequent pass ignores the entries at the end of the array, since
	 * they are sorted and are larger than any of the remaining entries. Thus, each
	 * pass makes one fewer comparison than the previous pass. Check the figure
	 * under HW05 assignment on Canvas.
	 * 
	 * This method implements bubble sort iteratively.
	 * 
	 * @param arr
	 *            Array of objects (with specific bounds) to be sorted in
	 *            nondecreasing order.
	 */
	public static <T extends Comparable<? super T>> void bubbleSort_Itr(T[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;

		for (int i = arr.length; i >= 0; i--) {
			for (int k = 0; k < i - 1; k++) {
				if (arr[k].compareTo(arr[k + 1]) > 0) {
					T temp = arr[k + 1];
					arr[k + 1] = arr[k];
					arr[k] = temp;
				}
			}
		}
	}

	/**
	 * A bubble sort can sort an array of n entries into ascending order by makeing
	 * n-1 passes through the array. On each pass, it compares adjacent entries and
	 * swaps them if they are out or order. For example, on the first pass, it
	 * compares the first and second entries, then the second and third entries, and
	 * so on. At the end of the first pass, the largest entry is in its proper
	 * position at the end of the array. We say that it has bubbled to its correct
	 * spot. Each subsequent pass ignores the entries at the end of the array, since
	 * they are sorted and are larger than any of the remaining entries. Thus, each
	 * pass makes one fewer comparison than the previous pass. Check the figure
	 * under HW05 assignment on Canvas.
	 * 
	 * This method implements bubble sort recursively.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void bubbleSort_Rec(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;

		bubbleSort_Rec(arr, arr.length - 1);
	}

	/**
	 * Overloaded bubblesort method that iterates through to given endPoint, then
	 * recursively calls
	 * 
	 * @param arr
	 *            given array to manipulate
	 * @param endPoint
	 *            end point of current iteration/run
	 */
	public static void bubbleSort_Rec(int[] arr, int endPoint) {
		if (endPoint == 0)
			return;
		for (int i = 0; i < endPoint; i++) {
			if (arr[i] > arr[i + 1]) {
				int temp = arr[i + 1];
				arr[i + 1] = arr[i];
				arr[i] = temp;
			}
		}
		bubbleSort_Rec(arr, endPoint - 1);
	}
}
