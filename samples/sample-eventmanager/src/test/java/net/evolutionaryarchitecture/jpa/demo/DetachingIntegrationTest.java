package net.evolutionaryarchitecture.jpa.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import net.evolutionaryarchitecture.jpa.samples.Agent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DetachingIntegrationTest {

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void attachingEntity() {

        Agent agent = new Agent("An agent");

        System.out.println("\nTransient");
        System.out.println("Agent: " + agent);
        System.out.println("Agent class: " + agent.getClass());

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(agent);

        System.out.println("\nAttached");
        System.out.println("Agent: " + agent);
        System.out.println("Agent class: " + agent.getClass());

        entityManager.close();
    }

}
