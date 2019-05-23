package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

/**
 * 
 * @author Lucas Keller
 *
 */

import org.junit.Test;

public class DNASequenceTest {

	@Test
	public void testIsValidLetter() {
		DNASequence dnaSimple = new DNASequence("aCcA".toCharArray());
		assertTrue(dnaSimple.isValidLetter('a'));
		assertTrue(dnaSimple.isValidLetter('g'));
		assertTrue(dnaSimple.isValidLetter('C'));
		assertTrue(dnaSimple.isValidLetter('A'));
		assertFalse(dnaSimple.isValidLetter('z'));
		assertFalse(dnaSimple.isValidLetter('#'));
		assertFalse(dnaSimple.isValidLetter('m'));
		assertFalse(dnaSimple.isValidLetter('&'));
		assertFalse(dnaSimple.isValidLetter(' '));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDNASequence() {
		DNASequence dnaSimple = new DNASequence("aCcA".toCharArray());
		DNASequence dnaSimple1 = new DNASequence("aCTA".toCharArray());
		DNASequence dnaSimple2 = new DNASequence("a&cA".toCharArray());
		DNASequence dnaSimple3 = new DNASequence(" ".toCharArray());
		
	}

}
