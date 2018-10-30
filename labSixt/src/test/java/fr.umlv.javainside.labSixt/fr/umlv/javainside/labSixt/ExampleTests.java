package fr.umlv.javainside.labSixt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

public class ExampleTests {

    @Test
    void test() {

        System.out.println("Hello Exampletest !");
    }


    @Test
    void aStaticHelloReflectiveTest() throws ReflectiveOperationException {
        Method astatichello = Example.class.getDeclaredMethod("aStaticHello", int.class);
        astatichello.setAccessible(true);
        Assertions.assertEquals("question 3", astatichello.invoke(null, 3));
    }

    @Test
    void anInstanceHelloReflectiveTest() throws ReflectiveOperationException {
        Method aninstancehello = Example.class.getDeclaredMethod("anInstanceHello", int.class);
        var example = new Example();
        aninstancehello.setAccessible(true);
        Assertions.assertEquals("question 3", aninstancehello.invoke( example, 3));
    }

    @Test
    void aStaticHelloLookUpTest() throws Throwable {

        var exampleMethHand = MethodHandles.lookup();
        var astatichelloType = MethodType.methodType(String.class, int.class);
        var exampleMethPrivateLookUp = MethodHandles.privateLookupIn(Example.class, exampleMethHand);
        var method = exampleMethPrivateLookUp.findStatic(Example.class, "aStaticHello", astatichelloType);
        Assertions.assertEquals("question 4",  (String) method.invokeExact(4));
    }

    @Test
    void anInstanceHelloLookUpTest() throws Throwable {
        var example = new Example();

        var exampleMethHand = MethodHandles.lookup();
        var aninstancehelloType = MethodType.methodType(String.class, int.class);
        var exampleMethPrivateLookUp = MethodHandles.privateLookupIn(Example.class, exampleMethHand);
        var method = exampleMethPrivateLookUp.findVirtual(Example.class, "anInstanceHello", aninstancehelloType);
        Assertions.assertEquals("question 5",  (String) method.invokeExact(example, 5));
    }

}
/*
La différence entre les deux : les deux api peuvent faire la meme chose ( appel à des trucs private ) mais sans faire les memes appels, ils doivent etre dans le meme module,
si non ouvert à tous.
L'api de reflexion permet de créer un objet method sur une methode private, invoke ( check à chaque fois) plante si pas set accessible.
Pour lookup c'est au moment du find qui plante.
L'un check au moment de l'appel, l'autre au moment de la recherche. Invoke box les objets alors que lookup ne boxe pas contrainte il faut indiquer les types de retour.
l'api de invocation permet plus de truc.
 */