package edu.iastate.cs228.proj1;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Lucas Keller
 *
 */

public class GenomicDNASequenceTest {

	GenomicDNASequence test1 = new GenomicDNASequence("ATGATTCCT".toCharArray());

	@Test(expected = IllegalArgumentException.class)
	public void testGenomicDNASequence() {
		GenomicDNASequence thrower = new GenomicDNASequence("ATG*".toCharArray());
	}

	//tests for inclusive bounds through extractExons() 
	@Test
	public void testMarkCoding() {
		GenomicDNASequence givenExample = new GenomicDNASequence("AATGCCAGTCagcatagcgtagact".toLowerCase().toCharArray());
		givenExample.markCoding(1, 5);
		givenExample.markCoding(8, 10);
		givenExample.markCoding(13, 16);
		assertArrayEquals("ATGCCTCAATAG".toLowerCase().toCharArray(), givenExample.extractExons(new int[] { 1, 5, 8, 10, 13, 16 }));
	}

	@Test
	public void testExtractExons() {
		GenomicDNASequence givenExample = new GenomicDNASequence("AATGCCAGTCagcatagcgtagact".toLowerCase().toCharArray());
		givenExample.markCoding(1, 16);
		assertArrayEquals("ATGCCTCAATAG".toLowerCase().toCharArray(), givenExample.extractExons(new int[] { 1, 5, 8, 10, 13, 16 }));
	}
	
	@Test ( expected = IllegalArgumentException.class)
	public void testIllegalArguments() {
		GenomicDNASequence givenExample = new GenomicDNASequence("AATGCCAGTCagcatagcgtagact".toLowerCase().toCharArray());
		givenExample.markCoding(1, 16);
		//givenExample.extractExons(new int[] { 1, 5, 8, 10, 13});
		//givenExample.extractExons(new int[] {});
		//givenExample.extractExons(new int[] { 1, 5, 8, 10, 99});
		givenExample.extractExons(new int[] { -1, 5, 8, 10, 13});
		givenExample.extractExons(new int[] { 5, 1, 8, 10, 13});
		
	}
	
	@Test ( expected = IllegalStateException.class)
	public void testIllegalState() {
		GenomicDNASequence givenExample = new GenomicDNASequence("AATGCCAGTCagcatagcgtagact".toLowerCase().toCharArray());
		givenExample.markCoding(1, 4);
		givenExample.markCoding(6, 10);
		givenExample.markCoding(8, 12);
		assertArrayEquals("ATGCCTCAATAG".toLowerCase().toCharArray(), givenExample.extractExons(new int[] { 1, 5, 8, 10, 13, 16 }));
		
	}
}
