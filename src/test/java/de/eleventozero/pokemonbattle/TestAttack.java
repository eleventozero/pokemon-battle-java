package de.eleventozero.pokemonbattle;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestAttack {

	@Test
	public void testConstructor_Constructor_Successful() {
		Attack a = new Attack("Thunder Shock", 40,"electric");

		assertEquals("Thunder Shock", a.getName());
		assertEquals(40, a.getDamage());
		assertEquals("electric", a.getType());
	}
}
