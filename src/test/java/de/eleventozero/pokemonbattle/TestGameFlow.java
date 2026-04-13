package de.eleventozero.pokemonbattle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestGameFlow {

	@Test
	public void testGameFlow_Constructor_Successful() {
		Pokemon p = new Pokemon( PokemonData.loadPokemon(25));
		BattleParticipant player = new BattleParticipant( p );

		Pokemon c = new Pokemon( PokemonData.loadPokemon(6));
		BattleParticipant enemy = new BattleParticipant( c );

		GameFlow g = new GameFlow( player, enemy);

		assertEquals(player, g.getPlayer());
		assertEquals(enemy, g.getEnemy());
	}


}
