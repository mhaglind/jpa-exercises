package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Entity;

@Entity
public class Pipe extends KeyedEntity {

    String description;

    public Pipe(String description) {
        super();
        this.description = description;
    }

    protected Pipe() {
        super();
    }

}
