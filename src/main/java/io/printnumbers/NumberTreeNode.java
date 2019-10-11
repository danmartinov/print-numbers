package io.printnumbers;

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
public class NumberTreeNode {
	
	WordedNumber units; // if not a composed node (no left branch) this holds the final units
	WordedNumber multiplier; // and this holds the multiplier

	NumberTreeNode left; // this to the eventually composed number of the highest multiplier (ex: 434 millions)
	NumberTreeNode right; // this points to the rest (remainder) of the number
	
	@SuppressWarnings("unused")
	private NumberTreeNode() {}
	
	public NumberTreeNode(int number) {
		int mantissa, remainder;
		
		this.multiplier = NumberDictionary.getFittingMultiplier(number);
		
		// check if in under thousand number, then mantissa is the hundreds
		if (multiplier.value == 1) {
			this.multiplier = null; // removing the multiplier, if neutral, to make the tree consistent (null used as a missing term)
			WordedNumber fittingUnits = NumberDictionary.getFittingUnit(number);
			mantissa = fittingUnits.value;
			remainder = number - mantissa;
		}
		else {
			mantissa = number / this.multiplier.value;
			remainder = number % this.multiplier.value;
		}
		
		WordedNumber exactUnits = NumberDictionary.getExactUnit(mantissa);
		if (null == exactUnits) {
			this.left = new NumberTreeNode(mantissa);
		}
		else {
			this.units = exactUnits; // no left branch
		}
		
		if (remainder != 0) {
			this.right = new NumberTreeNode(remainder);
		}
	}
	
	public List<WordedNumber> toList(List<WordedNumber> list) {
		if (left != null) {
			left.toList(list);
		}
		if (units != null && units.value > 0) { 
			list.add(units); 
		}
		if (multiplier != null && multiplier.value > 0) {
			list.add(multiplier);
		}
		if (right != null) {
			right.toList(list);
		}
		return list;
	}
	
	@Override
	public String toString() {
		List<WordedNumber> list = new ArrayList<>();
		list = this.toList(list);
		list = this.insertPreposition(list);
		return list.stream().map(WordedNumber::toString).collect(Collectors.joining(" "));
	}
	
	private List<WordedNumber> insertPreposition (List<WordedNumber> list) {
		// Note: 
		// My take on English grammar is that we add the "and" preposition only once, 
		// before the tail of the worded number, if the tail is made of a continuous 
		// sequence of max two numbers
		int count = 0;
		for (int i = list.size() - 1; i > 0; i--) {
			boolean canUse = list.get(i).canUsePreposition;
			// give we started finding preposition items and "interrupted", we stop looking
			if (!canUse && count > 0) {
				list.add(i+1, WordedNumber.getPreposition());
				break;
			}
			else if (count <= 2 && canUse) {
				count++;
			}
		}
		return list;
	}
	
}
