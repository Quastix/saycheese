package be.vdab.saycheese.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="flavours")
public class Flavour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name="cheeseflavours",
            joinColumns = @JoinColumn(name ="flavourId"),
            inverseJoinColumns = @JoinColumn(name = "cheeseId"))
    private Set<Cheese> cheeses = new LinkedHashSet<>();

    public Flavour(String name) {
        this.name = name;
    }

    protected Flavour(){}

    public long getId() {
        return id;
    }
    // Getters
    public String getName() {
        return name;
    }
    public Set<Cheese> getCheeses(){
        return Collections.unmodifiableSet(cheeses);
    }

    //Methods many to many cheese/flavours
    public boolean add (Cheese cheese){
        var addCheese = cheeses.add(cheese);
        if(!cheese.getFlavours().contains(this)){
            cheese.addFlavour(this);
        }
        return addCheese;
    }

    public boolean remove(Cheese cheese){
        var removeCheese = cheeses.remove(cheese);
        if(cheese.getFlavours().contains(this)){
            cheese.removeFlavour(this);
        }
        return removeCheese;
    }

    //equals and hashcode for many to many relations
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flavour flavour = (Flavour) o;
        return name.equals(flavour.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
