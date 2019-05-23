package edu.iastate.cs228.proj1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author Lucas Keller
 *
 */
public class SequenceTest {

	char[] basicSeq = new char[] { 'A', 'c', 'g', 'T', 'T' };
	char[] badSeq = new char[] { 'A', 'T', 'g', 'b', 'B' };
	Sequence seq1 = new Sequence(basicSeq);
	Sequence seq2 = new Sequence(badSeq);

	@Test(expected = IllegalArgumentException.class)
	public void testSequence() {
		Sequence testConstruct = new Sequence("ACT".toCharArray());
		Sequence seq2 = new Sequence("ACTbVzr;".toCharArray());
		Sequence seq3 = new Sequence("    ".toCharArray());
	}

	@Test
	public void testSeqLength() {
		assertEquals(5, seq1.seqLength());

	}

	@Test
	public void testGetSeq() {
		Sequence getSeqOriginal = new Sequence("AghhhT".toCharArray());
		Sequence getSeqDeep = new Sequence(getSeqOriginal.getSeq());
		assertEquals(getSeqDeep, getSeqOriginal);
		// tests if changing original changes copy
		assertFalse(getSeqDeep.equals(getSeqOriginal = new Sequence("T".toCharArray())));
		// assertEquals("AcgTT", seq1.getSeq().toString());
		// assertTrue("AcgTT".equals(seq1.toString()))
		assertEquals(5, seq1.getSeq().length);
	}

	@Test
	public void testToString() {
		assertEquals("AcgTT", seq1.toString());
		assertEquals("AAAAcTTT".toString(), new Sequence("AAAAcTTT".toCharArray()).toString());
		assertEquals("AcgTT".length(), seq1.toString().length());
	}

	@Test
	public void testEqualsObject() {
		assertTrue(seq1.equals(seq1));
		assertTrue(seq1.equals(new Sequence("AcgTT".toCharArray())));
		assertTrue(seq1.equals(new Sequence("ACGtt".toCharArray())));

		assertFalse(seq1.equals(badSeq));
		assertFalse(seq1.equals(new Sequence("ttGCA".toCharArray())));
		assertFalse(
				(new Sequence("AGASDTADFAD".toCharArray())).equals((new Sequence("AGTAAXVASDFadfa".toCharArray()))));
	}

	@Test
	public void testIsValidLetter() {
		assertTrue(seq1.isValidLetter('a'));
		assertTrue(seq1.isValidLetter('b'));
		assertTrue(seq1.isValidLetter('T'));
		assertTrue(seq1.isValidLetter('t'));
		assertFalse(seq1.isValidLetter('*'));
		assertFalse(seq1.isValidLetter(' '));
	}

}
