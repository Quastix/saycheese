package be.vdab.saycheese.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlavourTest {
    private Cheese cheese1;
    private Country country1;
    private Colour colour1;
    private Flavour flavour1;

    @BeforeEach
    void beforeEach() {
        flavour1 = new Flavour("chocolade");
        country1 = new Country("test");
        colour1 = new Colour("test");
        cheese1 = new Cheese("test1", true, country1, colour1, 1, 2, 3);
    }

    @Test
    void addCheese() {
        assertThat(flavour1.getCheeses()).isEmpty();
        assertThat(flavour1.add(cheese1)).isTrue();
        assertThat(flavour1.getCheeses()).containsOnly(cheese1);
        assertThat(cheese1.getFlavours()).containsOnly(flavour1);
    }

    @Test
    void docentVerwijderen() {
        assertThat(flavour1.add(cheese1)).isTrue();
        assertThat(flavour1.remove(cheese1)).isTrue();
        assertThat(flavour1.getCheeses()).isEmpty();
        assertThat(cheese1.getFlavours()).isEmpty();
    }
}
