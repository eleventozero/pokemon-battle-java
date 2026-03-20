package de.eleventozero.pokemonbattle;

/**
 * Represents immutable base data of a Pokemon.
 * This data is loaded from the database (PokemonData).
 */

public class PokemonProfile {

	private final String name;
	private final int maxHp;
	private final int attack;
	private final int defense;
	private String type;
	private final Attack[] attacks;

	/**
	 * Creates a new Pokemon profile.
	 *
	 * @param name    name of the Pokemon
	 * @param maxHp   maximum HP value
	 * @param attack  base attack stat
	 * @param defense base defense stat
	 * @param type    Pokemon type (e.g. "fire")
	 * @param attacks array of attacks (expected size = 3)
	 */
	public PokemonProfile( String name, int maxHp, int attack, int defense, String type, Attack[] attacks ) {
		this.name = name;
		this.maxHp = maxHp;
		this.attack = attack;
		this.defense = defense;
		this.type = type;
		this.attacks = attacks;
	}

	// Getter
	public String getName( ) {
		return name;
	}

	public int getMaxHp( ) {
		return maxHp;
	}

	public int getAttack( ) {
		return attack;
	}

	public int getDefense( ) {
		return defense;
	}

	public String getType( ) {
		return type;
	}

	public Attack[] getAttacks(){
		return attacks;
	}
}
