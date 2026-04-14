package de.eleventozero.pokemonbattle;

/**
 * Handles the main menu of the game.
 * The player can start different battle modes or exit the program.
 */
public class MainMenu {

	private final InputHandler input;

	/**
	 * Creates a new main menu with the given input handler.
	 *
	 * @param input input handler for reading user input
	 */
	public MainMenu(InputHandler input) {
		this.input = input;
	}

	/**
	 * Shows the main menu in a loop until the player exits the program.
	 */
	public void show() {

		while (true) {
			System.out.println("\n=== Pokemon Battle Menu ===");
			System.out.println("1 - Quick Match");
			System.out.println("2 - 1 vs 1");
			System.out.println("3 - 3 vs 3");
			System.out.println("4 - Exit");

			int choice = input.readIntInRange(1, 4);

			if (choice == 1) {
				startQuickMatch();
			} else if (choice == 2) {
				startOneVsOne();
			} else if (choice == 3) {
				startThreeVsThree();
			} else {
				System.out.println("No Pokemon were harmed during the exit!");
				return;
			}
		}
	}

	/**
	 * Starts a quick match.
	 * Both the player and the enemy receive a random 1-Pokemon team.
	 */
	private void startQuickMatch() {
		System.out.println("\nStarting Quick Match...");

		Pokemon[] playerTeam = PokemonData.loadRandomPokemonTeam(1);
		Pokemon[] enemyTeam = PokemonData.loadRandomPokemonTeam(1);

		System.out.println("Your Pokemon: " + playerTeam[0].getName());
		System.out.println("Enemy Pokemon: " + enemyTeam[0].getName());

		startBattle(playerTeam, enemyTeam);
	}

	/**
	 * Starts a 1 vs 1 battle.
	 * The player chooses one Pokemon, and the enemy gets one random Pokemon.
	 */
	private void startOneVsOne() {
		System.out.println("\nStarting 1 vs 1...");

		Pokemon[] playerTeam = choosePokemonTeam(1);
		Pokemon[] enemyTeam = PokemonData.loadRandomPokemonTeam(1);

		System.out.println("Enemy Pokemon: " + enemyTeam[0].getName());

		startBattle(playerTeam, enemyTeam);
	}

	/**
	 * Starts a 3 vs 3 battle.
	 * The player chooses three Pokemon, and the enemy gets three random Pokemon.
	 */
	private void startThreeVsThree() {
		System.out.println("\nStarting 3 vs 3...");

		Pokemon[] playerTeam = choosePokemonTeam(3);
		Pokemon[] enemyTeam = PokemonData.loadRandomPokemonTeam(3);

		System.out.println("Enemy team:");
		for (Pokemon pokemon : enemyTeam) {
			System.out.println("- " + pokemon.getName());
		}

		startBattle(playerTeam, enemyTeam);
	}

	/**
	 * Lets the player choose a team of a given size.
	 * Duplicate Pokemon choices are not allowed.
	 *
	 * @param size number of Pokemon the player must choose
	 * @return the chosen Pokemon team
	 */
	private Pokemon[] choosePokemonTeam(int size) {
		int[] availableIds = PokemonData.loadAllPokemonIds();
		Pokemon[] team = new Pokemon[size];
		boolean[] used = new boolean[availableIds.length];

		System.out.println("Available Pokemon IDs:");
		for (int id : availableIds) {
			System.out.println("- " + id);
		}

		for (int i = 0; i < size; i++) {
			System.out.println("\nChoose Pokemon " + (i + 1) + " of " + size + ":");

			while (true) {
				int chosenId = askForPokemonId(availableIds);
				int arrayIndex = findIdIndex(availableIds, chosenId);

				if (used[arrayIndex]) {
					System.out.println("You already chose that Pokemon.");
					continue;
				}

				used[arrayIndex] = true;
				team[i] = new Pokemon(PokemonData.loadPokemon(chosenId));

				System.out.println("You chose: " + team[i].getName());
				break;
			}
		}

		return team;
	}

	/**
	 * Reads a Pokemon id from the player until a valid id is entered.
	 *
	 * @param availableIds all valid Pokemon ids from the database
	 * @return a valid chosen Pokemon id
	 */
	private int askForPokemonId(int[] availableIds) {
		while (true) {
			System.out.println("Enter a Pokemon ID:");
			int chosenId = input.readIntInRange(1, 9999);

			if (isValidPokemonId(availableIds, chosenId)) {
				return chosenId;
			}

			System.out.println("Invalid Pokemon ID.");
		}
	}

	/**
	 * Checks whether a given id exists in the list of available Pokemon ids.
	 *
	 * @param availableIds all valid Pokemon ids
	 * @param chosenId     the id entered by the player
	 * @return true if the id exists, otherwise false
	 */
	private boolean isValidPokemonId(int[] availableIds, int chosenId) {
		for (int id : availableIds) {
			if (id == chosenId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the array index of a given Pokemon id.
	 *
	 * @param availableIds all valid Pokemon ids
	 * @param chosenId     the id to search for
	 * @return the index of the id in the array
	 */
	private int findIdIndex(int[] availableIds, int chosenId) {
		for (int i = 0; i < availableIds.length; i++) {
			if (availableIds[i] == chosenId) {
				return i;
			}
		}

		throw new RuntimeException("Pokemon ID not found: " + chosenId);
	}

	/**
	 * Creates battle participants from the given teams and starts the battle UI.
	 *
	 * @param playerTeam the player's team
	 * @param enemyTeam  the enemy's team
	 */
	private void startBattle(Pokemon[] playerTeam, Pokemon[] enemyTeam) {
		BattleParticipant player = new BattleParticipant(playerTeam);
		BattleParticipant enemy = new BattleParticipant(enemyTeam);

		BattleUI battleUI = new BattleUI();
		battleUI.startBattle(player, enemy);
	}
}