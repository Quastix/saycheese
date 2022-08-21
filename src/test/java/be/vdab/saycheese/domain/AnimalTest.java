package be.vdab.saycheese.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimalTest {

    private Cheese cheese1;
    private Country country1;
    private Colour colour1;
    private Animal animal1;

    @BeforeEach
    void beforeEach() {
        animal1 = new Animal("craterhoof");
        country1 = new Country("test");
        colour1 = new Colour("test");
        cheese1 = new Cheese("test1", true, country1, colour1, 1, 2, 3);
    }

    @Test
    void addCheese() {
        assertThat(animal1.getCheeses()).isEmpty();
        assertThat(animal1.add(cheese1)).isTrue();
        assertThat(animal1.getCheeses()).containsOnly(cheese1);
        assertThat(cheese1.getAnimals()).containsOnly(animal1);
    }

    @Test
    void docentVerwijderen() {
        assertThat(animal1.add(cheese1)).isTrue();
        assertThat(animal1.remove(cheese1)).isTrue();
        assertThat(animal1.getCheeses()).isEmpty();
        assertThat(cheese1.getAnimals()).isEmpty();
    }
}
