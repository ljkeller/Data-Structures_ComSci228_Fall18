package edu.iastate.cs228.proj2;

import java.util.Comparator;

/**
 * 
 * @author Lucas Keller
 *
 */
public class LexiconImpl implements Lexicon, Comparator<String> {

	/***
	 * Lookup table mapping characters in lexicographical order to to their input
	 * order. This is public to support automated grading.
	 */
	public CharacterValue[] characterOrdering;

	/***
	 * Creates an array of CharacterValue from characterOrdering. Sorts it using
	 * java.util.Arrays.sort().
	 * 
	 * @param characterOrdering
	 *            character order from configuration file
	 */
	public LexiconImpl(char[] characterOrdering) {
		this.characterOrdering = new CharacterValue[characterOrdering.length];
		for (int i = 0; i < this.characterOrdering.length; i++) {
			this.characterOrdering[i] = new CharacterValue(i, characterOrdering[i]);
		}
		java.util.Arrays.sort(this.characterOrdering, (c1, c2) -> {
			return c1.character - c2.character;
		});
	}

	/***
	 * Compares two words based on the configuration
	 * 
	 * @param a
	 *            first word
	 * @param b
	 *            second word
	 * @return negative if a<b, 0 if equal, positive if a>b
	 */
	@Override
	public int compare(String a, String b) {
		int counter = 0;
		int smallerLength = Math.min(a.length(), b.length());
		while (counter < smallerLength) {
			if (getCharacterOrdering(a.charAt(counter)) == getCharacterOrdering(b.charAt(counter))) {
				if (counter == a.length() - 1 && counter  == b.length() - 1)
					return 0;
				counter++;
			} else // else, if lengths are different but content is the same up until end of
					// smallest
				return (getCharacterOrdering(a.charAt(counter)) > getCharacterOrdering(b.charAt(counter))) ? 1 : -1;
		}
		return (a.length() > b.length()) ? -1 : 1;
	}

	/**
	 * Uses binary search to find the order of key.
	 * 
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {

		return getCharacterOrdering(key, 0, characterOrdering.length - 1);

	}

	/**
	 * Uses recursive binary search to find magnitude of the input character
	 * 
	 * @param key
	 *            the character passed in we want a magnitude for
	 * @param startIndex
	 *            the starting index of our array segment
	 * @param endIndex
	 *            the ending index of our array segment
	 * @return the magnitude, or value, of the passed in character
	 */
	private int getCharacterOrdering(char key, int startIndex, int endIndex) {
		int mid = startIndex + (endIndex - startIndex) / 2;

		if (startIndex > endIndex)
			return -1;
		else if (Character.compare(characterOrdering[mid].character, key) == 0)
			return characterOrdering[mid].value;
		else if (Character.compare(characterOrdering[mid].character, key) > 0)
			return getCharacterOrdering(key, startIndex, mid - 1);
		else if (Character.compare(characterOrdering[mid].character, key) < 0)
			return getCharacterOrdering(key, mid + 1, endIndex);

		return -1;
	}

	public static class CharacterValue {
		public int value;
		public char character;

		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}

		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue other = (CharacterValue) o;
			return value == other.value && character == other.character;
		}
	}

	/**
	 * Returns whether or not word is valid according to the alphabet known to this
	 * lexicon.
	 * 
	 * @param word
	 *            word to be checked.
	 *
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		boolean isValid = true;
		for (int i = 0; i < word.length(); i++) {
			if (getCharacterOrdering(word.charAt(i)) < 0)
				return !isValid;
		}
		return isValid;
	}

}
