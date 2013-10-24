package net.evolutionaryarchitecture.jpa.association;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import junit.framework.Assert;

import net.evolutionaryarchitecture.jpa.association.EventRepository;
import net.evolutionaryarchitecture.jpa.association.Person;

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
public class Lab {

    @Autowired
    EventRepository eventRepo;

    @Autowired
    DataSource dataSource;

    @PersistenceContext
    EntityManager em;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void registerForEvent() {
        eventRepo.registerForEvent(1L, 2L);
        eventRepo.registerForEvent(1L, 3L);
        Assert.assertEquals(2, jdbcTemplate
                .queryForInt("select count(*) from T_EVENT_REGISTRATIONS"));

    }

    @Test
    public void getAllRegisteredForEvent() {
        eventRepo.registerForEvent(1L, 2L);
        eventRepo.registerForEvent(2L, 2L);
        eventRepo.registerForEvent(4L, 2L);
        eventRepo.registerForEvent(5L, 2L);

        System.out.println("\n\ngetRegisteredUsersForEvent");
        Set<Person> registeredUsers = eventRepo.getRegisteredUsersForEvent(2L);

        Assert.assertEquals(4, registeredUsers.size());

        for (Person user : registeredUsers) {
            user.getAddresses();
            user.getEmailAddresses();
            user.getName();
        }
    }

    @Test
    public void getAllRegisteredForEventDetached() {
        eventRepo.registerForEvent(1L, 2L);
        eventRepo.registerForEvent(2L, 2L);
        eventRepo.registerForEvent(4L, 2L);
        eventRepo.registerForEvent(5L, 2L);
        em.flush();
        em.clear();

        
        System.out.println("\n\ngetRegisteredUsersForEvent");
        Set<Person> registeredUsers = eventRepo.getRegisteredUsersForEvent(2L);

        //em.clear();
        Assert.assertEquals(4, registeredUsers.size());

        for (Person user : registeredUsers) {
            user.getAddresses();
            user.getEmailAddresses();
            user.getName();
            for(Address a:user.getAddresses()) {
            	System.out.println(a.getCity());
            }
        }
    }

    @Test
    public void addEmail() {
        eventRepo.addNewEmailForUser(1L, "kalle@kalle.com");
        Assert.assertEquals(1, jdbcTemplate
                .queryForInt("select count(*) from T_PERSON_EMAIL_ADDR"));
    }

    @Test
    public void viewTestData() throws Exception {
        System.out
                .println(jdbcTemplate.queryForList("select * from T_PERSONS"));
        System.out.println(jdbcTemplate.queryForList("select * from T_EVENTS"));
    }

}
