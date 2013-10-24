package net.evolutionaryarchitecture.jpa.inheritance;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import junit.framework.Assert;

import net.evolutionaryarchitecture.jpa.inheritance.Conference;
import net.evolutionaryarchitecture.jpa.inheritance.Event;
import net.evolutionaryarchitecture.jpa.inheritance.EventRepository;
import net.evolutionaryarchitecture.jpa.inheritance.OnsiteCourse;
import net.evolutionaryarchitecture.jpa.inheritance.Person;
import net.evolutionaryarchitecture.jpa.inheritance.PublicCourse;

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
    
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void registerForEvent() {

        eventRepo.registerNewEvent(new Conference("JavaZone", Date.valueOf("2009-08-21"), "Stockholm Globe Arena"));
        eventRepo.registerNewEvent(new OnsiteCourse("Effective JPA", Date.valueOf("2011-02-25"),
                "Marten Haglind", "Oracle"));
        eventRepo.registerNewEvent(new PublicCourse("Core Spring", Date.valueOf("2011-04-25"), "Christian Bale",
                "Geita, Programutvikling"));

        Assert.assertEquals(3, jdbcTemplate.queryForInt("select count(*) from T_EVENTS"));
        Assert.assertEquals(2, jdbcTemplate.queryForInt("select count(*) from T_COURSES"));
        Assert.assertEquals(1, jdbcTemplate.queryForInt("select count(*) from T_PUBLIC_COURSES"));
        Assert.assertEquals(1, jdbcTemplate.queryForInt("select count(*) from T_ONSITE_COURSES"));

    }

    @Test
    public void listRegisteredEventsForUser() {

        // Add some events
        Long eventId1 = eventRepo.registerNewEvent(new Conference("JavaZone", Date.valueOf("2009-08-21"),
                "Stockholm Globe Arena"));
        Long eventId2 = eventRepo.registerNewEvent(new OnsiteCourse("Effective JPA", Date.valueOf("2011-02-25"),
                "Marten Haglind", "Oracle"));
        Long eventId3 = eventRepo.registerNewEvent(new PublicCourse("Core Spring", Date.valueOf("2011-04-25"),
                "Christian Bale", "Geita, Programutvikling"));

        // Add some users
        Person user1 = eventRepo.registerNewUser("Kalle", "Karlsson");
        Person user2 = eventRepo.registerNewUser("Eva", "Larsson");

        // Register users for some events
        eventRepo.registerForEvent(user1.getId(), eventId1);
        eventRepo.registerForEvent(user1.getId(), eventId3);
        eventRepo.registerForEvent(user2.getId(), eventId3);
        eventRepo.registerForEvent(user2.getId(), eventId2);

        // Retrieve registered events per user and
        // loop through and print events per user to console
        System.out.println("Events for " + user1.getName());
        for (Event event : eventRepo.getRegisteredEventsForUser(user1.getId())) {
            System.out.println(event);
        }
        
        System.out.println("Events for " + user2.getName());
        for (Event event : eventRepo.getRegisteredEventsForUser(user1.getId())) {
            System.out.println(event);
        }
    }

}
