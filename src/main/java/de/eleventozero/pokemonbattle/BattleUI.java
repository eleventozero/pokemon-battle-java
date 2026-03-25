package de.eleventozero.pokemonbattle;

/**
 * Handles the console-base battle user interface.
 * Responsible for displaying battle information, reading player choices,
 * and running the battle loop.
 */
public class BattleUI {

	private final InputHandler INPUTHANDLER;

	public BattleUI() {
		this.INPUTHANDLER = new InputHandler();
	}

	public void startBattle( BattleParticipant player, BattleParticipant enemy ) {
		GameFlow gameFlow = new GameFlow( player, enemy );

		System.out.println( "Welcome to Pokemon Battle!" );

		while ( !gameFlow.isGameOver() ) {
			showBattleStatus( gameFlow );

			if ( gameFlow.isPlayerTurn() ) {
				Pokemon activePokemon = gameFlow.getPlayer().getActivePokemon();

				showAttacks( activePokemon );

				int attackIndex = askPlayerForAttack( activePokemon );

				gameFlow.turn( attackIndex );
			} else {
				System.out.println( "Enemy's turn..." );
				gameFlow.turn( -1 );
			}

			System.out.println();
		}

		showWinner( gameFlow );
	}

	private void showBattleStatus( GameFlow gameFlow ) {
		Pokemon playerPokemon = gameFlow.getPlayer().getActivePokemon();
		Pokemon enemyPokemon = gameFlow.getEnemy().getActivePokemon();

		System.out.println( "Player: " + playerPokemon.getName()
				+ " | HP: " + playerPokemon.getCurrentHp() );
		System.out.println( "Enemy: " + enemyPokemon.getName()
				+ " | HP: " + enemyPokemon.getCurrentHp() );
		System.out.println();
	}

	/**
	 * Displays all attacks of the active Pokemon.
	 *
	 * @param pokemon active player Pokemon
	 */
	private void showAttacks(Pokemon pokemon){
		System.out.println("Choose an attack:");

		Attack[] attacks = pokemon.getAttacks();

		for (int i = 0; i < attacks.length; i++){
			System.out.println(i + " - " + attacks[i].getName());
		}
	}

	private int askPlayerForAttack(Pokemon pokemon){
		int maxIndex = pokemon.getAttacks().length -1;
		System.out.print("> ");
		return INPUTHANDLER.readIntInRange(0, maxIndex);
	}

	private void showWinner(GameFlow gameFlow) {
		if (gameFlow.getEnemy().getActivePokemon().isFainted()) {
			System.out.println("Congratulations, you win!");
		} else {
			System.out.println("You were defeated!");
		}
	}
}