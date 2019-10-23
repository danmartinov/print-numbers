package io.printnumbers.english;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a binary tree in which the spelling of the number will decompose.
 * On the left branch we store the composed number of the highest factor (multiplier), 
 * while on the right branch we store the remainder of the number after taking out the multiplier.
 * 
 * For example, 232_123_322 will be decomposed to the left in a tree representing 232 millions, 
 * while on the right a tree representing 123_322.
 * 
 * The left tree will further contain 2 hundred to the left and 32 to the right, further represented 
 * as a left:30 and a right:2.
 * 
 * This representation is then used to build the word number, as a list that can further be processed easily for English exceptions.
 * 
 * NOTE: The tree is built recursively. There is no concern of a stack overflow because of the limited length of the integer.
 * 
 * @author dan
 *
 */
public class EnglishNumberNode {
	int mantissa;
	int remainder;
	
	private EnglishAtom units; 
	private EnglishAtom multiplier; 

	private EnglishNumberNode left; 
	private EnglishNumberNode right; 
	
	@SuppressWarnings("unused")
	private EnglishNumberNode() {}
	
	public EnglishNumberNode(int number) {
		splitNumber(number);
	}
	
	
	/**
	 * Multiplier and units are set for a node that is not composed, or the only "left" composition is the minus sign.
	 *  
	 * @param number
	 * @return
	 */
	private void splitNumber(int number) {
		if (number == -1) {
			units = EnglishAtom.of(-1);
			return;
		}
		multiplier = EnglishAtom.multiplierOf(number);
		
		// no multiplier found means number under hundred
		// mantissa is going to be the value of the units matched
		if (null == multiplier) {
			if (number < 0) {
				left = new EnglishNumberNode(-1);
			}
			units = EnglishAtom.matchingUnitOf(number);
			mantissa = units.getValue();
			remainder = Math.abs(number) - mantissa;
		}
		else {
			mantissa = number / multiplier.getValue();
			remainder = Math.abs(number % multiplier.getValue());
		}
		
		if (null == units) {
			left = new EnglishNumberNode(mantissa);
		}
		
		if (remainder != 0) {
			right = new EnglishNumberNode(remainder);
		}
	}
	
	public List<EnglishAtom> toList(List<EnglishAtom> list) {
		if (left != null) {
			left.toList(list);
		}
		if (units != null && units.getValue() != 0) { 
			list.add(units); 
		}
		if (multiplier != null && multiplier.getValue() > 0) {
			list.add(multiplier);
		}
		if (right != null) {
			right.toList(list);
		}
		return list;
	}
	
	@Override
	public String toString() {
		List<EnglishAtom> list = new ArrayList<>();
		list = this.toList(list);
		list = this.insertPreposition(list);
		return list.stream().map(EnglishAtom::toString).collect(Collectors.joining(" "));
	}
	
	private List<EnglishAtom> insertPreposition (List<EnglishAtom> list) {
		// Note: 
		// My take on English grammar is that we add the "and" preposition only once, 
		// before the tail of the worded number, if the tail is made of a continuous 
		// sequence of max two numbers
		int count = 0;
		for (int i = list.size() - 1; i > 0; i--) {
			boolean canUse = list.get(i).isCanUsePreposition();
			// give we started finding preposition items and "interrupted", we stop looking
			if (!canUse && count > 0) {
				list.add(i+1, EnglishAtom.getPrepositionAtom());
				break;
			}
			else if (count <= 2 && canUse) {
				count++;
			}
		}
		return list;
	}
	
}
