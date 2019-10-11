package com.sonatype.interview.printnumbers;

import java.util.Scanner;

public class PrintNumbers {
	private static final Scanner scanner = new Scanner(System.in);
	
	// The translation method looks for exceptional cases like Zero or negative numbers, which do not belong in the general 
	// decomposition algorithm. It is easier to maintain the more general decomposition if these exceptions are treated separately in a wrapper.
	public static String translate(int entry) {
		boolean negative = false;
		if (entry == 0) {
			return "Zero";
		}
		if (entry < 0) {
			entry = Math.abs(entry);
			negative = true;
		}
		
		NumberTreeNode tree = new NumberTreeNode(entry);
		
		String result = tree.toString();
		if (negative) {
			result = "minus " + result;
		}
		return capitalize(result);
	}
	
	
	private static String capitalize(String sentence) {
		if (sentence.length() == 0) return sentence;
		return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
	}
	

	public static void main(String[] args) {
		System.out.println(
				String.format(
						"Please enter the integer to transform to words (between %d and %d):", 
						Integer.MIN_VALUE, 
						Integer.MAX_VALUE));
		
        int n = scanner.nextInt();
        // disconsider extraneous CR-LF
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String words = translate(n);
        System.out.println(words);
        scanner.close();
	}

	
}
