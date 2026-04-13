package de.eleventozero.pokemonbattle;

/**
 * Starts or exits the game.
 */

public class MainMenu {

	private final InputHandler input;

	public MainMenu (InputHandler input){
		this.input = input;
	}

	public void show(){

		while( true ) {
			System.out.println( "\n[1] Start Battle\n[2] Exit" );
			int choice = input.readIntInRange( 1, 2 );

			if ( choice == 1 ) {
				startBattle();
			} else {
				System.out.println( "No Pokemon were harmed during the exit!" );
				return; // end
			}
		}
	}

	private void startBattle() {

	}



}
