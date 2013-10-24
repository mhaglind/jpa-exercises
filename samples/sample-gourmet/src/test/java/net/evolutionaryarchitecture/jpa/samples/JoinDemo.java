package net.evolutionaryarchitecture.jpa.samples;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.evolutionaryarchitecture.jpa.samples.Conference;
import net.evolutionaryarchitecture.jpa.samples.EventRepository;
import net.evolutionaryarchitecture.jpa.samples.Name;
import net.evolutionaryarchitecture.jpa.samples.OnsiteCourse;
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
public class JoinDemo {

  @Autowired
  EventRepository eventRepo;

  @PersistenceContext
  EntityManager em;

  @Test
  public void fkAssociation() {
    OnsiteCourse onsiteCourse = new OnsiteCourse("Effective JPA",
        Timestamp.valueOf("2011-12-01 08:00:00"), "MŒrten Haglind",
        "Volvo");
    em.persist(onsiteCourse);

    Person theCustomer = new Person(new Name("Nils", "Nilsson"));
    em.persist(theCustomer);

    onsiteCourse.setTechnicalContact(theCustomer);
    em.flush();
  }

  @Test
  public void joinTableAssociation() {
    OnsiteCourse onsiteCourse = new OnsiteCourse("Effective JPA",
        Timestamp.valueOf("2011-12-01 08:00:00"), "MŒrten Haglind",
        "Volvo");
    em.persist(onsiteCourse);

    Person theCustomer = new Person(new Name("Eva", "Andersson"));
    em.persist(theCustomer);

    onsiteCourse.setBillingContact(theCustomer);
    em.flush();
  }

  @Test
  public void oneToManyWithoutJoinTable() {
    DemoDataHelper.createDefaultDataSet(eventRepo);

    Conference conference = em.createQuery(
        "from Conference where title='JavaOne'", Conference.class)
        .getSingleResult();
    Person person = em.createQuery(
        "from Person where name.firstName='Johan'", Person.class)
        .getSingleResult();

    conference.addOrganizer(person);

    em.flush();
  }

}
