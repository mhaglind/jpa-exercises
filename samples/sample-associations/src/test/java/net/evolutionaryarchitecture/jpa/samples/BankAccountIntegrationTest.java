package net.evolutionaryarchitecture.jpa.samples;

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
public class BankAccountIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void inheritance() {

        em.persist(new BankAccount());
        em.persist(new CreditCard());
        em.persist(new SavingsAccount());

        List<BankAccount> list = em.createQuery("from BankAccount",
                BankAccount.class).getResultList();

        for (BankAccount account : list) {
            System.out.println(account.getClass() + ": " + account.id);
        }
    }

}
