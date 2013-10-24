package net.evolutionaryarchitecture.jpa.samples;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
public class JpaQLIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void allEntities() {        
        
        List<BusinessUnit> businessUnits = em.createQuery("from BusinessUnit", BusinessUnit.class).getResultList();
        assertEquals(3, businessUnits.size());
        
        List<Factory> factories = em.createQuery("from Factory", Factory.class).getResultList();
        assertEquals(5, factories.size()); 
        
        List<Job> jobs = em.createQuery("from Job", Job.class).getResultList();
        assertEquals(274, jobs.size());    
        
        List<Manufacturer> manufacturers = em.createQuery("from Manufacturer", Manufacturer.class).getResultList();
        assertEquals(10, manufacturers.size());    
        
        List<Product> products = em.createQuery("from Product", Product.class).getResultList();
        assertEquals(15, products.size());  
        
    }
   
}
