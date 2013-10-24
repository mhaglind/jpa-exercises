package net.evolutionaryarchitecture.jpa.querying;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import net.evolutionaryarchitecture.jpa.querying.EventRepository;

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
	public void testJpaQlQuerying() {

		LabDataHelper.createDefaultDataSet(eventRepo);

		Assert.assertEquals(7, executeJpaQuery("from Event"));

		Assert.assertEquals(4, executeJpaQuery("from Course order by date"));

		// All Persons with a lastname of 'Karlsson'
		Assert.assertEquals(
				1,
				executeJpaQuery("from Person p where p.name.lastName = 'Karlsson'"));

		// All Persons that are attending the conference 'JavaOne'
		Assert.assertEquals(
				1,
				executeJpaQuery("select p from Person p join p.eventsRegisteredFor e where e.title = 'JavaOne' and e.class=Conference"));

		// All OnsiteCourses in February
		Assert.assertEquals(
				1,
				executeJpaQuery("from OnsiteCourse c where c.date between '2011-02-01' and '2011-02-28'"));

		// All Events containing the word 'Spring' in the title
		Assert.assertEquals(2,
				executeJpaQuery("from Event e where e.title like '%Spring%'"));

		// All Events attended by persons that lives in Sweden
		Assert.assertEquals(
				3,
				executeJpaQuery("select e from Event e join e.participants p join p.addresses a where a.country = 'Sverige'"));

	}

	private int executeJpaQuery(String query) {
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
