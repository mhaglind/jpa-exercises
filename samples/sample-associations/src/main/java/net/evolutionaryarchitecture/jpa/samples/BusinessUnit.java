package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessUnit {

    @Id
    String name;

    protected BusinessUnit() {
        super();
    }

    public BusinessUnit(String name) {
        this.name = name;
    }
}
