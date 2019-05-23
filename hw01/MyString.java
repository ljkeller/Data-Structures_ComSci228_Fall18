package edu.iastate.cs228.hw01;

/**
 * @author Lucas Keller
 *
 */
public class MyString {

	private char[] chars;

	public MyString(char[] chars) {
		if (chars == null || chars.length == 0)
			throw new IllegalArgumentException();

		this.chars = new char[chars.length];
		for (int i = 0; i < chars.length ; i++) {
			this.chars[i] = chars[i];
		}

	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--
	public int length() {
		int counter = 0;

		for (int i = 0; i < chars.length; i++) {
			counter++;
		}
		return counter;
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
	public char charAt(int index) {
		return chars[index];
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-
	public MyString substring(int begin, int end) {
		// adding one because of how array starting at 0
		if (end > chars.length || begin > end || begin < 0)
			throw new IndexOutOfBoundsException();
		char tempChars[] = new char[end - begin];
		int subCounter = begin;
		for (int i = 0; i < end - begin; i++) {
			tempChars[i] = chars[subCounter];
			subCounter++;

		}
		return new MyString(tempChars);
	}

	// It is ok to use
	// https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#toLowerCase-char-
	public MyString toLowerCase() {
		char[] tempChars = new char[chars.length];
		for (int i = 0; i < chars.length; i++) {
			tempChars[i] = Character.toLowerCase(chars[i]);
		}
		return new MyString(tempChars);
	}

	// You can assume only positive values.
	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#valueOf-int-
	public static MyString valueOf(int i) {
		//assume only positive values (so at least have size 1)
		int arraySizeCounter = 1;
		int iReducer = i;

		// finds how many array elements we will have to save memory
		while (iReducer / 10 != 0) {
			iReducer /= 10;
			arraySizeCounter++;
		}
		// reset iReducer to I for assignment
		iReducer = i;

		// Begin array assignment
		char[] tempChar = new char[arraySizeCounter];
		//Starting from max size because using mod for remainders. Otherwise is backwards
		for (int j = arraySizeCounter -1; j >= 0; j--) {
			//'0' is ascii value 48. Must add '0' to get ascii representation (then cast)
			tempChar[j] = (char) ((iReducer % 10) + '0');
			iReducer = iReducer / 10;
		}
		return new MyString(tempChar);
	}

	public char[] toCharArray() {
		return chars;
	}
}