package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Lucas Keller
 *
 */
public class SelectionSort extends SorterWithStatistics {

	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		
		for (int i = 0; i < words.length - 1; i++) {
			int minWordIndex = i;
			for (int k = i + 1; k < words.length; k++) {
				if (comp.compare(words[minWordIndex], words[k]) > 0) {
					minWordIndex = k;
				}
			}
			String tempMin = words[minWordIndex];
			words[minWordIndex] = words[i];
			words[i] = tempMin;
		}
	}
}
