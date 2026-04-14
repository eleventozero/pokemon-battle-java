package de.eleventozero.pokemonbattle;

/**
 * Handles the console-based battle user interface.
 * Responsible for displaying battle information, reading player choices,
 * and running the battle loop.
 */
public class BattleUI {

	private final InputHandler inputHandler;

	/**
	 * Creates a new battle UI with a default input handler.
	 */
	public BattleUI() {
		this.inputHandler = new InputHandler();
	}

	/**
	 * Starts and runs a battle between the player and the enemy.
	 *
	 * @param player the player participant
	 * @param enemy  the enemy participant
	 */
	public void startBattle(BattleParticipant player, BattleParticipant enemy) {
		GameFlow gameFlow = new GameFlow(player, enemy);

		System.out.println("Welcome to Pokemon Battle!");

		while (!gameFlow.isGameOver()) {
			showBattleStatus(gameFlow);

			if (gameFlow.isPlayerTurn()) {

				// If the active player Pokemon has fainted, the player must choose
				// another usable Pokemon before continuing.
				if (gameFlow.getPlayer().getActivePokemon().isFainted()) {
					handleForcedSwitch(gameFlow);
				} else {
					boolean continueBattle = handlePlayerTurn(gameFlow);

					// Exit battle if the player chose to leave.
					if (!continueBattle) {
						System.out.println("Battle ended.");
						return;
					}
				}

			} else {
				System.out.println("Enemy's turn...");
				gameFlow.enemyTurn();
			}

			System.out.println();
		}

		showWinner(gameFlow);
	}

	/**
	 * Displays the current battle status of both active Pokemon.
	 *
	 * @param gameFlow current game flow
	 */
	private void showBattleStatus(GameFlow gameFlow) {
		Pokemon playerPokemon = gameFlow.getPlayer().getActivePokemon();
		Pokemon enemyPokemon = gameFlow.getEnemy().getActivePokemon();

		System.out.println(
				"Player: " + playerPokemon.getName()
						+ " | HP: " + playerPokemon.getCurrentHp() + "/" + playerPokemon.getMaxHp());

		System.out.println(
				"Enemy: " + enemyPokemon.getName()
						+ " | HP: " + enemyPokemon.getCurrentHp() + "/" + enemyPokemon.getMaxHp());

		System.out.println();
	}

	/**
	 * Handles the player's normal turn.
	 * The player can choose between attack, switch, and exit.
	 *
	 * @param gameFlow current game flow
	 * @return true if the battle should continue, false if the player exits
	 */
	private boolean handlePlayerTurn(GameFlow gameFlow) {
		while (true) {
			System.out.println("Choose an action:");
			System.out.println("1 - Attack");
			System.out.println("2 - Switch Pokemon");
			System.out.println("3 - Exit Battle");

			int choice = inputHandler.readIntInRange(1, 3);

			// Attack
			if (choice == 1) {
				Pokemon activePokemon = gameFlow.getPlayer().getActivePokemon();
				showAttacks(activePokemon);
				int attackIndex = askPlayerForAttack(activePokemon);
				gameFlow.playerAttack(attackIndex);
				return true;
			}

			// Switch Pokemon
			if (choice == 2) {
				boolean switched = handlePlayerSwitch(gameFlow);

				// Only end the player's turn if the switch was successful.
				if (switched) {
					return true;
				}
			}

			// Exit Battle
			if (choice == 3) {
				return false;
			}
		}
	}

	/**
	 * Forces the player to switch Pokemon after the active Pokemon has fainted.
	 *
	 * @param gameFlow current game flow
	 */
	private void handleForcedSwitch(GameFlow gameFlow) {
		System.out.println("Your Pokemon has fainted.");
		System.out.println("Choose another Pokemon:");

		while (true) {
			showTeam(gameFlow.getPlayer());

			int switchIndex = askPlayerForSwitch(gameFlow.getPlayer());

			if (gameFlow.forcePlayerSwitch(switchIndex)) {
				System.out.println("Go, " + gameFlow.getPlayer().getActivePokemon().getName() + "!");
				return;
			}

			System.out.println("You cannot switch to that Pokemon.");
		}
	}

	/**
	 * Lets the player choose another Pokemon manually during the turn.
	 *
	 * @param gameFlow current game flow
	 * @return true if the switch was successful, otherwise false
	 */
	private boolean handlePlayerSwitch(GameFlow gameFlow) {
		System.out.println("Choose a Pokemon to switch to:");
		showTeam(gameFlow.getPlayer());

		int switchIndex = askPlayerForSwitch(gameFlow.getPlayer());

		if (gameFlow.playerSwitch(switchIndex)) {
			System.out.println("Go, " + gameFlow.getPlayer().getActivePokemon().getName() + "!");
			return true;
		}

		System.out.println("You cannot switch to that Pokemon.");
		return false;
	}

	/**
	 * Displays all attacks of the active Pokemon.
	 *
	 * @param pokemon active player Pokemon
	 */
	private void showAttacks(Pokemon pokemon) {
		System.out.println("Choose an attack:");

		Attack[] attacks = pokemon.getAttacks();

		for (int i = 0; i < attacks.length; i++) {
			System.out.println(i + " - " + attacks[i].getName());
		}
	}

	/**
	 * Displays the full player team with status information.
	 *
	 * @param participant the battle participant whose team should be shown
	 */
	private void showTeam(BattleParticipant participant) {
		Pokemon[] team = participant.getTeam();

		for (int i = 0; i < team.length; i++) {
			Pokemon pokemon = team[i];

			String status;
			if (pokemon.isFainted()) {
				status = "FAINTED";
			} else {
				status = pokemon.getCurrentHp() + "/" + pokemon.getMaxHp() + " HP";
			}

			String activeMarker = "";
			if (i == participant.getActiveIndex()) {
				activeMarker = " (active)";
			}

			System.out.println(i + " - " + pokemon.getName() + " | " + status + activeMarker);
		}
	}

	/**
	 * Reads the player's attack choice.
	 *
	 * @param pokemon the active Pokemon
	 * @return chosen attack index
	 */
	private int askPlayerForAttack(Pokemon pokemon) {
		int maxIndex = pokemon.getAttacks().length - 1;
		return inputHandler.readIntInRange(0, maxIndex);
	}

	/**
	 * Reads the player's Pokemon switch choice.
	 *
	 * @param participant the player participant
	 * @return chosen Pokemon index
	 */
	private int askPlayerForSwitch(BattleParticipant participant) {
		return inputHandler.readIntInRange(0, participant.getTeam().length - 1);
	}

	/**
	 * Displays the final battle result.
	 *
	 * @param gameFlow current game flow
	 */
	private void showWinner(GameFlow gameFlow) {
		if (!gameFlow.getEnemy().hasRemainingPokemon()) {
			System.out.println("Congratulations, you win!");
		} else {
			System.out.println("You were defeated!");
		}
	}
}