package de.eleventozero.pokemonbattle;

/**
 * Starts or exits the game.
 */

public class MainMenu {

	// Fields
	private InputHandler input;

	public void show(){
		System.out.println(BattleUI.WELCOME);
		System.out.println("\n[1] Start Battle\n[2] Exit" );
		int choice = input.readInput();

	}

	public MainMenu (InputHandler input){
		this.input = input;
	}

}
