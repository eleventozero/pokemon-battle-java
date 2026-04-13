package de.eleventozero.pokemonbattle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestPokemonData {

	@Test
	public void testLoadPokemon_CreatePokemonProfile_Successful() {
		PokemonProfile pikachu = PokemonData.loadPokemon( 25 );

		Attack thunderShock = new Attack( "Thunder Shock", 40, "electric" );
		Attack thunderbolt = new Attack( "Thunderbolt", 90, "electric" );
		Attack explosion = new Attack( "Explosion", 170, "normal" );

		Attack[] attacks = { thunderShock, thunderbolt, explosion };

		assertEquals( "Pikachu", pikachu.getName() );
		assertEquals( 35, pikachu.getMaxHp() );
		assertEquals( 55, pikachu.getAttack() );
		assertEquals( 40, pikachu.getDefense() );
		assertEquals( "electric", pikachu.getType() );
		//assertArrayEquals( attacks, pikachu.getAttacks() );
	}

	@Test
	public void testLoadAttacks_CreateAttackArray_Successful() {
		Attack thunderShock = new Attack( "Thunder Shock", 40, "electric" );
		Attack thunderbolt = new Attack( "Thunderbolt", 90, "electric" );
		Attack explosion = new Attack( "Explosion", 170, "normal" );
		Attack[] attacks = { thunderShock, thunderbolt, explosion };

		PokemonProfile pikachu = PokemonData.loadPokemon( 25 );
		Attack[] pikachuAttacks = pikachu.getAttacks();

		assertEquals("Thunder Shock", pikachuAttacks[0].getName() );
		assertEquals(40, pikachuAttacks[0].getDamage() );
		assertEquals("electric", pikachuAttacks[0].getType() );

		assertEquals("Thunderbolt", pikachuAttacks[1].getName() );
		assertEquals(90, pikachuAttacks[1].getDamage() );
		assertEquals("electric", pikachuAttacks[1].getType() );

		assertEquals("Explosion", pikachuAttacks[2].getName() );
		assertEquals(170, pikachuAttacks[2].getDamage() );
		assertEquals("normal", pikachuAttacks[2].getType() );
	}

}
