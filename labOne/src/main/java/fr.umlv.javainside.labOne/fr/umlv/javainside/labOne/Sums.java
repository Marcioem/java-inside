package fr.umlv.javainside.labOne;

import java.util.stream.IntStream;

public class Sums {
	public static int loopSum(int n) {
		int count =0;
		for(int i=0;i<=n;i++) {
			count +=i;
		}
		return count;
	}
	
	public static int streamSum(int n) {
		return IntStream.range(0,n+1).sum();
	}
}
