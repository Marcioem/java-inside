package fr.umlv.javainside.labSixt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;
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

        var astatichelloType = MethodType.methodType(String.class, int.class);
        var exampleMethPrivateLookUp = MethodHandles.privateLookupIn(Example.class, MethodHandles.lookup());
        var method = exampleMethPrivateLookUp.findStatic(Example.class, "aStaticHello", astatichelloType);
        Assertions.assertEquals("question 4",  (String) method.invokeExact(4));
    }

    @Test
    void anInstanceHelloLookUpTest() throws Throwable {
        var example = new Example();

        var aninstancehelloType = MethodType.methodType(String.class, int.class);
        var exampleMethPrivateLookUp = MethodHandles.privateLookupIn(Example.class, MethodHandles.lookup());
        var method = exampleMethPrivateLookUp.findVirtual(Example.class, "anInstanceHello", aninstancehelloType);
        Assertions.assertEquals("question 5",  (String) method.invokeExact(example, 5));
    }

    @Test
    void anInstanceHelloInsertArgsLookUpTest() throws Throwable {
        var example = new Example();

        var aninstancehelloType = MethodType.methodType(String.class, int.class);
        var anInsHellWithoutArgHand = MethodHandles.privateLookupIn(Example.class, MethodHandles.lookup());
        var anInsHellWithoutArgMeth = anInsHellWithoutArgHand.findVirtual(Example.class, "anInstanceHello", aninstancehelloType);
        var anInsHellWithArg = MethodHandles.insertArguments(anInsHellWithoutArgMeth, 1, 8);

        Assertions.assertEquals("question 8",  (String) anInsHellWithArg.invokeExact(example));
    }

    @Test
    void anInstanceHelloDropArgsLookUpTest() throws Throwable {
        var example = new Example();

        var aninstancehelloType = MethodType.methodType(String.class, int.class);
        var anInsHellHand = MethodHandles.privateLookupIn(Example.class, MethodHandles.lookup());
        var anInsHellMeth = anInsHellHand.findVirtual(Example.class, "anInstanceHello", aninstancehelloType);
        var anInsHellWithDropedArg = MethodHandles.dropArguments(anInsHellMeth, 1, int.class);

        Assertions.assertEquals("question 8",  (String) anInsHellWithDropedArg.invokeExact(example, 5, 8));
    }

    @Test
    void anInstanceHelloUnboxingLookUpTest() throws Throwable {
        var example = new Example();

        var aninstancehelloType = MethodType.methodType(String.class, int.class);
        var anInsHellHand = MethodHandles.privateLookupIn(Example.class, MethodHandles.lookup());
        var anInsHellMeth = anInsHellHand.findVirtual(Example.class, "anInstanceHello", aninstancehelloType);

        var aninstancehelloIntegerType = MethodType.methodType(String.class, Example.class, Integer.class);
        var anInsHellWithIntegerArg = anInsHellMeth.asType(aninstancehelloIntegerType);

        Assertions.assertEquals("question 9",  (String) anInsHellWithIntegerArg.invokeExact(example, Integer.valueOf(9)));
        Assertions.assertThrows(WrongMethodTypeException.class,  ()-> anInsHellWithIntegerArg.invokeExact(example, 9));
    }

    @Test
    void constantLookUpTest() throws Throwable {
        var constant = MethodHandles.constant(String.class, "question 10");
        Assertions.assertEquals("question 10",  (String) constant.invokeExact());
    }

    @Test
    void guardWithTestLookUpTest() throws Throwable {
        var equalsType = MethodType.methodType(boolean.class, Object.class);
        var equalsMeth = MethodHandles.lookup().findVirtual(String.class, "equals", equalsType);
        var testWithFoo = MethodHandles.insertArguments(equalsMeth, 1, "foo");

        var target = MethodHandles.constant(int.class, 1);
        var fallback = MethodHandles.constant(int.class, -1);

        var targetWithDropedArg = MethodHandles.dropArguments(target, 0, String.class);
        var fallbackWithDropedArg = MethodHandles.dropArguments(fallback, 0, String.class);

        var guardWithFoo = MethodHandles.guardWithTest(testWithFoo, targetWithDropedArg, fallbackWithDropedArg);

        Assertions.assertEquals(1, guardWithFoo.invokeExact("foo"));
        Assertions.assertEquals(-1, guardWithFoo.invokeExact("notFoo") );

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

/*
mutablecallsite contient une methodHandler qui appel target, on peut lui demander donnes moi le "" avec le dynamic...
 */