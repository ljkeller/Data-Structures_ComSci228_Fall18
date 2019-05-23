package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Lucas Keller
 *
 */

public class ProteinSequenceTest {

	ProteinSequence test1 = new ProteinSequence("ATGATTATTATt".toCharArray());

	@Test
	public void testIsValidLetter() {
		assertTrue(test1.isValidLetter('a'));
		assertFalse(test1.isValidLetter('b'));
		assertFalse(test1.isValidLetter('j'));
		assertFalse(test1.isValidLetter('O'));
		assertFalse(test1.isValidLetter('U'));
		assertFalse(test1.isValidLetter('X'));
		assertFalse(test1.isValidLetter('z'));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProteinSequence() {
		ProteinSequence thrower = new ProteinSequence("ATGx".toCharArray());
	}

}
