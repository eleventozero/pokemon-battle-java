package de.eleventozero.pokemonbattle;

/**
 * Controls the flow of the battle.
 * Responsible for turn execution and game loop.
 */
public class GameFlow {

	private boolean gameover = false;
	private boolean playerTurn = true;

	private BattleParticipant player;
	private BattleParticipant enemy;

	/**
	 * Creates a new game flow with two participants.
	 *
	 * @param player player participant
	 * @param enemy enemy participant
	 */
	public GameFlow(BattleParticipant player, BattleParticipant enemy){
		this.player = player;
		this.enemy = enemy;
	}

	/**
	 * Executes one turn of the game loop.
	 */
	public void turn(){

		if (gameover) return;

		if (playerTurn) {
			executePlayerTurn();

			// Check if enemy fainted.
			if (enemy.getActivePokemon().isFainted()){
				gameover = true;
				return;
			}
		} else {
			executeEnemyTurn()

			// Check if player is fainted.
			if (player.getActivePokemon().isFainted()){
				gameover = true;
				return;
			}
		}

		// Switch turn after action
		playerTurn = !playerTurn;
	}

	public void executePlayerTurn(){

		Pokemon attacker = player.getActivePokemon();
		Pokemon defender = enemy.getActivePokemon();

		int attackIndex = getPlayerChoice(); // external input

		Battle.applyAttack(attacker, defender, attackIndex);
	}

	/**
	 * Executes enemy's turn.
	 * Chooses a random attack.
	 */
	public void executeEnemyTurn(){

		Pokemon attacker = enemy.getActivePokemon();
		Pokemon defender = player.getActivePokemon();

		int attackIndex = (int) Math.random() * 3;

		Battle.applyAttack(attacker, defender, attackIndex);
	}

	// Getter
	public boolean isGameOver(){
		return gameover;
	}

	public boolean isPlayerTurn(){
		return playerTurn;
	}

	public BattleParticipant getPlayer(){
		return player;
	}

	public BattleParticipant getEnemy(){
		return enemy;
	}
}
