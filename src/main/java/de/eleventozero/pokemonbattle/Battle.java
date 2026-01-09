package de.eleventozero.pokemonbattle;

public class Battle {

	private Pokemon player;
	private Pokemon enemy;
	private boolean playerTurn;
	private boolean battleOver;

	public Battle( Pokemon player, Pokemon enemy ) {
		this.player = player;
		this.enemy = enemy;
		this.playerTurn = true;
	}

	public boolean isBattleOver( ) {
		return battleOver;
	}

	public void switchTurn( ) {
		playerTurn = !playerTurn;
	}

}
