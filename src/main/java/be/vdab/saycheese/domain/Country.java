package be.vdab.saycheese.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "country")
    private Set<Cheese> cheeses;

    public Country(String name) {
        this.name = name;
        this.cheeses = new LinkedHashSet<>();
    }

    protected Country(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Methods bidirectional oneToMany cheeses
    public Set<Cheese> getCheeses(){
        return Collections.unmodifiableSet(cheeses);
    }

    public boolean add(Cheese cheese){
        var addCheese = cheeses.add(cheese);
        return addCheese;
    }
}
