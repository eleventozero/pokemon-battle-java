package de.eleventozero.pokemonbattle;

public class testPokemonData {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));

		PokemonProfile p = PokemonData.loadPokemon(1);
		System.out.println(p.getName());
	}
}
