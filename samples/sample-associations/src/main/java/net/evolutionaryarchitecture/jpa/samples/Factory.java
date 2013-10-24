package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Factory {

    @Id
    @GeneratedValue
    Long factoryId;

    String name;

    @ManyToOne
    BusinessUnit unit;

    public Factory() {
        super();
    }

    public Factory(String name, BusinessUnit unit) {
        this.name = name;
        this.unit = unit;
    }
}
