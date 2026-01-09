package de.eleventozero.pokemonbattle;

/**
 * PokemonProfile is used as a Data-Type for Pokemon.
 */

public class PokemonProfile {

	// Fields
	private String name;
	private String type;
	private int maxHp;
	private int attack;
	private int defense;
	//private Move[] moves;

	// Constructor
	public PokemonProfile( String name, String type, int maxHp, int attack, int defense ) {
		this.name = name;
		this.type = type;
		this.maxHp = maxHp;
		this.attack = attack;
		this.defense = defense;
	}

	// Getter
	public String getName( ) { return name; }
	public String getType( ) { return type; }
	public int getMaxHp( ) { return maxHp; }
	public int getAttack( ) { return attack; }
	public int getDefense( ) { return defense; }
}
