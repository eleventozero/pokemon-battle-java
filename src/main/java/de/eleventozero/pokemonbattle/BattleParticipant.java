package de.eleventozero.pokemonbattle;

/**
 * Represents a participant in a battle (player or enemy).
 * Holds the currently active Pokemon.
 */

public class BattleParticipant {

	private Pokemon activePokemon;

	public BattleParticipant(Pokemon activePokemon){
		this.activePokemon = activePokemon;
	}

	// Getter
	public Pokemon getActivePokemon(){
		return activePokemon;
	}

	// Setter
	public void setActivePokemon(Pokemon activePokemon){
		this.activePokemon = activePokemon;
	}


}
