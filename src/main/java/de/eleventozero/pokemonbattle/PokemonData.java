package de.eleventozero.pokemonbattle;

import java.sql.*;

/**
 * Handles loading Pokemon data from the SQLite database.
 */

public class PokemonData {

	private static final String DB_URL = "jdbc:sqlite:pokemon.db";

	/**
	 * Loads a PokemonProfile by its id.
	 *
	 * @param id the Pokemon id
	 * @return the loaded PokemonProfile
	 */
	public static PokemonProfile loadPokemon( int id ) {

		// Replace '?' with the actual Pokemon Id.
		String sql = """
				SELECT name, base_hp, base_attack, base_defense, type
				FROM POKEMON
				WHERE id = ? 
				""";

		// try ( with-resources ) -> auto-close ressources
		try ( Connection conn = DriverManager.getConnection( DB_URL ); // Opens DB
			  PreparedStatement stmt = conn.prepareStatement( sql ) ) {

			stmt.setInt( 1, id ); // 1 -> First ? from the query

			ResultSet rs = stmt.executeQuery();

			// Ensure a result exists.
			if ( !rs.next() ) {
				throw new RuntimeException( "Pokemon not found: " + id );
			}

			// Extract base stats from the result.
			String name = rs.getString( "name" );
			int base_hp = rs.getInt( "base_hp" );
			int base_attack = rs.getInt( "base_attack" );
			int base_defense = rs.getInt( "base_defense" );
			String type = rs.getString( "type" );

			// Load attacks using the same connection.
			Attack[] attacks = loadAttacks( conn, id );

			// Create immutable profile.
			return new PokemonProfile( name, base_hp, base_attack, base_defense, type, attacks );

		} catch ( SQLException e ) {
			throw new RuntimeException( "Failed to load Pokemon with id: " + id,  e );
		}
	}

	/**
	 * Loads all attacks assigned to a Pokemon.
	 *
	 * @param conn the active database connection
	 * @param pokemonId the Pokemon id
	 * @return an array of attacks
	 */
	private static Attack[] loadAttacks( Connection conn, int pokemonId ) {

		String sql = """
				SELECT a.name, a.damage, a.type
				FROM ATTACK a
				JOIN USES u ON a.id = u.attack_id
				WHERE u.pokemon_id = ?
				""";

		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {

			stmt.setInt( 1, pokemonId );

			ResultSet rs = stmt.executeQuery();

			Attack[] attacks = new Attack[ 3 ];
			int i = 0;

			// Convert each row into an Attack object.
			while ( rs.next() && i < 3 ) {
				String name = rs.getString( "name" );
				int damage = rs.getInt( "damage" );
				String type = rs.getString( "type" );

				attacks[ i++ ] = new Attack( name, damage, type ); // [ i++ ] starts at 0
			}

			return attacks;
		} catch ( SQLException e ) {
			// Convert checked into unchecked Exception for simpler handling.
			throw new RuntimeException( "Failed to load attacks for Pokemon Id: " + pokemonId, e );
		}
	}

	/**
	 * Loads all Pokemon ids from the database.
	 *
	 * @return an array containing all Pokemon ids
	 */
	public static int[] loadAllPokemonIds() {

		String countSql = "SELECT COUNT(*) FROM POKEMON";
		String idsSql = "SELECT id FROM POKEMON";

		try (Connection conn = DriverManager.getConnection(DB_URL);
			 PreparedStatement countStmt = conn.prepareStatement(countSql);
			 PreparedStatement idsStmt = conn.prepareStatement(idsSql)) {

			ResultSet countRs = countStmt.executeQuery();
			int count = countRs.getInt(1);

			int[] ids = new int[count];
			int index = 0;

			ResultSet idsRs = idsStmt.executeQuery();

			// Copy all ids into the array.
			while (idsRs.next()) {
				ids[index++] = idsRs.getInt("id");
			}

			return ids;

		} catch (SQLException e) {
			throw new RuntimeException("Failed to load Pokemon ids.", e);
		}
	}

	/**
	 * Loads a random Pokemon team without duplicates.
	 *
	 * @param size number of Pokemon in the team
	 * @return an array of randomly selected Pokemon
	 */
	public static Pokemon[] loadRandomPokemonTeam(int size) {

		int[] allIds = loadAllPokemonIds();

		if (size > allIds.length) {
			throw new RuntimeException("Not enough Pokemon in database for a team of size " + size);
		}

		Pokemon[] team = new Pokemon[size];
		boolean[] used = new boolean[allIds.length];

		for (int i = 0; i < size; i++) {
			int randomIndex;

			// Keep rolling until an unused Pokemon id is found.
			do {
				randomIndex = (int) (Math.random() * allIds.length);
			} while (used[randomIndex]);

			used[randomIndex] = true;
			team[i] = new Pokemon(loadPokemon(allIds[randomIndex]));
		}

		return team;
	}
}
