package fr.umlv.javainside.labThree;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ExprSwitchesTest {


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
		assertEquals("zero", ExprSwitches.StringSwitch("foo"));
		assertEquals("one", ExprSwitches.StringSwitch("bar"));
		assertEquals("a lot", ExprSwitches.StringSwitch("baz"));
		assertEquals("zero", ExprSwitches.StringSwitch("viva zorg"));
	}

	@Test
	void EnumSwitchtest() {
		assertEquals("zero", ExprSwitches.enumSwitch(ExprSwitches.EnumSwitch.DEBUG));
		assertEquals("one", ExprSwitches.enumSwitch(ExprSwitches.EnumSwitch.WARNING));
		assertEquals("a lot", ExprSwitches.enumSwitch(ExprSwitches.EnumSwitch.INFO));
		assertEquals("zero", ExprSwitches.enumSwitch(ExprSwitches.EnumSwitch.ERROR));
	}

	static Stream<TestData> testDataProvider() {
		List<TestData> lst = List.of(
				new TestData(ExprSwitches::intSwitch, 0, "zero"),
				new TestData(ExprSwitches::intSwitch, 3, "zero"),
				new TestData(ExprSwitches::intSwitch2, 3, "zero"),
				new TestData(ExprSwitches::intSwitch, 1, "one"),
				new TestData(ExprSwitches::intSwitch, 2, "a lot"),
				new TestData(ExprSwitches::intSwitch2, 0, "zero"),
				new TestData(ExprSwitches::intSwitch2, 10, "one"),
				new TestData(ExprSwitches::intSwitch2, 100, "a lot"));
		return lst.stream();
	}

	@ParameterizedTest
	@MethodSource("intFunctionofStringProvider")
	void intSwitchTest(IntFunction<String> funct ) {
		assertThrows(IllegalArgumentException.class,  () -> funct.apply(-1));
	}

	static Stream<IntFunction<String>> intFunctionofStringProvider(){
		return Stream.of(ExprSwitches::intSwitch, ExprSwitches::intSwitch2);
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
