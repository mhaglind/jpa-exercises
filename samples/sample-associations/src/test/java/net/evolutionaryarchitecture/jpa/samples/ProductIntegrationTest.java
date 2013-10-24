package net.evolutionaryarchitecture.jpa.samples;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    Product p1 = new Product();
    Product p2 = new Product();
    Manufacturer m1 = new Manufacturer();
    Manufacturer m2 = new Manufacturer();

    @Before
    public void setUpData() {
        em.persist(p1);
        em.persist(p2);
        em.persist(m1);
        em.persist(m2);
        em.flush();
    }

    @Test
    public void inverseEnd() {

        m1.productsManufactured.add(p1);
        m1.productsManufactured.add(p2);
        m2.productsManufactured.add(p2);

        em.flush();
        em.refresh(m1);
        em.refresh(m2);

        assertEquals(0, m1.productsManufactured.size());
        assertEquals(0, m2.productsManufactured.size());

        assertEquals(0, p1.manufacturers.size());
        assertEquals(0, p2.manufacturers.size());
    }

    @Test
    public void normalEnd() {
        p1.manufacturers.add(m1);
        p2.manufacturers.add(m1);
        p1.manufacturers.add(m2);

        em.flush();
        em.refresh(m1);
        em.refresh(m2);

        assertEquals(2, m1.productsManufactured.size());
        assertEquals(1, m2.productsManufactured.size());

        assertEquals(2, p1.manufacturers.size());
        assertEquals(1, p2.manufacturers.size());
    }

}
