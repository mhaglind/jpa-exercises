package net.evolutionaryarchitecture.jpa.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import net.evolutionaryarchitecture.jpa.samples.Agent;

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
public class FlushingIntegrationTest {

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
        agentId = (Long) agent.getId();
        tx.commit();
        entityManager.close();
    }

    @Test
    public void autoFlushMode() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.setFlushMode(FlushModeType.AUTO); // Flushing to occur at
                                                        // query execution.

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        printAgent("at start");

        Agent agent = (Agent) entityManager.find(Agent.class, agentId);
        agent.setDescription("A");
        printAgent("after setting A");

        tx.commit();
        entityManager.close();
        printAgent("after commit");
    }

    @Test
    public void commitFlushMode() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.setFlushMode(FlushModeType.COMMIT); // Flushing to occur
                                                          // at query execution.

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        printAgent("at start");

        Agent agent = (Agent) entityManager.find(Agent.class, agentId);
        agent.setDescription("A");
        printAgent("after setting A");

        tx.commit();
        printAgent("after commit");

        entityManager.close();
    }


    private void printAgent(String text) {
        System.out.println("\nPrinting agent "
                + text
                + ": "
                + jdbcTemplate.queryForMap("select * from Agent where id="
                        + agentId) + "\n");
    }

}
