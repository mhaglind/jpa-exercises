package net.evolutionaryarchitecture.jpa.samples;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

/**
 * An emitter of events.
 */
@Entity
public class Agent extends KeyedEntity {

    private String description;
    
    @Version
    private long optLockVersion;

    @ManyToMany(mappedBy = "agents")
    Set<Event> events;

    public Agent(String description) {
        super();
        this.setDescription(description);
    }

    protected Agent() {
        super();
    }

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agent [description=").append(description)
				.append(", id=").append(id).append(", key=").append(key)
				.append("]");
		return builder.toString();
	}

	public Set<Event> getEvents() {
		return events;
	}

}
