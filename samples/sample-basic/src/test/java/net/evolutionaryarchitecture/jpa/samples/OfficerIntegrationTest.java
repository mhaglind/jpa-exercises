package net.evolutionaryarchitecture.jpa.samples;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OfficerIntegrationTest {

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void basic() {

        // 1
        System.out.println("--- 1 ---");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Officer newOfficer = new Officer();
        newOfficer.setName("David Sanders");
        entityManager.persist(newOfficer);
        long officerId = newOfficer.getId();
        System.out.println("Generated id: " + officerId);

        entityManager.getTransaction().commit();
        entityManager.close();

        // 2
       
        System.out.println("--- 2 ---");

        EntityManager entityManager2 = emf.createEntityManager();
        entityManager2.getTransaction().begin();

        @SuppressWarnings("unchecked")
        List<Officer> officers = entityManager2.createQuery(
                "from Officer o order by o.name asc").getResultList();

        for (Officer officer : officers) {
            System.out.println(officer);
        }
        
        entityManager2.getTransaction().commit();
        entityManager2.close();
        
        // 3
        System.out.println("--- 3 ---");
        EntityManager entityManager3 = emf.createEntityManager();
        entityManager3.getTransaction().begin();

        Officer foundOfficer = entityManager3.find(Officer.class, officerId);
        Officer superior = new Officer("Ronald McD", 5,
                "Head boss");
        foundOfficer.setSuperiorOfficer(superior);

        entityManager3.getTransaction().commit();
        entityManager3.close();
        
    }

}
