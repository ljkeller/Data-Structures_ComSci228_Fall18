package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 
 * @author Lucas Keller
 *
 */
public class CodingDNASequenceTest {

	CodingDNASequence test1 = new CodingDNASequence("ATG".toCharArray());
	CodingDNASequence test2 = new CodingDNASequence("ATGATTCAA".toCharArray());
	CodingDNASequence test3 = new CodingDNASequence("ATGat".toCharArray());
	CodingDNASequence test4 = new CodingDNASequence("ATGCCACCggAgggAtt".toCharArray());

	@Test(expected = IllegalArgumentException.class)
	public void testCodingDNASequence() {
		CodingDNASequence throwing = new CodingDNASequence("atga^".toCharArray());
		CodingDNASequence throwing1 = new CodingDNASequence("atgd".toCharArray());
	}

	@Test
	public void testCheckStartCodon() {
		assertTrue(test1.checkStartCodon());
		assertTrue(test2.checkStartCodon());
		assertTrue(test3.checkStartCodon());
		assertTrue(test4.checkStartCodon());
		assertFalse(new CodingDNASequence("AgT".toCharArray()).checkStartCodon());
		assertFalse(new CodingDNASequence("atc".toCharArray()).checkStartCodon());
	}

	@Test
	public void testTranslate() {
		assertArrayEquals("M".toCharArray(), test1.translate());
		assertArrayEquals("MIQ".toCharArray(), test2.translate());
		assertArrayEquals("M".toCharArray(), test3.translate());
		assertArrayEquals("MPPEG".toCharArray(), test4.translate());
	}

}
