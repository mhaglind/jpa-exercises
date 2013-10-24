package net.evolutionaryarchitecture.jpa.samples;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EventRepository {

  @PersistenceContext
  EntityManager em;

  public Person registerNewUser(String firstName, String lastName) {
    Person newPerson = new Person(new Name(firstName, lastName));
    em.persist(newPerson);
    em.flush();
    return newPerson;
  }

  public void addNewAddressForUser(Long personId, String street,
      String city, String postalCode, String country) {
    Person user = em.getReference(Person.class, personId);
    user.getAddresses().add(
        new Address(street, city, postalCode, country));
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

  public void registerForEvent(long personId, long eventId) {
    Person personToRegister = em.getReference(Person.class, personId);
    Event eventToRegisterFor = em.getReference(Event.class, eventId);

    personToRegister.addRegistrationToEvent(eventToRegisterFor);
    em.flush();
  }

  public Event getEventWithParticipants(long eventId) {
    Query query = em.createQuery("from Event event "
        + "left join fetch event.participants participants "
        + "left join fetch participants.addresses addresses "
        + "where event.id=:eventId");
    query.setParameter("eventId", eventId);
    return (Event) query.getSingleResult();
  }
  
  public Set<Person> getRegisteredUsersForEvent(long eventId) {
    Query query = em.createQuery("from Event event "
        + "left join fetch event.participants participants "
        + "left join fetch participants.addresses addresses "
        + "where event.id=:eventId");
    query.setParameter("eventId", eventId);
    Event event = (Event) query.getSingleResult();
    Set<Person> participants = event.getParticipants();
    for (Person participant : participants) {
      System.out.println(participant.getName());
    }
    return participants;
  }

  public Set<Event> getRegisteredEventsForUser(long personId) {
    Person person = em.find(Person.class, personId);
    return person.getEventsRegisteredFor();
  }

}
