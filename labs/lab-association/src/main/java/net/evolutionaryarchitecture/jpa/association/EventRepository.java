package net.evolutionaryarchitecture.jpa.association;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EventRepository {

    @PersistenceContext
    EntityManager em;

    public void registerForEvent(long personId, long eventId) {

        // TODO:
        // Create an link between the person and event referenced
        // (ie add the event to the persons 'eventsRegisteredFor' set)

    }

    public Set<Person> getRegisteredUsersForEvent(long eventId) {

        // TODO:
        // Return a set of persons registered for the event.

        return null;
    }

    public Person registerNewUser(String firstName, String lastName) {
        Person newPerson = new Person(new Name(firstName, lastName));
        em.persist(newPerson);
        return newPerson;
    }

    public void addNewAddressForUser(Long personId, String street, String city,
            String postalCode, String country) {
        Person user = em.getReference(Person.class, personId);
        user.getAddresses().add(new Address(street, city, postalCode, country));
        em.flush();
    }

    public Long registerNewEvent(Event event) {
        em.persist(event);
        em.flush();
        return event.getId();
    }

    public void addNewEmailForUser(long personId, String email) {
        Person user = (Person) em.getReference(Person.class, personId);
        user.getEmailAddresses().add(email);
        em.flush();
    }

}
