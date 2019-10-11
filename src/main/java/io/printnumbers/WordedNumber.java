package io.printnumbers;

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
public class WordedNumber {
	public static final String preposition = "and";
	public static final String minus = "minus";
	
	public final int value;
	public final String word;
	public final boolean canUsePreposition;
	
	public WordedNumber(int value, String word, boolean canUsePreposition) {
		this.value = value;
		this.word = word;
		this.canUsePreposition = canUsePreposition;
	}
	
	public String getWord(boolean usePreposition) {
		return (canUsePreposition && usePreposition) ? "and " + word : word;
	}

	@Override
	public String toString() {
		return word;
	}

	public static WordedNumber getPreposition() {
		return new WordedNumber(0, WordedNumber.preposition, false);
	}

	public static WordedNumber getMinus() {
		return new WordedNumber(0, WordedNumber.minus, false);
	}
}
