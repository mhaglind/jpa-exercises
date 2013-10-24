package net.evolutionaryarchitecture.jpa.demo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;
import javax.sql.DataSource;

import net.evolutionaryarchitecture.jpa.samples.Agent;
import net.evolutionaryarchitecture.jpa.samples.Event;

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
public class OptimisticLockingIntegrationTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    EntityManagerFactory emf;

    private JdbcTemplate jdbcTemplate;

    private Long agentId;

    private Long eventId;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);

        // Create an agent and event for testing
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Agent agent = new Agent("An agent");
        entityManager.persist(agent);
        agentId = agent.getId();

        Event event = new Event("An event", new Date());
        entityManager.persist(event);
        eventId = event.getId();

        tx.commit();
        entityManager.close();

    }

    @Test
    public void entityWithoutOptimisticLocking() {

        EntityManager entityManagerA = emf.createEntityManager();
        EntityTransaction txA = entityManagerA.getTransaction();
        txA.begin();

        EntityManager entityManagerB = emf.createEntityManager();
        EntityTransaction txB = entityManagerB.getTransaction();
        txB.begin();

        // Session sessionA = sessionFactory.openSession();
        // Transaction txA = sessionA.beginTransaction();
        //
        // Session sessionB = sessionFactory.openSession();
        // Transaction txB = sessionB.beginTransaction();

        Event eventA = (Event) entityManagerA.find(Event.class, eventId);
        eventA.setDescription("Session A sets description");

        Event eventB = (Event) entityManagerB.find(Event.class, eventId);
        eventB.setDescription("Session B sets description");

        txA.commit();
        entityManagerA.close();

        txB.commit();
        entityManagerB.close();

        printEvent("After test");

    }

    @Test
    public void entityWithOptimisticLocking() {

        EntityManager entityManagerA = emf.createEntityManager();
        EntityTransaction txA = entityManagerA.getTransaction();
        txA.begin();

        EntityManager entityManagerB = emf.createEntityManager();
        EntityTransaction txB = entityManagerB.getTransaction();
        txB.begin();

        Agent agentA = (Agent) entityManagerA.find(Agent.class, agentId);
        agentA.setDescription("Session A sets description");

        Agent agentB = (Agent) entityManagerB.find(Agent.class, agentId);
        agentB.setDescription("Session B sets description");

        txA.commit();
        entityManagerA.close();

        try {
            txB.commit();
        } catch (RollbackException ex) {
            assertEquals(OptimisticLockException.class, ex.getCause()
                    .getClass());
            System.out.println(ex.getMessage());
        }
        
//        entityManagerB.refresh(agentB);
//        agentB.setDescription("Session B sets description");
//        txB.commit();
        
        entityManagerB.close();

        printAgent("After test");

    }

    private void printAgent(String text) {
        System.out.println("\nPrinting agent "
                + text
                + ": "
                + jdbcTemplate.queryForMap("select * from Agent where id="
                        + agentId) + "\n");
    }

    private void printEvent(String text) {
        System.out.println("\nPrinting event "
                + text
                + ": "
                + jdbcTemplate.queryForMap("select * from Event where id="
                        + eventId) + "\n");
    }
}
