package de.eleventozero.pokemonbattle;

import java.util.Scanner;

public class InputHandler {

	// Fields
	private final Scanner SCANNER = new Scanner( System.in );

	// Constructor
	public int readInput( ) {
		return SCANNER.nextInt( );
	}
}
