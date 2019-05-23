package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Lucas Keller
 *
 */

public class MergeSort extends SorterWithStatistics {

	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {

		if (words.length == 1)
			return;
		int mid = words.length / 2;
		int k = 0, i = 0;

		String[] firstHalf = new String[mid];
		for (i = 0; i < mid; i++) {
			firstHalf[i] = words[i];
		}
		sortHelper(firstHalf, comp);

		int remaining = words.length - mid;

		String[] secondHalf = new String[remaining];
		for (i = mid; i < words.length; i++) {
			secondHalf[k] = words[i];
			k++;
		}
		sortHelper(secondHalf, comp);

		sortHelper(firstHalf, secondHalf, words, comp);

		
	}

	/**
	 * The comparation and filling part of mergesort/The demanding part of mergesort
	 * 
	 * @param firstHalf
	 *            The first half of the original array
	 * @param secondHalf
	 *            The second half of the original array
	 * @param original
	 *            The original array to be modified
	 * @param comp
	 *            The comparator used for comparing words
	 */
	private void sortHelper(String[] firstHalf, String[] secondHalf, String[] original, Comparator<String> comp) {

		int counterF = 0;
		int counterS = 0;
		int i = 0;

		while (counterF < firstHalf.length && counterS < secondHalf.length) {
			if (comp.compare(firstHalf[counterF], secondHalf[counterS]) >= 0) {
				original[i] = secondHalf[counterS];
				i++;
				counterS++;
			} else if (comp.compare(firstHalf[counterF], secondHalf[counterS]) < 0) {
				original[i] = firstHalf[counterF];
				i++;
				counterF++;
			}
		}
		while (counterF < firstHalf.length && counterS >= secondHalf.length) {
			original[i] = firstHalf[counterF];
			i++;
			counterF++;
		}
		while (counterS < secondHalf.length && counterF >= firstHalf.length) {
			original[i] = secondHalf[counterS];
			i++;
			counterS++;
		}
	}

}
