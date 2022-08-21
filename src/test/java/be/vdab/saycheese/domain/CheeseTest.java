package be.vdab.saycheese.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class CheeseTest {
    private Cheese cheese1;
    private Cheese cheese2;
    private Country country1;
    private Colour colour1;
    private Flavour flavour1;
    private Animal animal1;

    @BeforeEach
    void beforeEach() {
        animal1 = new Animal("craterhoof");
        flavour1 = new Flavour("chocolade");
        country1 = new Country("test");
        colour1 = new Colour("test");
        cheese1 = new Cheese("test1", true, country1, colour1, 1, 2, 3);
        cheese2 = new Cheese("test2", true, country1, colour1, 1, 2, 3);
    }

    //Test urls collection value objects with base type
    @Test
    void aNewCheeseDoesntHaveAnUrl() {
        assertThat(cheese1.getUrls()).isEmpty();
    }

    @Test
    void addUrl() {
        assertThat(cheese1.addUrl("test")).isTrue();
        assertThat(cheese1.getUrls()).containsOnly("test");
    }

    @Test
    void addingAnUrlWithTheSameNameFails() {
        cheese1.addUrl("test");
        assertThat(cheese1.addUrl("test")).isFalse();
        assertThat(cheese1.getUrls()).containsOnly("test");
    }

    @Test
    void nullAsUrlFails() {
        assertThatNullPointerException().isThrownBy(() -> cheese1.addUrl(null));
    }

    @Test
    void anEmptyUrlFails() {
        assertThatIllegalArgumentException().isThrownBy(() -> cheese1.addUrl(""));
    }

    @Test
    void anUrlWithOnlySpacesFails() {
        assertThatIllegalArgumentException().isThrownBy(() -> cheese1.addUrl(" "));
    }

    @Test
    void removeUrl() {
        cheese1.addUrl("test");
        assertThat(cheese1.removeUrl("test")).isTrue();
        assertThat(cheese1.getUrls()).isEmpty();
    }

    @Test
    void removingAnNoneExistingUrlFails() {
        cheese1.addUrl("test");
        assertThat(cheese1.removeUrl("test2")).isFalse();
        assertThat(cheese1.getUrls()).containsOnly("test");
    }

    //Test Many to one Cheese/country
    @Test
    void multiplyCheeseCanComeFromTheSamCountry() {
        assertThat(country1.getCheeses()).containsOnly(cheese1, cheese2);
    }

    @Test
    void Cheese1OriginsFromCountry1() {
        assertThat(cheese1.getCountry()).isEqualTo(country1);
        assertThat(country1.getCheeses()).contains(cheese1);
    }

    @Test
    void aNullCountryInTheSetterFails() {
        assertThatNullPointerException().isThrownBy(() -> cheese1.setCountry(null));
    }

    //Test Many to one Cheese/colour
    @Test
    void multiplyCheeseCanHaveTheSamColour() {
        assertThat(colour1.getCheeses()).containsOnly(cheese1, cheese2);
    }

    @Test
    void Cheese1HasColour1() {
        assertThat(cheese1.getColour()).isEqualTo(colour1);
        assertThat(colour1.getCheeses()).contains(cheese1);
    }

    @Test
    void aNullColourInTheSetterFails() {
        assertThatNullPointerException().isThrownBy(() -> cheese1.setColour(null));
    }

    //Test many to many cheese/flavour
    @Test
    void addFlavour() {
        assertThat(cheese1.addFlavour(flavour1)).isTrue();
        assertThat(cheese1.getFlavours()).containsOnly(flavour1);
        assertThat(flavour1.getCheeses()).containsOnly(cheese1);
    }

    @Test
    void removeFlavour() {
        assertThat(cheese1.addFlavour(flavour1)).isTrue();
        assertThat(cheese1.removeFlavour(flavour1)).isTrue();
        assertThat(cheese1.getFlavours()).isEmpty();
        assertThat(flavour1.getCheeses()).isEmpty();
    }
    //Test many to many cheese/animal
    @Test
    void addAnimal() {
        assertThat(cheese1.addAnimal(animal1)).isTrue();
        assertThat(cheese1.getAnimals()).containsOnly(animal1);
    }

    @Test
    void removeAnimal() {
        assertThat(cheese1.addAnimal(animal1)).isTrue();
        assertThat(cheese1.removeAnimal(animal1)).isTrue();
        assertThat(cheese1.getAnimals()).isEmpty();
        assertThat(animal1.getCheeses()).isEmpty();
    }
}
