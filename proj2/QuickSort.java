package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Lucas Keller
 *
 */

public class QuickSort extends SorterWithStatistics {

	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		if (words.length == 1)
			return;
		sortHelper(words, comp, 0, words.length - 1);
	}

	/**
	 * Simple overridden method for sortHelper that uses the partition method that does the leg work.
	 * @param words
	 * 			The string array
	 * @param comp
	 * 			The comparator we use to compare words
	 * @param startIndex
	 * 			Starting point for our partition call, ect...
	 * @param endIndex
	 * 			The final index we sort
	 */
	private void sortHelper(String[] words, Comparator<String> comp, int startIndex, int endIndex) {
		if (endIndex > startIndex) {
			int pivot = partition(words, comp, startIndex, endIndex);
			sortHelper(words, comp, startIndex, pivot - 1);
			sortHelper(words, comp, pivot + 1, endIndex);
		}
	}

	/**
	 * The method doing the actual sorting, based off of the pivot point
	 * @param words
	 * 			The String array
	 * @param comp
	 * 			The comparator we use for comparing words
	 * @param startIndex	
	 * 			The index used for our starting point/pivot
	 * @param endIndex
	 * 			The the last index we consider for comparation
	 * @return
	 * 			The location of the pivot, after swapping
	 */
	private int partition(String[] words, Comparator<String> comp, int startIndex, int endIndex) {

		String pivot = words[startIndex];
		int fromBeg = startIndex + 1;
		int fromEnd = endIndex;

		while (fromEnd > fromBeg) {
			while (fromBeg <= fromEnd && comp.compare(words[fromBeg], pivot) <= 0) {
				fromBeg++;
			}
			while (fromBeg <= fromEnd && comp.compare(words[fromEnd], pivot) > 0) {
				fromEnd--;
			}

			if (fromBeg < fromEnd) {
				String temp = words[fromBeg];
				words[fromBeg] = words[fromEnd];
				words[fromEnd] = temp;
			}
		}
		while (fromEnd > startIndex && comp.compare(words[fromEnd], pivot) >= 0)
			fromEnd--;

		if (comp.compare(pivot, words[fromEnd]) > 0) {
			words[startIndex] = words[fromEnd];
			words[fromEnd] = pivot;
			return fromEnd;
		} else
			return startIndex;
	}
}
