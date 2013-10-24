package net.evolutionaryarchitecture.jpa.samples.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Person implements Serializable {

    private static final long serialVersionUID = -6191289534525542419L;

    protected Long id;

    protected Date lastUpdated;

    protected Name name;

    protected Set<Address> addresses;

    protected Set<Event> events;
    
    protected Set<String> emailAddresses;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    
    public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(Set<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	protected Person() {
        super();
    }

    public Person(Name name, Set<Address> addresses) {
        this();
        this.name = name;
        this.addresses = addresses;
    }

    public Person(Name name, Set<Address> addresses, Set<String> emailAddresses) {
        this(name, addresses);
        this.emailAddresses = emailAddresses;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((addresses == null) ? 0 : addresses.hashCode());
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Person other = (Person) obj;
        if (addresses == null) {
            if (other.addresses != null)
                return false;
        } else if (!addresses.equals(other.addresses))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public String toString() {
    	return "Person " + this.getName().toString();
    }

}
