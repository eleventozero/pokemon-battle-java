package de.eleventozero.pokemonbattle;

import java.util.Scanner;

public class InputHandler {

	private final Scanner SCANNER;

	public InputHandler() {
		this.SCANNER = new Scanner( System.in);
	}

	/**
	 * Reads an integer input from the user within a specified range.
	 *
	 * @param min minimum allowed value ( inclusive )
	 * @param max maximum allowed value ( inclusive )
	 * @return a valid integer within the specific range
	 */
	public int readIntInRange( int min, int max ) {
		int input;

		while ( true ) {
			if ( SCANNER.hasNextInt() ) { // checks if the next input is a valid integer
				input = SCANNER.nextInt();

				if(input >= min && input <= max) {
					return input;
				}

				System.out.println("Enter a number between " + min + " and " + max);
				System.out.println("> ");
			} else {
				System.out.println("Invalid input. Enter a number.");
				System.out.println("> ");
				SCANNER.next();
			}
		}
	}
}
