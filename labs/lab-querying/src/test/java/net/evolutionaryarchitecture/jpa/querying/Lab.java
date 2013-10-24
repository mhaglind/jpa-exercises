package net.evolutionaryarchitecture.jpa.querying;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class Lab {

    @Autowired
    EventRepository eventRepo;

    @PersistenceContext
    EntityManager em;

    @Test
    public void testHqlQuerying() {

        // TODO:
        // Inspect the TestDataHelper - it creates some test data to work with.
        LabDataHelper.createDefaultDataSet(eventRepo);

        Assert.assertEquals(7, executeJpaQlQuery("from Event"));

        // TODO:
        // Replace XXX below with the queries described

        // Get all Courses sorted by date
        Assert.assertEquals(4, executeJpaQlQuery("from Course c order by c.date"));

        // All Persons with a lastname of 'Karlsson'
        Assert.assertEquals(1, executeJpaQlQuery("from Person p where p.name.lastName like 'Karlsson'"));

        // All Persons that are attending the conference 'JavaOne'
        Assert.assertEquals(1, executeJpaQlQuery("select p from Person p join p.eventsRegisteredFor c" +
        		" where " +
        		"c.title='JavaOne'"));

        // All OnsiteCourses in February
//        Assert.assertEquals(1, executeJpaQlQuery("XXX"));

        // All Events containing the word 'Spring' in the title
//        Assert.assertEquals(2, executeJpaQlQuery("XXX"));

        // All Events attended by persons that lives in Sweden
//        Assert.assertEquals(3, executeJpaQlQuery("XXX"));

    }

    private int executeJpaQlQuery(String query) {
        System.out.println("\n\nExecuting query " + query);

        @SuppressWarnings("rawtypes")
        List returnedEntities = em.createQuery(query).getResultList();
        for (Object obj : returnedEntities) {
            System.out.println(obj);
        }
        int numberOfRowsReturned = returnedEntities.size();
        System.out.println("Number of rows: " + numberOfRowsReturned);
        return numberOfRowsReturned;
    }

}
