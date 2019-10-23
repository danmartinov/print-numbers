package io.printnumbers;

import java.util.Scanner;

import io.printnumbers.english.EnglishNumberNode;

public class PrintNumbers {
	
	private String translate(int entry) {
		if (entry == 0) {
			return "Zero";
		}
		EnglishNumberNode tree = new EnglishNumberNode(entry);
		String result = tree.toString();
		return capitalize(result);
	}
	
	
	private String capitalize(String sentence) {
		if (sentence.length() == 0) return sentence;
		return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
	}
	
	
	private void initialPrompt() {
		System.out.println(
				String.format(
						"Please enter an integer to transform to words (between %d and %d):", 
						Integer.MIN_VALUE, 
						Integer.MAX_VALUE));
	}
	
	
	private void processInput() {
		Scanner scanner = new Scanner(System.in);
		int number;
		initialPrompt();
    	while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.printf("\"%s\" is not a valid number.\n", input);
            initialPrompt();
        }
        number = scanner.nextInt();
		String words = translate(number);
		System.out.println(words);
        scanner.close();
	}
	
	
	public static void main(String[] args) {
		PrintNumbers printNumbers = new PrintNumbers();
		printNumbers.processInput();
	}

	
}
