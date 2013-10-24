package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event extends KeyedEntity {

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    Date eventTimestamp;

    @ManyToMany
    @JoinTable
    Set<Agent> agents;

    public Event(String description, Date eventTimestamp) {
        super();
        this.setDescription(description);
        this.eventTimestamp = eventTimestamp;
    }

    protected Event() {
        super();
    }

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
