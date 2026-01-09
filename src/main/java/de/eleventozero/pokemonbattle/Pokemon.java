package de.eleventozero.pokemonbattle;

public class Pokemon {

	// Fields
	private PokemonProfile profile;
	public int currentHp;


	// Constructors
	public Pokemon( int index ) {
		this.profile = PokemonData.PROFILES[ index ];
		this.currentHp = profile.getMaxHp( );
	}

	// Getter
	public String getName( ) {
		return profile.getName( );
	}

	public String getType( ) {
		return profile.getType( );
	}

	public int getMaxHp( ) {
		return profile.getMaxHp( );
	}

	public int getAttack( ) {
		return profile.getAttack( );
	}

	public int getDefense( ) {
		return profile.getDefense( );
	}
}