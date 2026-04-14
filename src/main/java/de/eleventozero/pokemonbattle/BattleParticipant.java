package de.eleventozero.pokemonbattle;

/**
 * Represents a participant in a battle ( player or enemy ).
 * <p>
 * Responsibilities:
 * - storing the Pokemon team
 * - keeping track of the currently active Pokemon
 * - checking if usable Pokemon are still available
 * - switching to another Pokemon
 */
public class BattleParticipant {

	private final Pokemon[] team;
	private int activeIndex;

	/**
	 * Creates a battle participant with a given team.
	 * The first Pokemon in the array starts as the active Pokemon.
	 *
	 * @param team the participant's Pokemon team
	 */
	public BattleParticipant( Pokemon[] team ) {
		this.team = team;
		this.activeIndex = 0;
	}

	public Pokemon getActivePokemon() {
		return team[ activeIndex ];
	}

	public Pokemon[] getTeam() {
		return team;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public boolean hasRemainingPokemon() {
		for ( Pokemon p : team ) {
			if ( !p.isFainted() ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tries to switch to another Pokemon in the team.
	 *
	 * A switch is only allowed if:
	 * - the index is inside the team range
	 * - the chosen Pokemon is not already active
	 * - the chosen Pokemon has not fainted
	 *
	 * @param newIndex index of the Pokemon to switch to
	 * @return true if the switch was successful, otherwise false
	 */
	public boolean switchPokemon(int newIndex) {

		// Index must point to a valid team position.
		if (newIndex < 0 || newIndex >= team.length) {
			return false;
		}

		// Cannot switch to the Pokemon that is already active.
		if (newIndex == activeIndex) {
			return false;
		}

		// Cannot switch to a fainted Pokemon.
		if (team[newIndex].isFainted()) {
			return false;
		}

		// Switch successful.
		activeIndex = newIndex;
		return true;
	}

 }

