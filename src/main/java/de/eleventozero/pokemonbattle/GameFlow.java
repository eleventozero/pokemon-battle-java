package de.eleventozero.pokemonbattle;

/**
 * Controls the flow of the battle.
 * Responsible for handling player actions, enemy turns, turn switching, and checking if the battle is over.
 */
public class GameFlow {

	private boolean gameOver = false;
	private boolean playerTurn = true;

	private BattleParticipant player;
	private BattleParticipant enemy;

	/**
	 * Creates a new game flow with two participants.
	 *
	 * @param player the player participant
	 * @param enemy the enemy participant
	 */
	public GameFlow(BattleParticipant player, BattleParticipant enemy){
		this.player = player;
		this.enemy = enemy;
	}

	/**
	 * Executes the player's attack action.
	 * <p>
	 * After the attack:
	 * - the enemy may faint
	 * - if the enemy still has Pokemon left, it switches to the next available one
	 * - the turn changes to the enemy
	 *
	 * @param attackIndex index of the selected attack
	 */
	public void playerAttack(int attackIndex) {

		// Do nothing if the battle is already over or if it is not the player's turn.
		if (gameOver || !playerTurn) {
			return;
		}

		Pokemon attacker = player.getActivePokemon();
		Pokemon defender = enemy.getActivePokemon();

		Battle.applyAttack(attacker, defender, attackIndex);

		// If the enemy's active Pokemon fainted, check whether the enemy still has usable Pokemon left.
		// If yes, switch to the next one.
		if (defender.isFainted()) {

			if (!enemy.hasRemainingPokemon()) {
				gameOver = true;
				return;
			}

			// Enemy switches to the first available non-fainted Pokemon.
			for (int i = 0; i < enemy.getTeam().length; i++) {
				if (enemy.switchPokemon(i)) {
					break;
				}
			}
		}

		// End the player's turn.
		playerTurn = false;
	}

	/**
	 * Executes the player's switch action.
	 * <p>
	 * If the switch is successful, the turn ends and the enemy's turn begins.
	 *
	 * @param newIndex index of the Pokemon to switch to
	 * @return true if the switch was successful, otherwise false
	 */
	public boolean playerSwitch(int newIndex) {

		// A switch is only possible during the player's turn and while the game is active.
		if (gameOver || !playerTurn) {
			return false;
		}

		boolean switched = player.switchPokemon(newIndex);

		// A successful switch counts as the player's action for this turn.
		if (switched) {
			playerTurn = false;
		}

		return switched;
	}

	/**
	 * Executes the enemy's turn.
	 * <p>
	 * The enemy always uses a random attack.
	 * If the player's active Pokemon faints, the battle only ends if the player
	 * has no remaining Pokemon left. Otherwise, the UI must let the player choose
	 * the next Pokemon manually.
	 */
	public void enemyTurn() {

		// Do nothing if the battle is over or if it is not the enemy's turn.
		if (gameOver || playerTurn) {
			return;
		}

		Pokemon attacker = enemy.getActivePokemon();
		Pokemon defender = player.getActivePokemon();

		// Choose a random attack from the enemy's available attacks.
		int attackIndex = (int) (Math.random() * attacker.getAttacks().length);

		Battle.applyAttack(attacker, defender, attackIndex);

		// If the player's active Pokemon fainted, only end the battle
		// if no usable Pokemon are left in the player's team.
		if (defender.isFainted() && !player.hasRemainingPokemon()) {
			gameOver = true;
			return;
		}

		// End the enemy's turn.
		playerTurn = true;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public BattleParticipant getPlayer() {
		return player;
	}

	public BattleParticipant getEnemy() {
		return enemy;
	}

}
