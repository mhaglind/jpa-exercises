package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

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
public class OrderItemIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createOrder() {

        Order order = new Order();
        order.orderNr = "ABC123";
        order.addOrderItem("FKW-1864-B", 50);
        order.addOrderItem("THX-9483-B", 12);

        em.persist(order);
        em.flush();
        
    }

}
