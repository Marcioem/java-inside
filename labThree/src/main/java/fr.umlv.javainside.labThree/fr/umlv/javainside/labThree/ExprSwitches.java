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

	public static String stringSwitch(String index) {
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

    public static String exprIntSwitch(int binary) {
        return switch(binary) {
            case 3, 0 -> "zero";
            case 1 -> "one";
            case 2 -> "a lot";
            default -> throw new IllegalArgumentException();
        };
    }

    public static String exprIntSwitch2(int binary) {
        return switch(binary) {
            case 3, 0 -> "zero";
            case 10 -> "one";
            case 100 -> "a lot";
            default -> throw new IllegalArgumentException();
        };
    }

    public static String exprStringSwitch(String index) {
        return switch(index) {
            case "viva zorg", "foo" -> "zero";
            case "bar" -> "one";
            case "baz" -> "a lot";
            default -> throw new IllegalArgumentException("String  :" + index + " isn't recognized.");
        };
    }

    public static String exprEnumSwitch(EnumSwitch enumString) {
        return switch(enumString) {
            case ERROR, DEBUG -> "zero";
            case WARNING -> "one";
            case INFO -> "a lot";
            default -> throw new IllegalArgumentException("Enum  :" + enumString + " isn't recognized.");
        };
    }
}
