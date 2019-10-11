package io.printnumbers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.printnumbers.WordedNumber;

public class WordedNumberTest {
	private WordedNumber withPrep = null;
	private WordedNumber withoutPrep = null;
	
	
	@Before
	public void setUp() {
		this.withPrep = new WordedNumber(12, "dozen", true);
		this.withoutPrep = new WordedNumber(1_000_000, "million", false);
	}

	@Test
	public void testWordedNumber() {
		assertNotNull(withPrep);
	}

	@Test
	public void testGetWord_with_preposition() {
		assertEquals("and dozen", withPrep.getWord(true));
	}

	@Test
	public void testGetWord_without_preposition() {
		assertEquals("million", withoutPrep.getWord(true));
	}
	
	@Test
	public void testToString() {
		assertEquals("dozen", withPrep.toString());
	}

	@Test
	public void testGetPreposition() {
		assertEquals("and", WordedNumber.getPreposition().word);
	}

	@Test
	public void testGetMinus() {
		assertEquals("minus", WordedNumber.getMinus().word);
	}

}
