package fr.umlv.javainside.labOne;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SumsTests {

	@Test
	void testLoopSum() {
		assertEquals(0,Sums.loopSum(0));
		assertEquals(1,Sums.loopSum(1));
		assertEquals(10,Sums.loopSum(4));
	}
	
	@Test
	void testStreamSum() {
		assertEquals(0,Sums.streamSum(0));
		assertEquals(3,Sums.streamSum(2));
		assertEquals(10,Sums.streamSum(4));
	}
}
