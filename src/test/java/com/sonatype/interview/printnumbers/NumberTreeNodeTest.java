package com.sonatype.interview.printnumbers;

import static org.junit.Assert.*;

import org.junit.Test;

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
public class NumberTreeNodeTest {

	@Test
	public void testToString1_() {
		NumberTreeNode tree = new NumberTreeNode(1_234_567_890);
		assertEquals("one billion two hundred thirty four million five hundred sixty seven thousand eight hundred and ninety", tree.toString());
	}
	
	@Test
	public void testToString2_() {
		NumberTreeNode tree = new NumberTreeNode(1_234_567_893);
		assertEquals("one billion two hundred thirty four million five hundred sixty seven thousand eight hundred and ninety three", tree.toString());
	}
	
	@Test
	public void testToString3_() {
		NumberTreeNode tree = new NumberTreeNode(1_234_567_000);
		assertEquals("one billion two hundred thirty four million five hundred and sixty seven thousand", tree.toString());
	}
	
	@Test
	public void testToString4_() {
		NumberTreeNode tree = new NumberTreeNode(1_234_000_000);
		assertEquals("one billion two hundred and thirty four million", tree.toString());
	}
	
	@Test
	public void testToString5_() {
		NumberTreeNode tree = new NumberTreeNode(1_234_000_001);
		assertEquals("one billion two hundred thirty four million and one", tree.toString());
	}
	
	@Test
	public void testToString6_() {
		NumberTreeNode tree = new NumberTreeNode(1_234_000_011);
		assertEquals("one billion two hundred thirty four million and eleven", tree.toString());
	}
	
	@Test
	public void testToString7_() {
		NumberTreeNode tree = new NumberTreeNode(1_234_000_191);
		assertEquals("one billion two hundred thirty four million one hundred and ninety one", tree.toString());
	}
	
	@Test
	public void testToString8_() {
		NumberTreeNode tree = new NumberTreeNode(191);
		assertEquals("one hundred and ninety one", tree.toString());
	}
	
	@Test
	public void testToString9_() {
		NumberTreeNode tree = new NumberTreeNode(0);
		assertEquals("", tree.toString());
	}
	
	@Test
	public void testToString10_() {
		NumberTreeNode tree = new NumberTreeNode(2_000_000_001);
		assertEquals("two billion and one", tree.toString());
	}
	
}
