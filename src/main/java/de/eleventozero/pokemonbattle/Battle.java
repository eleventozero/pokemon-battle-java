package de.eleventozero.pokemonbattle;

/**
 * Contains battle-related calculations such as damage.
 */
public class Battle {

	/**
	 * Calculate the damage dealt by an attacker to a defender using a specific attack.
	 * <p>
	 * Formula:
	 * base   = 5 + floor( floor((A * power) / D) / 50)
	 * damage = base * stab * effectiveness * random
	 *
	 * @param attacker attacking Pokemon
	 * @param defender defending Pokemon
	 * @param attack   attack being used
	 * @return damage as integer
	 */
	public static int calculateDamage( Pokemon attacker, Pokemon defender, Attack attack ) {

		// Base stats
		int A = attacker.getAttack( );
		int D = Math.max( 1, defender.getDefense( ) ); // Avoid division by 0
		int power = attack.getDamage( );

		// Base damage calculation
		int base = 5 + ( ( A * power ) / D ) / 50;

		// STAB (Same Type Attack Bonus)
		double stab = attack.getType().equals(attacker.getType()) ? 1.5 : 1.0;

		// Type effectiveness
		double effectiveness = typeMultiplier(attack.getType(), defender.getType());

		// Random factor between 0.7 and 1.3
		double random = (7 + Math.random() * 6) / 10.0;

		// Final damage
		double damage = base * stab * effectiveness * random;

		return (int) Math.floor(damage);
	}

