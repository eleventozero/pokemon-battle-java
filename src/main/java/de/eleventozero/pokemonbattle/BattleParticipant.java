package de.eleventozero.pokemonbattle;

/**
 * Represents a participant in a battle (player or enemy).
 * Holds the currently active Pokemon.
 */

public class BattleParticipant {

	private final Pokemon[] team;
	private int activeIndex;

	public BattleParticipant(Pokemon[] team){
		this.team = team;
		this.activeIndex = 0;
	}

	public Pokemon getActivePokemon(){
		return team[activeIndex];
	}

	public Pokemon[] getTeam() {
		return team;
	}

	public int getActiveIndex() {
		return activeIndex;
	}
	public boolean hasRemainingPokemon() {
		for (Pokemon p : team ) {
		} }
}

