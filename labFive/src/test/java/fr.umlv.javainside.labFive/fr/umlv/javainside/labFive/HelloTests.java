package fr.umlv.javainside.labFive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Objects;

@SuppressWarnings("static-method")
class HelloTests {
  // pro requires the test class to finish with 'Tests'

    @Test
    void test() {
    System.out.println("Hello test !");
    }

    @Test
    void Alientest() {
    Alien aaaaalieen= new Alien("Vegeta", 400);
    assertEquals("{\n" +
            "   \"planet\": \"Vegeta\"\n" +
            "   \"age\": \"400\"\n" +
            "}",Main.toJSON(aaaaalieen));
    }

    @Test
    void Persontest() {
        Person peeeeeeeeeerson= new Person("Son", "Goku");
        assertEquals("{\n" +
                "   \"firstName\": \"Son\"\n" +
                "   \"lastName\": \"Goku\"\n" +
                "}",Main.toJSON(peeeeeeeeeerson));
    }

    class Alien {
        private final String planet;
        private final int age;

        public Alien(String planet, int age) {
            if (age <= 0) {
                throw new IllegalArgumentException("Too young...");
            }
            this.planet = Objects.requireNonNull(planet);
            this.age = age;
        }

        @JSONProperty
        public String getPlanet() {
            return planet;
        }

        @JSONProperty
        public int getAge() {
            return age;
        }
    }

    class Person {
        private final String firstName;
        private final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = Objects.requireNonNull(firstName);
            this.lastName = Objects.requireNonNull(lastName);
        }

        @JSONProperty
        public String getFirstName() {
            return firstName;
        }

        @JSONProperty
        public String getLastName() {
            return lastName;
        }
    }
}
