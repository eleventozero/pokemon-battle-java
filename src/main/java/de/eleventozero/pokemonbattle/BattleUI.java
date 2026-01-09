package de.eleventozero.pokemonbattle;

public class Messages {

	// Fields
	public static final String WELCOME = "Welcome to Pokemon Battle!";
	public static final String END_WIN = "You defeated Pokemon Trainer Java.";
	public static final String END_LOOSE = "You lost the battle.";

	// Methods
	public static void printLine( ) {
		for ( int i = 0; i < 30; i++ ) {
			System.out.print( "-" );
		}
		System.out.println( );
	}

}