package edu.iastate.cs228.hw07;

import java.util.ArrayList;

/**
 * 
 * @author Lucas Keller
 * 
 *         NOTE:
 * 
 *         0. Put your Firstname and Lastname after above author tag. Make sure
 *         that in both cases the first letter is uppercase and all others are
 *         lowercase. 1. You are allowed to create and use your own private
 *         helper methods. If you are introducing your own helper methods those
 *         need to be private and properly documented as per Javadoc style. 2.
 *         No data fields can be introduced. 3. No custom classes of your own
 *         can be introduced or used. 4. Import statements are not allowed. 5.
 *         Fully qualified class names usage is not allowed. Except the ones
 *         that are already provided to you. 6. You are allowed to reuse any
 *         part of the provided source codes or shown under lecture notes, which
 *         do not violate any of above. 7. If you have any additional questions
 *         please ask on Piazza Q/A platform, but first PLEASE search and make
 *         sure that it was not already asked and answered. PLEASE setup your
 *         notifications for both Canvas and Piazza so that you are updated
 *         whenever there are any changes immediately.
 * 
 * 
 *         8. You need to answer the questions below for each method.
 * 
 */

public class SearchingExercises {
	/**
	 * Assume you are given an array of Integer values sorted in strictly increasing
	 * order, and a List of unsorted Integer values. Your task in this method is to
	 * find the minimal interval, i.e., to compute the smallest range, of array
	 * indices that contains all of the target values in a given List givenValues.
	 * If the value in givenValues is smaller than sortedArr[0], then it means that
	 * range will start at -1. If the value in givenValue is larger than
	 * sortedArr[sortedArray.length-1], then it means that range will end at
	 * sortedArray.length.
	 * 
	 * For example, if the givenValue is composed of [8, 2, 9, 17], and the
	 * sortedArr is composed of {5, 8, 10, 13, 15, 20, 22, 26}, the range returned
	 * would be {-1, 5}.
	 *
	 * 
	 * QUESTION: Assuming sortedArr has m elements and givenValues has n values,
	 * what is the Big Oh performance of your algorithm?
	 * 
	 * ANSWER: My findMinInterval method is going to operate at O(n + m) or another
	 * way to look at is is O( max(n,m) ). This is because the max of the two would
	 * take over in the big O notation, and the other would be ignored. More
	 * efficient methods should exist using a sort of modified binary search
	 * implementation, and checking the previous min and max variables. If I have
	 * time, I will implement that way.
	 * 
	 * NOTE: Brief description is required as part of your answer.
	 * 
	 * @param sortedArr
	 *            an array of Integers sorted in strictly increasing order. At least
	 *            one element will be present. No null.
	 * @param givenValues
	 *            a List of unsorted Integer values. At least one element will be
	 *            present. No null.
	 * @return a two element array of Integers indicating mininmal lower and upper
	 *         bounds (indices) in sortedArr which would include all List values.
	 */
	public static Integer[] findMinInterval(Integer[] sortedArr, java.util.List<Integer> givenValues) {
		// start by finding min and max values
		int min = givenValues.get(0);
		int max = givenValues.get(givenValues.size() - 1);
		for (int i = 1; i < givenValues.size(); i++) {
			if (givenValues.get(i) < min)
				min = givenValues.get(i);
			else if (givenValues.get(i) > max)
				max = givenValues.get(i);
		}

		int startPoint = -1;
		int endPoint = sortedArr.length;
		for (int i = 0; i < sortedArr.length - 1; i++) {
			if (max > sortedArr[i] && max <= sortedArr[i + 1])
				endPoint = i + 1;

			if (min >= sortedArr[i] && min < sortedArr[i + 1])
				startPoint = i + 1;
		}
		return new Integer[] { startPoint, endPoint };
	}

	/**
	 * Assume that you are given a two-dimensional array with Comparable items
	 * stored in it. For example, consider the following 3 by 4 two-dimensional
	 * array
	 * 
	 * 1, 4, 55, 88 7, 15, 61, 91 14, 89, 90, 99
	 * 
	 * Values in each row and column of this two-dimensional array are always come
	 * sorted in strictly increasing order.
	 * 
	 * Our method find2D needs to inform the user whether the the user searched key
	 * exists in this two-dimensional array or not. You can assume that
	 * two-dimensional array will have at least one element in it.
	 * 
	 * 
	 * QUESTION: Assuming arr has m rows and n columns, what is the Big Oh
	 * performance of your algorithm?
	 * 
	 * ANSWER: Because I'm using binary search for each row (I'm sure there is a
	 * more efficient method, but this atleast seems to work well), my big O
	 * efficiency should be O(n*log(m)). This is because my implementation will go
	 * through all n columns, and only takes log(m) of the row m into consideration.
	 * 
	 * 
	 * 
	 * NOTE: Brief description is required as part of your answer.
	 * 
	 * @param arr
	 *            two-dimensional array of Comparable objects. At least will have a
	 *            single element. No null values.
	 * @param key
	 *            searched Comparable object. Not null.
	 * @return indication of whether key exists in arr or not.
	 * 
	 */
	public static <T extends Comparable<? super T>> boolean find2D(T[][] arr, T key) {
		for (int i = 0; i < arr.length; i++)
			if (binarySearch(arr[i], key, 0, arr[i].length - 1) == true)
				return true;
		return false;
	}

	/**
	 * A binary search method that looks at a simple 1D array with a key
	 * 
	 * @param arr
	 *            the array to examine through
	 * @param key
	 *            the item we are seeking
	 * @param startIndex
	 *            the starting index within the array arr
	 * @param endIndex
	 *            the ending index within the ending index arr
	 * @return boolean variable representing if we found the item or not
	 *
	 */
	private static <T extends Comparable<? super T>> boolean binarySearch(T[] arr, T key, int startIndex,
			int endIndex) {
		if (startIndex > endIndex)
			return false;
		int mid = startIndex + (endIndex - startIndex) / 2;
		if (arr[mid].compareTo(key) == 0)
			return true;
		else if (arr[mid].compareTo(key) > 0)
			return binarySearch(arr, key, startIndex, mid - 1);
		else
			return binarySearch(arr, key, mid + 1, endIndex);
	}

}
