package net.evolutionaryarchitecture.jpa.querying;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PERSONS")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "PERSON_ID")
    private Long id;
    
    @Column(name="PERSON_AGE")
    private int age;
    
    @Embedded
    private Name name;
    
    @ElementCollection
    @CollectionTable(name="T_ADDRESS")
    private Set<Address> addresses = new HashSet<Address>();
    
    @ElementCollection
    @CollectionTable(name="T_PERSON_EMAIL_ADDR")
    private Set<String> emailAddresses = new HashSet<String>();
    
    @ManyToMany
    @JoinTable(name="T_EVENT_REGISTRATIONS")
    private Set<Event> eventsRegisteredFor = new HashSet<Event>();

    protected Person() {
    }

    protected Person(Name name) {
        setName(name);
    }

    public void addRegistrationToEvent(Event event) {
        this.getEventsRegisteredFor().add(event);
        event.getParticipants().add(this);
    }

    public void removeRegistrationFromEvent(Event event) {
        this.getEventsRegisteredFor().remove(event);
        event.getParticipants().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Set<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public Set<Event> getEventsRegisteredFor() {
        return eventsRegisteredFor;
    }

    @SuppressWarnings("unused")
    private void setEventsRegisteredFor(Set<Event> eventsRegisteredFor) {
        this.eventsRegisteredFor = eventsRegisteredFor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Person other = (Person) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person [id=").append(id).append(", age=").append(age)
                .append(", name=").append(name).append(", addresses=")
                .append(addresses).append(", emailAddresses=")
                .append(emailAddresses).append("]");
        return builder.toString();
    }

}