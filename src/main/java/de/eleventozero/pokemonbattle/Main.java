package de.eleventozero.pokemonbattle;


// Strg+Alt+l
public class Main {

	public static void main( String[] args ) {
		BattleUI.printLine( );
		System.out.println( BattleUI.WELCOME );
		BattleUI.printLine( );
		Pokemon test = new Pokemon( 0 );
		System.out.println( test.getName( ) );
	}


}
