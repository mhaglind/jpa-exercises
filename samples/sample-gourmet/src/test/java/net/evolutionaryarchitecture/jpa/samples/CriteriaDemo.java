package net.evolutionaryarchitecture.jpa.samples;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

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
public class CriteriaDemo {

  @Autowired
  EventRepository eventRepo;

  @PersistenceContext
  EntityManager em;

  @Test
  public void basicCriteria() {

    DemoDataHelper.createDefaultDataSet(eventRepo);

    System.out.println("\n\n\tbasicCriteria\n");

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Person> query = cb.createQuery(Person.class);
    Root<Person> root = query.from(Person.class);
    query.select(root);

    List<Person> persons = em.createQuery(query).getResultList();

    assertEquals(4, persons.size());

  }

  @Test
  public void criteriaWithWhereClause() {

    DemoDataHelper.createDefaultDataSet(eventRepo);

    System.out.println("\n\n\tcriteriaWithWhereClause\n");

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Person> query = cb.createQuery(Person.class);
    Root<Person> root = query.from(Person.class);

    ParameterExpression<String> lastName = cb.parameter(String.class,
        "lastName");

    query.select(root).where(
        cb.equal(root.get("name").get("lastName"), lastName));

    List<Person> persons = em.createQuery(query)
        .setParameter("lastName", "Eriksson").getResultList();

    assertEquals(2, persons.size());

  }

}
