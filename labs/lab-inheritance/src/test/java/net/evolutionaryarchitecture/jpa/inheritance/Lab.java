package net.evolutionaryarchitecture.jpa.inheritance;

import javax.sql.DataSource;

import junit.framework.Assert;

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

        // TODO
        // Register one conference, one onsite course and one public course
        // Hint: Something like:
        // eventRepo.registerNewEvent(new Conference("JavaZone", Date
        // .valueOf("2009-08-21"), "Stockholm Globe Arena"));

        Assert.assertEquals(3,
                jdbcTemplate.queryForInt("select count(*) from T_EVENTS"));
        Assert.assertEquals(2,
                jdbcTemplate.queryForInt("select count(*) from T_COURSES"));
        Assert.assertEquals(1, jdbcTemplate
                .queryForInt("select count(*) from T_PUBLIC_COURSES"));
        Assert.assertEquals(1, jdbcTemplate
                .queryForInt("select count(*) from T_ONSITE_COURSES"));
        Assert.assertEquals(1,
                jdbcTemplate.queryForInt("select count(*) from T_CONFERENCES"));

    }

    @Test
    public void listRegisteredEventsForUser() {

        // TODO:
        // Add some events (like in the test case above)

        // TODO:
        // Add some users
        // Hint:
        // Person user1 = eventRepo.registerNewUser("Kalle", "Karlsson");

        // TODO:
        // Register users for some events
        // Hint:
        // eventRepo.registerForEvent(user1.getId(), eventId1);

        // TODO:
        // Retrieve registered events per user and
        // loop through and print events per user to console
        // Hint:
        // System.out.println("Events for " + user1.getName());
        // for (Event event:
        // eventRepo.getRegisteredEventsForUser(user1.getId())){
        // System.out.println(event);
        // }

    }

}
