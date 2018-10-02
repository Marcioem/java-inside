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
}
