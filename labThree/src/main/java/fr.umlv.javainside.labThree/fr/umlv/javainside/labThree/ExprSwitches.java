package fr.umlv.javainside.labThree;

public class ExprSwitches {

	public static enum EnumSwitch {
		DEBUG, WARNING, INFO, ERROR
	}
	
	public static String intSwitch(int binary) {
		switch(binary) {
            case 3:
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

    public static String exprIntSwitch(int binary) {
        switch(binary) {
            case 3:
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
            case 3:
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

    public static String exprIntSwitch2(int binary) {
        switch(binary) {
            case 3:
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

	public static String StringSwitch(String index) {
		switch(index) {
			case "viva zorg" :
			case "foo": return "zero";
			case "bar" : return "one";
			case "baz" : return "a lot";
			default : throw new IllegalArgumentException("String  :" + index + " isn't recognized.");
		}
	}

	public static String enumSwitch(EnumSwitch enumString) {
		switch(enumString) {
			case ERROR :
			case DEBUG: return "zero";
			case WARNING : return "one";
			case INFO : return "a lot";
			default: throw new IllegalArgumentException("Enum  :" + enumString + " isn't recognized.");
		}
	}
}
