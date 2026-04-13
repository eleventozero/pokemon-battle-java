package de.eleventozero.pokemonbattle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class testPokemonProfile {

	@Test
	public void testPokemonProfile_Constructor_Success() {

		Attack thunderShock = new Attack( "Thunder Shock", 40, "electric" );
		Attack thunderbolt = new Attack( "Thunderbolt", 90, "electric" );
		Attack explosion = new Attack( "Explosion", 170, "normal" );

		Attack[] attacks = { thunderShock, thunderbolt, explosion };

		PokemonProfile pp = new PokemonProfile( "Pikachu", 35, 55, 40, "electric",
				attacks );

		assertEquals( "Pikachu", pp.getName() );
		assertEquals( 35, pp.getMaxHp() );
		assertEquals( 55, pp.getAttack() );
		assertEquals( 40, pp.getDefense() );
		assertEquals( "electric", pp.getType() );
		assertArrayEquals( attacks, pp.getAttacks() );
	}
}
