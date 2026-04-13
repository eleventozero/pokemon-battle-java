package de.eleventozero.pokemonbattle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestBattle {

	@Test
	public void testAppyAttack_HpDecrease_Successful() {

		PokemonProfile pikachu = PokemonData.loadPokemon( 25 );
		PokemonProfile charizard = PokemonData.loadPokemon( 6 );

		Pokemon p = new Pokemon( pikachu );
		Pokemon c = new Pokemon( charizard );

		assertEquals( 78, c.getCurrentHp() );
		Battle.applyAttack( p, c, 0 );
		assertTrue( c.getCurrentHp() < 78 );
	}

	@Test
	public void testTypeMultiplier_CalculateMultiplier_Successful() {

		assertEquals( 0.5, Battle.typeMultiplier( "normal", "rock" ) );
		assertEquals( 2, Battle.typeMultiplier( "fire", "ice" ) );
		assertEquals( 0.5, Battle.typeMultiplier( "water", "water" ) );
		assertEquals( 0.5, Battle.typeMultiplier( "fighting", "poison" ) );
		assertEquals( 1, Battle.typeMultiplier( "bug", "bug" ) );
	}

	@Test
	public void testCalculateDamage_CalculateDamage_Successful() {

		PokemonProfile pikachu = PokemonData.loadPokemon( 25 );
		PokemonProfile charizard = PokemonData.loadPokemon( 6 );

		Pokemon attacker = new Pokemon( pikachu );
		Pokemon defender = new Pokemon( charizard );

		Attack thunderShock = new Attack( "Thunder Shock", 40, "electric" );

		int damage = Battle.calculateDamage( attacker, defender, thunderShock );

		int A = attacker.getAttack();
		int D = Math.max( 1, defender.getDefense() );
		int power = thunderShock.getDamage();
		int base = 5 + ( ( A * power ) / D ) / 50;
		double stab = 1.5;
		double effectiveness = 1; // elecric vs fire
		int minDamage = ( int ) Math.floor( base * stab * effectiveness * 0.7 );
		int maxDamage = ( int ) Math.floor( base * stab * effectiveness * 1.3 );

		assertTrue( damage <= maxDamage && damage >= minDamage );
	}
}
