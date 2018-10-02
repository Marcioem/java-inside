package fr.umlv.javainside.labTwo;

public class Switches {
	
	
	public static String intSwitch(int binary) {
		switch(binary) {
			case 0:
				return "zero";
			case 1:
				return "one";
			case 2:
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}	
	}
	
	public static String intSwitch2(int binary) {
		switch(binary) {
			case 0:
				return "zero";
			case 10:
				return "one";
			case 100:
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}
			
	}
}
