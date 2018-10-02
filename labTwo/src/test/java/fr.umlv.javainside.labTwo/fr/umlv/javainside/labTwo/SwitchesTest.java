package fr.umlv.javainside.labTwo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.IntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SwitchesTest {
	
	
	@ParameterizedTest
	@MethodSource("intFunctionofStringProvider")
	void intSwitchTest(IntFunction<String> funct ) {
		assertThrows(IllegalArgumentException.class,  () -> funct.apply(-1));
	}
	
	
	static Stream<IntFunction<String>> intFunctionofStringProvider(){
		return Stream.of(Switches::intSwitch, Switches::intSwitch2);
	}
	/*
	 * 	
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
	 */
	
}
