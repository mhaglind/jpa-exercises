package net.evolutionaryarchitecture.jpa.demo;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import net.evolutionaryarchitecture.jpa.samples.Agent;

import org.hibernate.LazyInitializationException;
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
public class GetVsFindIntegrationTest {

    @Autowired
    DataSource dataSource;

    @PersistenceUnit
    EntityManagerFactory emf;

    private JdbcTemplate jdbcTemplate;

    private Long agentId;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        // Create an agent for testing
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Agent agent = new Agent("An agent");
        entityManager.persist(agent);
        agentId = agent.getId();
        tx.commit();
        entityManager.close();
    }

    @Test
    public void findExisting() {
        EntityManager entityManager = emf.createEntityManager();
        // EntityTransaction tx = entityManager.getTransaction();
        // tx.begin();

        System.out.println("Before find");
        Agent agent = entityManager.find(Agent.class, agentId);
        System.out.println("After find");
        System.out.println(agent);

        // tx.commit();
        entityManager.close();
    }

    @Test
    public void findNonExisting() {
        EntityManager entityManager = emf.createEntityManager();
        // EntityTransaction tx = entityManager.getTransaction();

        System.out.println("Before find");
        Agent agent = entityManager.find(Agent.class, 666L);
        System.out.println("After find");

        try {
            System.out.println(agent);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        // tx.commit();
        entityManager.close();
    }

    @Test
    public void getDehydrated() {
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Before load");
        Agent agentLoad = entityManager.getReference(Agent.class, agentId);

        System.out.println("After load");

        entityManager.close();

        System.out.println("After em.close");

        try {
            System.out.println(agentLoad);
            fail();
        } catch (LazyInitializationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getExisting() {
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Before get");
        Agent agentGet = (Agent) entityManager.getReference(Agent.class,
                agentId);

        System.out.println("After get");
        System.out.println(agentGet);

        entityManager.close();
    }

}
