package de.eleventozero.pokemonbattle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestBattleParticipant {

	@Test
	public void testConstructor_CreateConstructor_Successful() {

		PokemonProfile pikachu = PokemonData.loadPokemon( 25 );
		Pokemon p = new Pokemon( pikachu );

		BattleParticipant b = new BattleParticipant( p );

		assertEquals(p, b.getActivePokemon());
	}

	@Test
	public void testSetActivePokemon_ChangeActivePokemon_Successful() {

		PokemonProfile pikachu = PokemonData.loadPokemon( 25 );
		Pokemon p = new Pokemon( pikachu );
		PokemonProfile charizard = PokemonData.loadPokemon( 6 );
		Pokemon c = new Pokemon( charizard );

		BattleParticipant b = new BattleParticipant( p );
		assertEquals(p, b.getActivePokemon());

		b.setActivePokemon(c);
		assertEquals(c, b.getActivePokemon());
	}
}
