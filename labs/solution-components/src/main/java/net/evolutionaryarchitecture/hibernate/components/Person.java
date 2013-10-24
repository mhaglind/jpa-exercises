package net.evolutionaryarchitecture.hibernate.components;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_PERSON")
public class Person implements Serializable {

    private static final long serialVersionUID = -6191289534525542419L;

    @Id
    @GeneratedValue
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdated;

    @Embedded
    protected Name name;

    @ElementCollection
    @CollectionTable(name = "T_ADDRESS")
    protected Set<Address> addresses;

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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    protected Person() {
        super();
    }

    public Person(Name name) {
        this();
        this.name = name;
    }

    public Person(Name name, Set<Address> addresses) {
        this(name);
        setAddresses(addresses);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((addresses == null) ? 0 : addresses.hashCode());
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

}
