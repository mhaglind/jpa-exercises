package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Entity;

/**
 * A consumer of events.
 */
@Entity
public class Sink extends KeyedEntity {

    String description;

    public Sink(String description) {
        super();
        this.description = description;
    }

    protected Sink() {
        super();
    }

}
