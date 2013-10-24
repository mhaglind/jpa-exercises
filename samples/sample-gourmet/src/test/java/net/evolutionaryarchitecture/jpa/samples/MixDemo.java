package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import net.evolutionaryarchitecture.jpa.samples.EventRepository;
import net.evolutionaryarchitecture.jpa.samples.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MixDemo {

  @Autowired
  EventRepository eventRepo;

  @PersistenceContext
  EntityManager em;

  @PersistenceUnit
  EntityManagerFactory emf;

  @Autowired
  DataSource ds;

  JdbcTemplate jt;

  @Before
  public void setUpJdbcTemplate() {
    jt = new JdbcTemplate(ds);
  }

  @Test
  public void mixScenario() {
    DemoDataHelper.createDefaultDataSet(eventRepo);

    Person person = em.createQuery(
        "from Person where name.firstName='Johan'", Person.class)
        .getSingleResult();
    long id = person.getId();

    System.out.println("\n1. Firstname: "
        + person.getName().getFirstName());

    person.getName().setFirstName("Nisse");

    String jdbcName = jt.queryForObject(
        "select first from t_persons where person_id = " + id,
        String.class);
    System.out.println("\n2. Firstname: " + jdbcName);

    Person person2 = em.find(Person.class, id);
    System.out.println("\n3. Firstname: "
        + person2.getName().getFirstName());
  }

  @Test
  public void mixScenarioFlush() {
    DemoDataHelper.createDefaultDataSet(eventRepo);

    Person person = em.createQuery(
        "from Person where name.firstName='Johan'", Person.class)
        .getSingleResult();
    long id = person.getId();

    System.out.println("\n1. Firstname: "
        + person.getName().getFirstName());

    person.getName().setFirstName("Nisse");
    em.flush();

    String jdbcName = jt.queryForObject(
        "select first from t_persons where person_id = " + id,
        String.class);
    System.out.println("\n2. Firstname: " + jdbcName);

    Person person2 = em.find(Person.class, id);
    System.out.println("\n3. Firstname: "
        + person2.getName().getFirstName());
  }

}
