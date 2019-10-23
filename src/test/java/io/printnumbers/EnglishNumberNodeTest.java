package io.printnumbers;

import static org.junit.Assert.*;

import org.junit.Test;

import io.printnumbers.english.EnglishNumberNode;

/**
 * The tree testing can get very complex at low level, so I chose to implement tests 
 * that would catch low level issues by testing a final string result. 
 * This approach maintains high condition coverage but in case of failure it requires more 
 * debugging effort to find the root cause. It's a balance of effort between before and after finding
 * a failure.
 * 
 * @author dan
 *
 */
public class EnglishNumberNodeTest {

	@Test
	public void testToString_ninety() {
		EnglishNumberNode tree = new EnglishNumberNode(1_234_567_890);
		assertEquals("one billion two hundred thirty four million five hundred sixty seven thousand eight hundred and ninety", tree.toString());
	}
	
	@Test
	public void testToString_three() {
		EnglishNumberNode tree = new EnglishNumberNode(1_234_567_893);
		assertEquals("one billion two hundred thirty four million five hundred sixty seven thousand eight hundred and ninety three", tree.toString());
	}
	
	@Test
	public void testToString_thousand() {
		EnglishNumberNode tree = new EnglishNumberNode(1_234_567_000);
		assertEquals("one billion two hundred thirty four million five hundred and sixty seven thousand", tree.toString());
	}
	
	@Test
	public void testToString_million() {
		EnglishNumberNode tree = new EnglishNumberNode(1_234_000_000);
		assertEquals("one billion two hundred and thirty four million", tree.toString());
	}
	
	@Test
	public void testToString_million_and_one() {
		EnglishNumberNode tree = new EnglishNumberNode(1_234_000_001);
		assertEquals("one billion two hundred thirty four million and one", tree.toString());
	}
	
	@Test
	public void testToString_million_and_eleven() {
		EnglishNumberNode tree = new EnglishNumberNode(1_234_000_011);
		assertEquals("one billion two hundred thirty four million and eleven", tree.toString());
	}
	
	@Test
	public void testToString_million_hundred() {
		EnglishNumberNode tree = new EnglishNumberNode(1_234_000_191);
		assertEquals("one billion two hundred thirty four million one hundred and ninety one", tree.toString());
	}
	
	@Test
	public void testToString_no_multiplier() {
		EnglishNumberNode tree = new EnglishNumberNode(191);
		assertEquals("one hundred and ninety one", tree.toString());
	}
	
	@Test
	public void testToString_zero() {
		EnglishNumberNode tree = new EnglishNumberNode(0);
		assertEquals("", tree.toString());
	}
	
	@Test
	public void testToString_billion_and_one() {
		EnglishNumberNode tree = new EnglishNumberNode(2_000_000_001);
		assertEquals("two billion and one", tree.toString());
	}
	
	@Test
	public void testToString_minus_big() {
		EnglishNumberNode tree = new EnglishNumberNode(-2_147_483_648);
		assertEquals("minus two billion one hundred forty seven million four hundred eighty three thousand six hundred and forty eight", tree.toString());
	}
	
	@Test
	public void testToString_minus_min() {
		EnglishNumberNode tree = new EnglishNumberNode(Integer.MIN_VALUE);
		assertEquals("minus two billion one hundred forty seven million four hundred eighty three thousand six hundred and forty eight", tree.toString());
	}
	
	@Test
	public void testToString_max() {
		EnglishNumberNode tree = new EnglishNumberNode(Integer.MAX_VALUE);
		assertEquals("two billion one hundred forty seven million four hundred eighty three thousand six hundred and forty seven", tree.toString());
	}
	
}
