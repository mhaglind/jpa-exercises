package net.evolutionaryarchitecture.jpa.association;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = -1112950418544161356L;

    String street;

    String city;

    String postalCode;

    String country;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    protected Address() {
        super();
    }

    public Address(String street, String city, String postalCode, String country) {
        this();
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((city == null) ? 0 : city.hashCode());
        result = PRIME * result + ((country == null) ? 0 : country.hashCode());
        result = PRIME * result
                + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = PRIME * result + ((street == null) ? 0 : street.hashCode());
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
        final Address other = (Address) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.getStreet() + " - " + this.getCity() + " - "
                + this.getCountry();
    }

}
