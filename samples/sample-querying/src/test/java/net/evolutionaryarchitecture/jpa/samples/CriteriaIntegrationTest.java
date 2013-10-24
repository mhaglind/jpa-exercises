package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CriteriaIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void query() {
        System.out.println("TODO");
    }

}
