package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Entity;

@Entity
public class Publisher extends KeyedEntity {

    String description;

    public Publisher(String description) {
        super();
        this.description = description;
    }

    protected Publisher() {
        super();
    }

}
