package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.evolutionaryarchitecture.jpa.samples.StateChange.State;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class JobIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void lifecycle() throws InterruptedException {

        Job job = new Job();  
        
        job.changeState(State.ONGOING);
        
        Thread.sleep(1000);
        job.changeState(State.DONE);

        em.persist(job);
        em.flush();
        
    }

}
