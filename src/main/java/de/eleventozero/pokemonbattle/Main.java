package de.eleventozero.pokemonbattle;
// Strg+Alt+l

public class Main {

	public static void main( String[] args ) {
		InputHandler input = new InputHandler();
		MainMenu menu = new MainMenu(input);
		menu.show();
	}


}
