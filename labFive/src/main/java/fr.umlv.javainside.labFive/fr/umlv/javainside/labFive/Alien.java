package fr.umlv.javainside.labFive;

import java.util.Objects;

public class Alien {
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