	/**
	 * Returns type effectiveness multiplier.
	 * Values: 0, 0.5, 1, 2
	 *
	 * @param atkType type of the attacking Pokemon
	 * @param defType type of the defending Pokemon
	 * @return effectiveness multiplier:
	 * 		   0   = no effect
	 * 		   0.5 = not very effective
	 * 		   1   = normal
	 * 		   2   = super effective
	 */
	public static double typeMultiplier(String atkType, String defType){

		// NORMAL
		if (atkType.equals("normal") && defType.equals("rock")) return 0.5;
		if (atkType.equals("normal") && defType.equals("ghost")) return 0;

		// FIRE
		if (atkType.equals("fire") && defType.equals("grass")) return 2;
		if (atkType.equals("fire") && defType.equals("ice")) return 2;
		if (atkType.equals("fire") && defType.equals("bug")) return 2;
		if (atkType.equals("fire") && defType.equals("water")) return 0.5;
		if (atkType.equals("fire") && defType.equals("fire")) return 0.5;
		if (atkType.equals("fire") && defType.equals("rock")) return 0.5;
		if (atkType.equals("fire") && defType.equals("dragon")) return 0.5;

		// WATER
		if (atkType.equals("water") && defType.equals("fire")) return 2;
		if (atkType.equals("water") && defType.equals("rock")) return 2;
		if (atkType.equals("water") && defType.equals("ground")) return 2;
		if (atkType.equals("water") && defType.equals("water")) return 0.5;
		if (atkType.equals("water") && defType.equals("grass")) return 0.5;
		if (atkType.equals("water") && defType.equals("dragon")) return 0.5;

		// ELECTRIC
		if (atkType.equals("electric") && defType.equals("water")) return 2;
		if (atkType.equals("electric") && defType.equals("flying")) return 2;
		if (atkType.equals("electric") && defType.equals("electric")) return 0.5;
		if (atkType.equals("electric") && defType.equals("grass")) return 0.5;
		if (atkType.equals("electric") && defType.equals("dragon")) return 0.5;
		if (atkType.equals("electric") && defType.equals("ground")) return 0;

		// GRASS
		if (atkType.equals("grass") && defType.equals("water")) return 2;
		if (atkType.equals("grass") && defType.equals("rock")) return 2;
		if (atkType.equals("grass") && defType.equals("ground")) return 2;
		if (atkType.equals("grass") && defType.equals("fire")) return 0.5;
		if (atkType.equals("grass") && defType.equals("grass")) return 0.5;
		if (atkType.equals("grass") && defType.equals("poison")) return 0.5;
		if (atkType.equals("grass") && defType.equals("flying")) return 0.5;
		if (atkType.equals("grass") && defType.equals("bug")) return 0.5;
		if (atkType.equals("grass") && defType.equals("dragon")) return 0.5;

		// ICE
		if (atkType.equals("ice") && defType.equals("grass")) return 2;
		if (atkType.equals("ice") && defType.equals("ground")) return 2;
		if (atkType.equals("ice") && defType.equals("flying")) return 2;
		if (atkType.equals("ice") && defType.equals("dragon")) return 2;
		if (atkType.equals("ice") && defType.equals("water")) return 0.5;
		if (atkType.equals("ice") && defType.equals("ice")) return 0.5;

		// FIGHTING
		if (atkType.equals("fighting") && defType.equals("normal")) return 2;
		if (atkType.equals("fighting") && defType.equals("rock")) return 2;
		if (atkType.equals("fighting") && defType.equals("ice")) return 2;
		if (atkType.equals("fighting") && defType.equals("poison")) return 0.5;
		if (atkType.equals("fighting") && defType.equals("flying")) return 0.5;
		if (atkType.equals("fighting") && defType.equals("psychic")) return 0.5;
		if (atkType.equals("fighting") && defType.equals("bug")) return 0.5;
		if (atkType.equals("fighting") && defType.equals("ghost")) return 0;

		// POISON
		if (atkType.equals("poison") && defType.equals("grass")) return 2;
		if (atkType.equals("poison") && defType.equals("bug")) return 2;
		if (atkType.equals("poison") && defType.equals("poison")) return 0.5;
		if (atkType.equals("poison") && defType.equals("ground")) return 0.5;
		if (atkType.equals("poison") && defType.equals("rock")) return 0.5;
		if (atkType.equals("poison") && defType.equals("ghost")) return 0.5;

		// GROUND
		if (atkType.equals("ground") && defType.equals("fire")) return 2;
		if (atkType.equals("ground") && defType.equals("electric")) return 2;
		if (atkType.equals("ground") && defType.equals("poison")) return 2;
		if (atkType.equals("ground") && defType.equals("rock")) return 2;
		if (atkType.equals("ground") && defType.equals("grass")) return 0.5;
		if (atkType.equals("ground") && defType.equals("bug")) return 0.5;
		if (atkType.equals("ground") && defType.equals("flying")) return 0;

		// FLYING
		if (atkType.equals("flying") && defType.equals("grass")) return 2;
		if (atkType.equals("flying") && defType.equals("fighting")) return 2;
		if (atkType.equals("flying") && defType.equals("bug")) return 2;
		if (atkType.equals("flying") && defType.equals("electric")) return 0.5;
		if (atkType.equals("flying") && defType.equals("rock")) return 0.5;

		// PSYCHIC
		if (atkType.equals("psychic") && defType.equals("fighting")) return 2;
		if (atkType.equals("psychic") && defType.equals("poison")) return 2;
		if (atkType.equals("psychic") && defType.equals("psychic")) return 0.5;

		// BUG
		if (atkType.equals("bug") && defType.equals("grass")) return 2;
		if (atkType.equals("bug") && defType.equals("psychic")) return 2;
		if (atkType.equals("bug") && defType.equals("fire")) return 0.5;
		if (atkType.equals("bug") && defType.equals("fighting")) return 0.5;
		if (atkType.equals("bug") && defType.equals("flying")) return 0.5;
		if (atkType.equals("bug") && defType.equals("ghost")) return 0.5;

		// ROCK
		if (atkType.equals("rock") && defType.equals("fire")) return 2;
		if (atkType.equals("rock") && defType.equals("ice")) return 2;
		if (atkType.equals("rock") && defType.equals("flying")) return 2;
		if (atkType.equals("rock") && defType.equals("bug")) return 2;
		if (atkType.equals("rock") && defType.equals("fighting")) return 0.5;
		if (atkType.equals("rock") && defType.equals("ground")) return 0.5;

		// GHOST
		if (atkType.equals("ghost") && defType.equals("ghost")) return 2;
		if (atkType.equals("ghost") && defType.equals("psychic")) return 0;

		// DRAGON
		if (atkType.equals("dragon") && defType.equals("dragon")) return 2;

		// Default
		return 1;
	}

	/**
	 * Applies an attack and returns the damage dealt.
	 *
	 * @param attacker attacking Pokemon
	 * @param defender defending Pokemon
	 * @param attackIndex index of the attack (0-2)
	 * @return damage dealt
	 */
	public static int applyAttack(Pokemon attacker, Pokemon defender, int attackIndex){

		Attack attack = attacker.getAttacks()[attackIndex];

		int damage = calculateDamage(attacker, defender, attack);

		int newHp = defender.getCurrentHp() - damage;

		defender.setCurrentHp(newHp);

		return damage;
	}


}
