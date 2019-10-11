package com.sonatype.interview.printnumbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberDictionaryTest {

	@Test
	public void test_getFittingUnit_hundred() {
		assertEquals(100, NumberDictionary.getFittingUnit(100).value);
	}

	@Test
	public void test_geFittingtUnit_thousand() {
		assertEquals(1000, NumberDictionary.getFittingUnit(2_000).value);
	}
	
	@Test
	public void test_getFittingUnit_zero() {
		assertEquals(0, NumberDictionary.getFittingUnit(0).value);
	}
	
	@Test
	public void test_geFittingtUnit_nineteen() {
		assertEquals("nineteen", NumberDictionary.getFittingUnit(19).word);
	}
	
	@Test
	public void test_getFittingUnit_one() {
		assertEquals("one", NumberDictionary.getFittingUnit(1).word);
	}
	
	@Test
	public void test_getExactUnit_one() {
		assertEquals("one", NumberDictionary.getExactUnit(1).word);
	}
	
	@Test
	public void test_getFittingMultiplier_million() {
		assertEquals("million", NumberDictionary.getFittingMultiplier(2_000_000).word);
	}
	
}
