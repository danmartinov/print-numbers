package io.printnumbers;

import java.util.Collections;
import java.util.TreeMap;

/**
 * This is a class that can not be instantiated, containing the maps to use when translating the numbers into words.
 * The maps are backed by trees to keep have them sorted in descending order. This is used to get the highest fitting
 * unit or multiplier in a given number.
 * 
 * The separation between units and multipliers is due to the representation in words, being compounded or not. 
 * Ex: "forty" is a unit, "forty thousand" is compounded with a multiplier.
 * 
 * The "units
 * 
 * @author dan
 *
 */
public class NumberDictionary {
	private static final TreeMap<Integer, WordedNumber> units = new TreeMap<>(Collections.reverseOrder());
	private static final TreeMap<Integer, WordedNumber> multipliers = new TreeMap<>(Collections.reverseOrder());	
	
	static {
        units.put(0, new WordedNumber(0, "zero", false));
        units.put(1, new WordedNumber(1, "one", true));
        units.put(2, new WordedNumber(2, "two", true));
        units.put(3, new WordedNumber(3, "three", true));
        units.put(4, new WordedNumber(4, "four", true));
        units.put(5, new WordedNumber(5, "five", true));
        units.put(6, new WordedNumber(6, "six", true));
        units.put(7, new WordedNumber(7, "seven", true));
        units.put(8, new WordedNumber(8, "eight", true));
        units.put(9, new WordedNumber(9, "nine", true));
        units.put(10, new WordedNumber(10, "ten", true));
        units.put(11, new WordedNumber(11, "eleven", true));
        units.put(12, new WordedNumber(12, "twelve", true));
        units.put(13, new WordedNumber(13, "thirteen", true));
        units.put(14, new WordedNumber(14, "fourteen", true));
        units.put(15, new WordedNumber(15, "fifteen", true));
        units.put(16, new WordedNumber(16, "sixteen", true));
        units.put(17, new WordedNumber(17, "seventeen", true));
        units.put(18, new WordedNumber(18, "eighteen", true));
        units.put(19, new WordedNumber(19, "nineteen", true));
        units.put(20, new WordedNumber(20, "twenty", true));
        units.put(30, new WordedNumber(30, "thirty", true));
        units.put(40, new WordedNumber(40, "forty", true));
        units.put(50, new WordedNumber(50, "fifty", true));
        units.put(60, new WordedNumber(60, "sixty", true));
        units.put(70, new WordedNumber(70, "seventy", true));
        units.put(80, new WordedNumber(80, "eighty", true));
        units.put(90, new WordedNumber(90, "ninety", true));
        units.put(100, new WordedNumber(100, "hundred", false));
        units.put(1_000, new WordedNumber(1_000, "thousand", false));
        units.put(1_000_000, new WordedNumber(1_000_000, "million", false));
        units.put(1_000_000_000, new WordedNumber(1_000_000_000, "billion", false));
        
        multipliers.put(100, new WordedNumber(100, "hundred", false));
        multipliers.put(1_000, new WordedNumber(1_000, "thousand", false));
        multipliers.put(1_000_000, new WordedNumber(1_000_000, "million", false));
        multipliers.put(1_000_000_000, new WordedNumber(1_000_000_000, "billion", false));
	}
	
	private NumberDictionary() {}
	
	public static WordedNumber getFittingUnit(Integer number) {
		return units.entrySet().stream()
				.filter(e -> e.getKey() <= number)
				.findFirst()
				.map(e -> e.getValue())
				.orElse(null);
	}
	
	public static WordedNumber getExactUnit(Integer number) {
		WordedNumber result = units.get(number);
		return result;
	}
	
	public static WordedNumber getFittingMultiplier(Integer number) {
		return multipliers.entrySet().stream()
				.filter(e -> e.getKey() <= number)
				.findFirst()
				.map(e -> e.getValue())
				.orElse(new WordedNumber(1, "", false));
	}
	
}
