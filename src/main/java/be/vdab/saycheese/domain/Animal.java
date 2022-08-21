package be.vdab.saycheese.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name="cheeseanimals",
            joinColumns = @JoinColumn(name ="animalId"),
            inverseJoinColumns = @JoinColumn(name = "cheeseId"))
    private Set<Cheese> cheeses = new LinkedHashSet<>();

    public Animal(String name) {
        this.name = name;
    }

    protected Animal(){}
    // getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Set<Cheese> getCheeses(){
        return Collections.unmodifiableSet(cheeses);
    }

    //Methods many to many cheese/flavours
    public boolean add (Cheese cheese){
        var addCheese = cheeses.add(cheese);
        if(!cheese.getAnimals().contains(this)){
            cheese.addAnimal(this);
        }
        return addCheese;
    }

    public boolean remove(Cheese cheese){
        var removeCheese = cheeses.remove(cheese);
        if(cheese.getAnimals().contains(this)){
            cheese.removeAnimal(this);
        }
        return removeCheese;
    }

    //equals and hashcode for many to many relations

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
