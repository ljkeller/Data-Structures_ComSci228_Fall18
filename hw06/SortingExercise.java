package edu.iastate.cs228.hw06;

/**
 * 
 * @author Lucas Keller
 * 
 *         NOTE:
 * 
 *         0. Put your Firstname and Lastname after above author tag. Make sure
 *         that in both cases the first letter is uppercase and all others are
 *         lowercase. 1. You are allowed to create and use your own private
 *         helper methods. 2. No data fields can be introduced. 3. No custom
 *         classes of your own can be introduced or used. 4. Import statements
 *         are not allowed. 5. Fully qualified class names usage is not allowed.
 *         6. You are allowed to reuse any part of the source codes provided or
 *         shown under lecture notes, which do not violate any of above.
 * 
 * 
 * 
 */

public class SortingExercise {
	/**
	 * Modified implementation of in class provided quick sort code.
	 * 
	 * 
	 * The implementation of our original quick sort needs to be revised as follows
	 * in this implementation. If the array has 23 entries, choose the middle entry
	 * as the pivot. For arrays between 24 - 50 use the last element as the pivot
	 * value. For arrays larger than 50 entries, use the median-of-three
	 * pivot-selection scheme described below. For arrays fewer than 23 entries, use
	 * insertion sort instead of quick sort.
	 * 
	 * Median-of-three pivot selection chooses as pivot the median of three entries
	 * in the array, i.e., the first entry, the middle entry, and the last entry. We
	 * will use specific version of it as follows.
	 * 
	 * For example, let's say original array is as follows
	 * 
	 * 5, 8, 6, 4, 9, 3, 7, 1, 2
	 * 
	 * first entry = 5 middle entry = 9 // index is (0+8)/2=4 last entry = 2
	 * 
	 * Median of 5, 9, 2, would be 5. Check: https://en.wikipedia.org/wiki/Median
	 * 
	 * Now our array would look as follows after positioning the pivot:
	 * 
	 * 2, 8, 6, 4, 5, 3, 7, 1, 9
	 * 
	 * Now our array would look as follows just before partitioning:
	 * 
	 * 2, 5, 6, 4, 8, 3, 7, 1, 9
	 * 
	 * Our pivot is at position 1 of array, i.e., value 5. Both low and high start
	 * as shown in source code of quick sort under lecture notes, i.e.,
	 * 
	 * int low = first + 1; int high = last;
	 * 
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void modifiedQuickSort(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		if (arr.length < 23) {
			insertionSort(arr, 0, arr.length - 1);
		} else
			modifiedQuickSort(arr, 0, arr.length - 1);
	}

	public static void modifiedQuickSort(int[] arr, int startPoint, int endPoint) {
		if(endPoint + 1 - startPoint < 23) {//if length is 22 or less
			insertionSort(arr, startPoint, endPoint);
			return;
		}
		if (endPoint + 1 - startPoint >= 23 && endPoint > startPoint) {// while length is 23 or greater
			int pivotIndex = partition(arr, startPoint, endPoint);
			modifiedQuickSort(arr, startPoint, pivotIndex - 1);
			modifiedQuickSort(arr, pivotIndex + 1, endPoint);
		}
		
		
	}

	private static int partition(int[] arr, int startPoint, int endPoint) {
		int pivot;
		if (endPoint + 1 - startPoint == 23) {
			pivot = arr[(endPoint + startPoint) / 2];
			arr[(endPoint + startPoint) / 2] = arr[startPoint];
			arr[startPoint] = pivot;
		} else if (endPoint + 1 - startPoint > 50) {
			int medianIndex = getMedianIndex(arr, startPoint, endPoint);
			pivot = arr[medianIndex];
			arr[medianIndex] = arr[startPoint];
			arr[startPoint] = pivot;
		} else { // range from 23 to 50
			pivot = arr[endPoint];
			arr[endPoint] = arr[startPoint];
			arr[startPoint] = pivot;
		}

		int low = startPoint + 1;
		int high = endPoint;

		while (low < high) {

			while (low <= high && arr[low] <= pivot)
				low++;

			while (low <= high && arr[high] > pivot)
				high--;

			if (high > low) {
				int temp = arr[high];
				arr[high] = arr[low];
				arr[low] = temp;
			}
		}

		while (high > startPoint && arr[high] >= pivot)
			high--;

		if (pivot > arr[high]) {
			arr[startPoint] = arr[high];
			arr[high] = pivot;
			return high;
		} else
			return startPoint;
	}

	private static int getMedianIndex(int[] arr, int startIndex, int endIndex) {
		int[] possibleMedians = new int[] { arr[startIndex], arr[(endIndex - startIndex) / 2], arr[endIndex] };
		for (int i = 0; i < 2; i++) {
			int minIndex = i;
			for (int k = i + 1; k < 3; k++) {
				if (possibleMedians[minIndex] > possibleMedians[k])
					minIndex = k;
			}
			int temp = possibleMedians[minIndex];
			possibleMedians[minIndex] = possibleMedians[i];
			possibleMedians[i] = temp;
		}
		if (arr[startIndex] == possibleMedians[1])
			return startIndex;
		if (arr[endIndex] == possibleMedians[1])
			return endIndex;
		else
			return (endIndex - startIndex) / 2;
	}

//	public static void main(String[] args) {
//		 int[] test10 = new int[] { 3, 7, -1, 16, 8, 0, 2, 7, 6, 10 };
//		 modifiedQuickSort(test10);
//		 for (int i : test10)
//		 System.out.println(i);

//		 System.out.println(getMedianIndex(new int[] {5, 8, 6, 4, 1, 3, 7, 1, 2}, 0,
//		 8));

//		int[] test23 = new int[] {9, 7, 6, 5, 4, 3, 2, 5,6,7,6,5,4,5,6,7,6,5,4,5,6,5,4};
//		modifiedQuickSort(test23);
//		for(int i : test23)
//			System.out.println(i);
	
//		int[] test50 = new int[] { 80, 83, 75, 92, 41, 24, 30, 53, 77, 1, 10, 80, 46, 63, 2, 82, 46, 29, 57, 88, 31, 25,
//				36, 29, 35, 28, 64, 52, 42, 4, 69, 66, 68, 78, 10, 50, 82, 67, 82, 50, 16, 96, 76, 64, 35, 63, 54, 53,
//				59, 100, 17 };
//		modifiedQuickSort(test50);
//		for (int i : test50)
//			System.out.println(i);
//	}

	private static void insertionSort(int[] arr, int startPoint, int endPoint) {
		for (int i = startPoint + 1; i <= endPoint; i++) {
			int temp = arr[i];
			int k = i - 1;
			while (k >= startPoint && arr[k] > temp) {
				arr[k + 1] = arr[k];
				k--;
			}
			arr[k + 1] = temp;
		}
	}
}
