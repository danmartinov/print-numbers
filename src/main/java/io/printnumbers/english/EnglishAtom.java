package io.printnumbers.english;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import io.printnumbers.interfaces.NumberAtom;

/**
 * Class (immutable) holding info on how to translate a number.
 * The boolean "canUsePreposition" is used to decide if the preposition "and" 
 * should be used in front of the word if the context is appropriate.
 * 
 * The overloaded getter "getWord" will add the preposition if the "usePreposition"
 * parameter is true.
 * 
 * @author dan
 *
 */
public class EnglishAtom implements NumberAtom {
	public static final String preposition = "and";
	
	private final int value;
	private final String word;
	private final boolean canUsePreposition;
	
	private static final Map<Integer, EnglishAtom> units = new TreeMap<Integer, EnglishAtom>(Collections.reverseOrder());
	private static final Map<Integer, EnglishAtom> multipliers = new TreeMap<Integer, EnglishAtom>(Collections.reverseOrder());
	private static final EnglishAtom prepositionAtom;
	
	static {
      units.put(-1, new EnglishAtom(-1, "minus", false));
      units.put(0, new EnglishAtom(0, "zero", false));
      units.put(1, new EnglishAtom(1, "one", true));
      units.put(2, new EnglishAtom(2, "two", true));
      units.put(3, new EnglishAtom(3, "three", true));
      units.put(4, new EnglishAtom(4, "four", true));
      units.put(5, new EnglishAtom(5, "five", true));
      units.put(6, new EnglishAtom(6, "six", true));
      units.put(7, new EnglishAtom(7, "seven", true));
      units.put(8, new EnglishAtom(8, "eight", true));
      units.put(9, new EnglishAtom(9, "nine", true));
      units.put(10, new EnglishAtom(10, "ten", true));
      units.put(11, new EnglishAtom(11, "eleven", true));
      units.put(12, new EnglishAtom(12, "twelve", true));
      units.put(13, new EnglishAtom(13, "thirteen", true));
      units.put(14, new EnglishAtom(14, "fourteen", true));
      units.put(15, new EnglishAtom(15, "fifteen", true));
      units.put(16, new EnglishAtom(16, "sixteen", true));
      units.put(17, new EnglishAtom(17, "seventeen", true));
      units.put(18, new EnglishAtom(18, "eighteen", true));
      units.put(19, new EnglishAtom(19, "nineteen", true));
      units.put(20, new EnglishAtom(20, "twenty", true));
      units.put(30, new EnglishAtom(30, "thirty", true));
      units.put(40, new EnglishAtom(40, "forty", true));
      units.put(50, new EnglishAtom(50, "fifty", true));
      units.put(60, new EnglishAtom(60, "sixty", true));
      units.put(70, new EnglishAtom(70, "seventy", true));
      units.put(80, new EnglishAtom(80, "eighty", true));
      units.put(90, new EnglishAtom(90, "ninety", true));
      units.put(100, new EnglishAtom(100, "hundred", false));
      units.put(1_000, new EnglishAtom(1_000, "thousand", false));
      units.put(1_000_000, new EnglishAtom(1_000_000, "million", false));
      units.put(1_000_000_000, new EnglishAtom(1_000_000_000, "billion", false));
      
      multipliers.put(100, new EnglishAtom(100, "hundred", false));
      multipliers.put(1_000, new EnglishAtom(1_000, "thousand", false));
      multipliers.put(1_000_000, new EnglishAtom(1_000_000, "million", false));
      multipliers.put(1_000_000_000, new EnglishAtom(1_000_000_000, "billion", false));
      
      prepositionAtom = new EnglishAtom(0, EnglishAtom.preposition, false);
	}
	
	/**
	 * Hidden no-params constructor. Should never be used.
	 */
	private EnglishAtom() {
		this.value = 0;
		this.word = "zero";
		this.canUsePreposition = false;
	}
	
	/**
	 * Hidden all-params constructor. 
	 * Used internally to create the immutable maps from where the factory serves instances.
	 * 
	 * @param value
	 * @param word
	 * @param canUsePreposition
	 */
	private EnglishAtom(int value, String word, boolean canUsePreposition) {
		this.value = value;
		this.word = word;
		this.canUsePreposition = canUsePreposition;
	}
	
	/**
	 * Factory method providing an instance from the map of units.
	 * 
	 * @param number
	 * @return
	 */
	public static EnglishAtom of(int number) { 
		return units.get(number); 
	}
	
	/**
	 * Factory method providing the instance of the highest matching unit.
	 * 
	 * @param number
	 * @return
	 */
	public static EnglishAtom matchingUnitOf(int number) { 
		return units.entrySet().stream()
				.filter(e -> e.getKey() <= Math.abs(number))
				.findFirst()
				.map(e -> e.getValue())
				.orElse(null);
	}
	
	/**
	 * Factory method providing an instance from the map of units.
	 * 
	 * @param number
	 * @return
	 */
	public static EnglishAtom unitOf(int number) { 
		return units.get(number); 
	}
	
	/**
	 * Factory method providing the instance of the highest matching multiplier.
	 * 
	 * @param number
	 * @return
	 */
	public static EnglishAtom multiplierOf(int number) { 
		return multipliers.entrySet().stream()
				.filter(e -> Math.abs(number / e.getKey()) >= 1)
				.findFirst()
				.map(e -> e.getValue())
				.orElse(null);
	}
	
	@Override
	public String toString() {
		return getWord();
	}

	public static EnglishAtom getPrepositionAtom() {
		return prepositionAtom;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(this.getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EnglishAtom) {
			return this.getValue() == ((NumberAtom)obj).getValue();
		}
		else {
			return false;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return this;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String getWord() {
		return this.word;
	}

	@Override
	public boolean canUsePreposition() {
		return this.isCanUsePreposition();
	}

	@Override
	public String getPreposition() {
		return EnglishAtom.preposition;
	}

	public boolean isCanUsePreposition() {
		return this.canUsePreposition;
	}
	
	
}
