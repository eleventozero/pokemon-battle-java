package de.eleventozero.pokemonbattle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestPokemon {

	@Test
	public void testConstructor_CreatConstructor_Successful() {
		Attack flameThrower = new Attack( "Flame Thrower", 90, "fire" );
		Attack fireBlast = new Attack( "Fire Blast", 110, "fire" );
		Attack wingAttack = new Attack( "Wing Attack", 60, "flying" );
		Attack[] attacks = { flameThrower, fireBlast, wingAttack };

		PokemonProfile charizard = new PokemonProfile( "Charizard", 78, 84, 78, "fire", attacks );
		Pokemon c = new Pokemon( charizard );

		assertEquals( "Charizard", c.getName() );
		assertEquals( 78, c.getCurrentHp() );
		assertEquals( 78, c.getMaxHp() );
		assertEquals( 84, c.getAttack() );
		assertEquals( 78, c.getDefense() );
		assertEquals( "fire", c.getType() );

		assertEquals( "Flame Thrower", c.getAttacks()[ 0 ].getName() );
		assertEquals( 90, c.getAttacks()[ 0 ].getDamage() );
		assertEquals( "fire", c.getAttacks()[ 0 ].getType() );

		assertEquals( "Fire Blast", c.getAttacks()[ 1 ].getName() );
		assertEquals( 110, c.getAttacks()[ 1 ].getDamage() );
		assertEquals( "fire", c.getAttacks()[ 1 ].getType() );

		assertEquals( "Wing Attack", c.getAttacks()[ 2 ].getName() );
		assertEquals( 60, c.getAttacks()[ 2 ].getDamage() );
		assertEquals( "flying", c.getAttacks()[ 2 ].getType() );
	}

	@Test
	public void testSetCurrentHp_ChangeHp_Successful() {
		PokemonProfile p = PokemonData.loadPokemon( 6 );
		Pokemon charizard = new Pokemon( p );
		charizard.setCurrentHp( 100 );

		assertEquals( 100, charizard.getCurrentHp() );
	}

	@Test
	public void testSetCurrentHp_ClampHp_Successful() {
		PokemonProfile p = PokemonData.loadPokemon( 6 );
		Pokemon charizard = new Pokemon( p );
		charizard.setCurrentHp( -100 );

		assertEquals( 0, charizard.getCurrentHp() );
	}

	@Test
	public void testIsFainted_Fainted_True() {
		PokemonProfile p = PokemonData.loadPokemon( 6 );
		Pokemon charizard = new Pokemon( p );
		charizard.setCurrentHp( 0 );

		assertTrue(charizard.isFainted());
	}

	@Test
	public void testIsFainted_Fainted_False() {
		PokemonProfile p = PokemonData.loadPokemon( 6 );
		Pokemon charizard = new Pokemon( p );

		assertFalse(charizard.isFainted());
	}
}
