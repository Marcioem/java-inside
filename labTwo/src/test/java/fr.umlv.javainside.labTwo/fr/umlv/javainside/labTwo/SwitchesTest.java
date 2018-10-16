package fr.umlv.javainside.labTwo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SwitchesTest {
	static class TestData {
		IntFunction function;
		int parameter;
		String expectedReturn;

		public TestData(IntFunction function,int parameter, String expectedReturn) {
			this.function = function;
			this.parameter = parameter;
			this.expectedReturn = expectedReturn;
		}
		public IntFunction getFunction() {
			return function;
		}
		public int getParameter() {
			return parameter;
		}
		public String getExpectedReturn() {
			return expectedReturn;
		}
	}

	@ParameterizedTest
	@MethodSource("testDataProvider")
	void intSwitchtest(TestData function) {
		assertEquals(function.expectedReturn, function.getFunction().apply(function.getParameter()));
	}


	@Test
	void StringSwitchtest() {
		assertEquals("zero", Switches.stringSwitch("foo"));
		assertEquals("one", Switches.stringSwitch("bar"));
		assertEquals("a lot", Switches.stringSwitch("baz"));
		assertEquals("zero", Switches.stringSwitch("viva zorg"));
	}

	@Test
	void EnumSwitchtest() {
		assertEquals("zero", Switches.enumSwitch(Switches.EnumSwitch.DEBUG));
		assertEquals("one", Switches.enumSwitch(Switches.EnumSwitch.WARNING));
		assertEquals("a lot", Switches.enumSwitch(Switches.EnumSwitch.INFO));
		assertEquals("zero", Switches.enumSwitch(Switches.EnumSwitch.ERROR));
	}

	static Stream<TestData> testDataProvider() {
		List<TestData> lst = List.of(
				new TestData(Switches::intSwitch, 0, "zero"),
				new TestData(Switches::intSwitch, 3, "zero"),
				new TestData(Switches::intSwitch2, 3, "zero"),
				new TestData(Switches::intSwitch, 1, "one"),
				new TestData(Switches::intSwitch, 2, "a lot"),
				new TestData(Switches::intSwitch2, 0, "zero"),
				new TestData(Switches::intSwitch2, 10, "one"),
				new TestData(Switches::intSwitch2, 100, "a lot"));
		return lst.stream();
	}

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
		assertThrows(IllegalStateException.class,  () -> ExprSwitches.intSwitch(-1));
	}

	@Test
	void shouldGetALotStringWhen2Send() {
		assertEquals("a lot", ExprSwitches.intSwitch(2));
	}

	@Test
	void shouldGetOneStringWhen1Send() {
		assertEquals("one", ExprSwitches.intSwitch(1));
	}

	@Test
	void shouldGetZeroStringWhen0Send() {
		assertEquals("zero", ExprSwitches.intSwitch(0));
	}
	 */
	
}
