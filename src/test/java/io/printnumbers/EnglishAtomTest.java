package io.printnumbers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.printnumbers.english.EnglishAtom;
import io.printnumbers.interfaces.NumberAtom;

public class EnglishAtomTest {
	private NumberAtom withPrep = null;
	private NumberAtom withoutPrep = null;
	
	
	@Before
	public void setUp() {
		this.withPrep = EnglishAtom.of(12);
		this.withoutPrep = EnglishAtom.of(1_000_000);
	}

	@Test
	public void testWordedNumber() {
		assertNotNull(withPrep);
	}

	@Test
	public void testGetWord_with_preposition() {
		assertEquals("and twelve", withPrep.getWord(true));
	}

	@Test
	public void testGetWord_without_preposition() {
		assertEquals("million", withoutPrep.getWord(true));
	}
	
	@Test
	public void testToString() {
		assertEquals("twelve", withPrep.toString());
	}

	@Test
	public void testGetPreposition() {
		assertEquals("and", EnglishAtom.getPrepositionAtom().getWord());
	}

	@Test
	public void testGetMinus() {
		assertEquals("minus", EnglishAtom.of(-1).getWord());
	}

	@Test
	public void test_getFittingUnit_hundred() {
		assertEquals(100, EnglishAtom.matchingUnitOf(100).getValue());
	}

	@Test
	public void test_geFittingtUnit_thousand() {
		assertEquals(1000, EnglishAtom.matchingUnitOf(2_000).getValue());
	}
	
	@Test
	public void test_getFittingUnit_zero() {
		assertEquals(0, EnglishAtom.matchingUnitOf(0).getValue());
	}
	
	@Test
	public void test_geFittingtUnit_nineteen() {
		assertEquals("nineteen", EnglishAtom.matchingUnitOf(19).getWord());
	}
	
	@Test
	public void test_getFittingUnit_one() {
		assertEquals("one", EnglishAtom.matchingUnitOf(1).getWord());
	}
	
	@Test
	public void test_getExactUnit_one() {
		assertEquals("one", EnglishAtom.unitOf(1).getWord());
	}
	
	@Test
	public void test_getFittingMultiplier_million() {
		assertEquals("million", EnglishAtom.multiplierOf(2_000_000).getWord());
	}
	

}
