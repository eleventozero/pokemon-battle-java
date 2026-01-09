package de.eleventozero.pokemonbattle;

/**
 * PokemonStats is used as a datatype for Pokemon.
 */

public class PokemonStats {

	// Fields
	public String name;
	public String type;
	public int hp;
	public int attack;
	public int defense;

	// Constructor
	public PokemonStats( String name, String type, int hp, int attack, int defense ) {
		this.name = name;
		this.type = type;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}

}
