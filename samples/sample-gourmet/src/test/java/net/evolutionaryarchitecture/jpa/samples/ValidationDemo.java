package net.evolutionaryarchitecture.jpa.samples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import net.evolutionaryarchitecture.jpa.samples.EventRepository;
import net.evolutionaryarchitecture.jpa.samples.Name;
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
public class ValidationDemo {

  @Autowired
  EventRepository eventRepo;

  @PersistenceContext
  EntityManager em;

  @Test
  public void validation() {
    Person person = new Person(new Name("Mister", "Wrong"));
    person.setAge(-1);
    try {
      em.persist(person);
      fail();
    } catch (ConstraintViolationException ex) {
      assertEquals("Age must be greater than zero", ex
          .getConstraintViolations().iterator().next().getMessage());
    }
  }

}
