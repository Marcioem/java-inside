package fr.umlv.javainside.labTwo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SwitchesTest {
	
	@Test
	void shouldGetErrorWhenNegativeInt() {
		assertThrows(IllegalStateException.class,  () -> Switches.intSwitch(-1));
	}
	
	@Test
	void shouldGetALotStringWhen2Send() {
		assertEquals("a lot", Switches.intSwitch(2));
	}
	
	@Test
	void shouldGetOneStringWhen1Send() {
		assertEquals("one", Switches.intSwitch(1));
	}
	
	@Test
	void shouldGetZeroStringWhen0Send() {
		assertEquals("zero", Switches.intSwitch(0));
	}
	
}
