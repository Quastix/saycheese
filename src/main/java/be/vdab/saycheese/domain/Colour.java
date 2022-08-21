package be.vdab.saycheese.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "colours")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany(mappedBy = "colour")
    private Set<Cheese> cheeses;

    public Colour(String name) {
        this.name = name;
        this.cheeses = new LinkedHashSet<>();
    }
    protected Colour(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Set<Cheese> getCheeses(){
        return Collections.unmodifiableSet(cheeses);
    }

    public boolean add(Cheese cheese){
        var addCheese = cheeses.add(cheese);
        return addCheese;
    }
}
