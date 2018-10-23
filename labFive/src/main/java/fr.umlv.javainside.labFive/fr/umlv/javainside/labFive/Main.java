package fr.umlv.javainside.labFive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    /*
    public static String toJSON(Person person) {
        return
            "{\n" +
            "  \"firstName\": \"" + person.getFirstName() + "\"\n" +
            "  \"lastName\": \"" + person.getLastName() + "\"\n" +
            "}\n";
    }

    public static String toJSON(Alien alien) {
        return
            "{\n" +
            "  \"planet\": \"" + alien.getPlanet() + "\"\n" +
            "  \"members\": \"" + alien.getAge() + "\"\n" +
            "}\n";
    }*/

    public static String toJSON(Object obj) {
        return Arrays.stream(obj.getClass().getMethods()).filter(method -> method.getName().startsWith("get"))
                .map(e -> "   \"" + Main.propertyName(e.getName()) + "\": \"" + Main.callInvoke(e,obj) + "\"")
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    public static Object callInvoke(Method method,Object obj) {
        try{
            return method.invoke(obj);
        }catch(IllegalAccessException ex){
            throw new AssertionError();
        } catch(InvocationTargetException e) {
            var eCause = e.getCause();
            if(eCause instanceof RuntimeException) {
                throw (RuntimeException)eCause;
            }
            if(eCause instanceof Error) {
                throw (Error)eCause;
            }
            throw new UndeclaredThrowableException(eCause);
        }
    }

    private static String propertyName(String name) {
       return Character.toLowerCase(name.charAt(3)) + name.substring(4);
     }

    public static void main(String[] args) {
        var person = new Person("John", "Doe");
        System.out.println(toJSON(person));
        var alien = new Alien("E.T.", 100);
        System.out.println(toJSON(alien));
        System.out.println(toJSON((Object)alien));
    }
}
