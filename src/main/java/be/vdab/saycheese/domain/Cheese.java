package be.vdab.saycheese.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cheese")
public class Cheese {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean vegetarian;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryId")
    private Country country;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "colourId")
    private Colour colour;
    private int likes;
    private int dislikes;
    private int version;
    @ManyToMany(mappedBy = "cheeses")
    private Set<Flavour> flavours = new LinkedHashSet<>();
    @ManyToMany(mappedBy = "cheeses")
    private Set<Animal> animals = new LinkedHashSet<>();
    @ElementCollection
    @CollectionTable(name = "webpages",
            joinColumns = @JoinColumn(name = "cheeseId"))
    @Column(name = "url")
    private Set<String> urls;

    public Cheese(String name, boolean vegetarian, Country country, Colour colour, int likes, int dislikes, int version) {
        this.name = name;
        this.vegetarian = vegetarian;
        setCountry(country);
        setColour(colour);
        this.likes = likes;
        this.dislikes = dislikes;
        this.version = version;
        this.urls = new LinkedHashSet<>();
    }

    protected Cheese() {
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getVersion() {
        return version;
    }

    public Set<String> getUrls() {
        return Collections.unmodifiableSet(urls);
    }

    public Country getCountry() {
        return country;
    }

    public Colour getColour() {
        return colour;
    }
    public Set<Flavour> getFlavours() {
        return Collections.unmodifiableSet(flavours); }
    public Set<Animal> getAnimals() {
        return Collections.unmodifiableSet(animals); }

    //Setters
    public void setCountry(Country country) {
        if (!country.getCheeses().contains(this)) {
            country.add(this);
        }
        this.country = country;
    }

    public void setColour(Colour colour) {
        if (!colour.getCheeses().contains(this)) {
            colour.add(this);
        }
        this.colour = colour;
    }


    //Methods Set<String> urls
    public boolean addUrl(String url) {
        if (url.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return urls.add(url);
    }

    public boolean removeUrl(String url) {
        return urls.remove(url);
    }
    //Methods many to many cheese/flavours
    public boolean addFlavour(Flavour flavour){
        var addFlavour = flavours.add(flavour);
        if(!flavour.getCheeses().contains(this)){
            flavour.add(this);
        }
        return addFlavour;
    }

    public boolean removeFlavour(Flavour flavour){
        var removeFlavour = flavours.remove(flavour);
        if(flavour.getCheeses().contains(this)){
            flavour.remove(this);
        }
        return removeFlavour;
    }
    //Methods many to many cheese/animals
    public boolean addAnimal(Animal animal){
        var addAnimal = animals.add(animal);
        if(!animal.getCheeses().contains(this)){
            animal.add(this);
        }
        return addAnimal;
    }

    public boolean removeAnimal(Animal animal){
        var removeAnimal = animals.remove(animal);
        if(animal.getCheeses().contains(this)){
            animal.remove(this);
        }
        return removeAnimal;
    }

    // equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cheese cheese = (Cheese) o;
        return name.equals(cheese.name) && country.equals(cheese.country) && colour.equals(cheese.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, colour);
    }
}
