package io.printnumbers.interfaces;

/**
 * Interface to be implemented for each language.
 * The implementing class will have to implement the factory methods and prevent the use of constructors.
 * Since the set of instances for each language is finite, an immutable map should be preferred.
 * 
 * @author dan
 *
 */
public interface NumberAtom {
	
	int getValue();
	
	String getWord();
	
	/**
	 * Returns the worded number prepended with a preposition if required, and if the atom allows it.
	 * 
	 * @param withPreposition
	 * @return
	 */
	default String getWord(boolean withPreposition) { 
		return withPreposition && canUsePreposition() ? getPreposition() + " " + getWord() : getWord(); 
	}
	
	/**
	 * Returns true if the atom can be preceded by a preposition.
	 * 
	 * @return
	 */
	boolean canUsePreposition();
	
	/**
	 * Returns the preposition for the specific language
	 * @return
	 */
	String getPreposition();

	/**
	 * Factory method returning the largest multiplier atom corresponding to the number, 
	 * if it exists, or null otherwise.
	 * 
	 * @param number
	 * @return
	 */
	static NumberAtom multiplierOf(int number) { return null; }


	/**
	 * Factory method returning the unit atom corresponding to the number, 
	 * if it exists, or null otherwise.
	 * 
	 * @param number
	 * @return
	 */
	static NumberAtom unitOf(int number) { return null; }

	/**
	 * Factory method returning the largest unit atom corresponding to the number, 
	 * if it exists, or null otherwise.
	 * 
	 * @param number
	 * @return
	 */
	static NumberAtom matchingUnitOf(int number) { return null; }

	/**
	 * Factory method returning the unit atom corresponding to the number, 
	 * if it exists, or null otherwise.
	 * 
	 * @param number
	 * @return
	 */
	static NumberAtom of(int number) { return null; }
}