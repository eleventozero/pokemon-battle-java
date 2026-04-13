package de.eleventozero.pokemonbattle;

/**
 * Represents a Pokemon object in a battle.
 * Holds a reference to its immutable profile and mutable HP.
 */
public class Pokemon {

	private final PokemonProfile profile;
	private int currentHp;

	/**
	 * Creates a Pokemon from a given profile.
	 *
	 * @param profile base data of the Pokemon
	 */
	public Pokemon( PokemonProfile profile ) {
		this.profile = profile;
		this.currentHp = profile.getMaxHp();
	}

	/**
	 * Updates HP while ensuring it does not fall below zero.
	 */
	public void setCurrentHp(int hp){
		this.currentHp = Math.max(0, hp);
	}

	public boolean isFainted(){
		return currentHp <= 0;
	}

	// Getter
	public String getName() {
		return profile.getName();
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public int getMaxHp() {
		return profile.getMaxHp();
	}

	public int getAttack() {
		return profile.getAttack();
	}

	public int getDefense() {
		return profile.getDefense();
	}

	public String getType() {
		return profile.getType();
	}

	public Attack[] getAttacks() {
		return profile.getAttacks();
	}

}