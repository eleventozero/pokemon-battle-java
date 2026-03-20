package de.eleventozero.pokemonbattle;

/**
 * Represents a battle move.
 */
public class Attack {

	private final String name;
	private final int damage;
	private final String type;

	/**
	 * Creates a new attack.
	 *
	 * @param name   : name of the attack
	 * @param damage : base damage value
	 * @param type   : attack type (e.g. "fire")
	 */
	public Attack(String name, int damage, String type){
		this.name = name;
		this.damage = damage;
		this.type = type;
	}

	// Getter
	public String getName(){ return name;}
	public int getDamage(){ return damage;}
	public String getType(){ return type;}
}
