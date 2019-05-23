package edu.iastate.cs228.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Lucas Keller
 * 
 *         Discussion Post: It is hard to make exact performance predictions,
 *         especially with some of the smaller input sizes, but obviously I
 *         predict Selection sort to be the slowest of the group by a good
 *         amount. While it may be true that selection sort is O(n^2), quicksort
 *         is technically O(n^2) and mergesort is O(nlong), I predict merge and
 *         quicksort to be very close in performace. With the input size of
 *         10000000, selection sort should really struggle, as the computation
 *         time goes to 10000000*10000000. I believe that if quick sort isn't
 *         close to mergesort, I could probably do adjustments to make it close
 *         to merge (based on pivot). On quick calculations, it looks like
 *         quicksort will have more than a million times the total executions of
 *         mergeSort. From the tests, my selection sort out-performs quick and
 *         merge sort early on, then tends to be beat out especially after that
 *         1000 size list. With time, it really seems like quickSort is being
 *         between merge and selection. Towards the higher list sizes, selection
 *         sort is really struggling- the O(n^2) really makes things difficult.
 *         Its probable my code could be improved greatly though. Overall, the
 *         results parrallel to what we'd expect from class. *
 */
public class EvalSorts {
	public static final int kNumberOfWordsToSort = 10000;

	/**
	 * main is responsible only for extracting fileNames from args, reading the
	 * files, and constructing an instance of the this class configured with the
	 * input data. FileNotFoundException and FileConfigurationException exceptions
	 * should be handled in main, i.e., print out appropriate message to the user.
	 */
	public static void main(String args[]) {
		char[] alphabet = null; // ref to the Lexicon it creates.
		String[] wordList = null; // ref to the list of words to be sorted.
		EvalSorts theApp = null; // ref to the app.
		LexiconImpl comp = null; // the concrete lexicon your app uses.

		// TODO
		/*
		 * 
		 * Here you should add code that extracts the file names from the args array,
		 * opens and reads the data from the files,constructs an instance of Lexicon
		 * from the character order file, and then create an instance of this class
		 * (EvalSorts) to act as a configured instance of the application. After you
		 * have constructed the configured instance, you should start it running (see
		 * below).
		 * 
		 * 
		 * 
		 * 
		 */
		if (args.length < 2) {
			System.out.println("The passed array is not long enough");
			return;
		}
		File configurationFile = new File(args[0]);
		File wordListFile = new File(args[1]);

		try {
			// Character ordering
			alphabet = readCharacterOrdering(configurationFile.getAbsolutePath());
			if (alphabet == null)
				throw new FileNotFoundException();
			comp = new LexiconImpl(alphabet);

			// Wordlist related
			wordList = readWordsFile(wordListFile.getAbsolutePath(), comp);
			if (wordList == null)
				throw new FileNotFoundException();

		} catch (FileConfigurationException ex) {
			System.out.println("Failed to give correctly configured file");
			return;
		} catch (FileNotFoundException ex) {
			System.out.println("A file was not found");
			return;
		}

		// configure an instance of the app
		theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
		// now execute that instance
		theApp.runSorts();

	}

	private String[] words; // ref to the word lit
	private Lexicon lex; // ef to the relevant lexicon
	private int numWordsToSort = kNumberOfWordsToSort; // No longer needed

	/**
	 * This constructor configures an instance of EvalSorts to sort input read my
	 * main, using the character order read by main and now embedded in an instance
	 * of Lexicon
	 * 
	 * @param lex
	 *            the instance of lexicon to be used
	 * @param wordList
	 *            the wordlist (as array of string) to be sorted
	 * @param numWordsToSort
	 *            each sort will be repeated until it has sorted this many words.
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		this.lex = lex;
		this.words = wordList;
		numWordsToSort = wordList.length;
	}

	/**
	 * runSorts() performs the sort evaluation.
	 * 
	 * Note: The three sorters extend a common base so they share the same interface
	 * for starting the sort and collecting statistics. Thus, you should create
	 * instances of the sorter and save references to each in an array of base type.
	 * This allows you to use a simple loop to run all the reports and collect the
	 * statistics.
	 */
	public void runSorts() {

		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		sorters[0] = new SelectionSort();
		sorters[1] = new MergeSort();
		sorters[2] = new QuickSort();

		for (int i = 0; i < 3; i++) {
			sorters[i].sort(words, lex);
			System.out.println(sorters[i].getReport());
		}

	}

	/**
	 * Reads the characters contained in filename and returns them as a character
	 * array.
	 * 
	 * @param filename
	 *            the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used or
	 *          null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename)
			throws FileNotFoundException, FileConfigurationException {
		try {
			Scanner readFile = new Scanner(new File(filename));
			String result = "";
			String tempLine = "";
			while (readFile.hasNextLine()) {
				tempLine = readFile.nextLine();
				if (tempLine.length() > 1)
					throw new FileConfigurationException();
				result += tempLine;
			}

			char[] charOrdering = new char[result.length()];
			for (int i = 0; i < result.length(); i++) {
				charOrdering[i] = result.charAt(i);
				if (result.lastIndexOf(result.charAt(i)) != i) // checks to see if elements repeat
					throw new FileConfigurationException();
			}

			return charOrdering;
		} catch (FileNotFoundException ex) {
			return null;
		}

	}

	/**
	 * Reads the words from the file and returns them as a String array.
	 * 
	 * @param filename
	 *            file containing words
	 * @return the words contained in the file or null if there was a
	 *         FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp)
			throws FileNotFoundException, FileConfigurationException {
		try {
			Scanner readWords = new Scanner(new File(filename));
			String tempLine;
			int counter = 0;
			while (readWords.hasNextLine()) {
				tempLine = readWords.nextLine();
				boolean flag = comp.isValid(tempLine);
				if (flag == false)
					throw new FileConfigurationException();
				else {
					counter++;
				}
			}
			readWords.close();
			String[] validWords = new String[counter];
			readWords = new Scanner(new File(filename));
			counter = 0;
			while (readWords.hasNextLine()) {
				validWords[counter] = readWords.nextLine();
				counter++;
			}
			readWords.close();
			return validWords;

		} catch (FileNotFoundException ex) {
			return null;
		}
	}

}
