package net.evolutionaryarchitecture.jpa.samples.domain;

import java.io.Serializable;

public class Name implements Serializable {

    private static final long serialVersionUID = -1667403471458386436L;

    String firstName;

    String lastName;

    String namePrefix;

    String nameSuffix;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getLongDisplayName() {
        return firstName + " " + lastName;
        
    }
    
    private Name() {
        super();
    }

    public Name(String firstName, String lastName, String namePrefix, String nameSuffix) {
        this(firstName, lastName);
        this.namePrefix = namePrefix;
        this.nameSuffix = nameSuffix;
    }

    public Name(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = PRIME * result + ((lastName == null) ? 0 : lastName.hashCode());
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
        final Name other = (Name) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }
    
    public String toString() {
    	return this.getLongDisplayName();
    }
}
