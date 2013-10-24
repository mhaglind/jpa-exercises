package net.evolutionaryarchitecture.jpa.samples;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.evolutionaryarchitecture.jpa.samples.Event;
import net.evolutionaryarchitecture.jpa.samples.EventRepository;
import net.evolutionaryarchitecture.jpa.samples.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class NamedQueriesDemo {

  @Autowired
  EventRepository eventRepo;

  @PersistenceContext
  EntityManager em;

  @Test
  public void namedQlQuery() {
    DemoDataHelper.createDefaultDataSet(eventRepo);

    List<Event> events = em
        .createNamedQuery("findEventsByParticipantCountry",
            Event.class).setParameter("country", "Sverige")
        .getResultList();
    assertEquals(3, events.size());
  }

  @Test
  public void namedNativeQuery() {
    DemoDataHelper.createDefaultDataSet(eventRepo);

    List<Person> persons = em
        .createNamedQuery("findPersonsByCountry", Person.class)
        .setParameter(1, "Sverige").getResultList();
    assertEquals(1, persons.size());
  }

}
