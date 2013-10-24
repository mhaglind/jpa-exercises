package net.evolutionaryarchitecture.jpa.samples;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

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
public class EventPublicationServiceIntegrationTest {

    @Autowired
    EventPublicationService eventManager;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DataSource dataSource;

    @Test
    public void registerEvents() {

        Agent item1 = new Agent("item1");
        entityManager.persist(item1);
        Agent item2 = new Agent("item2");
        entityManager.persist(item2);
        Agent item3 = new Agent("item3");
        entityManager.persist(item3);
        Agent item4 = new Agent("item4");
        entityManager.persist(item4);

        eventManager.registerEvent("event1",
                Timestamp.valueOf("2010-01-01 10:00:00.0"), item1.getKey(),
                item2.getKey(), item3.getKey(), item4.getKey());

        eventManager.registerEvent("event2",
                Timestamp.valueOf("2010-01-02 10:00:00.0"), item1.getKey(),
                item4.getKey());

        eventManager.registerEvent("event3",
                Timestamp.valueOf("2010-01-05 10:00:00.0"), item2.getKey(),
                item4.getKey());

        // List<Event> eventsItem1 =
        // eventManager.findEventsForItemBetween(item1.getKey(), Timestamp
        // .valueOf("2010-01-01 00:00:00.0"),
        // Timestamp.valueOf("2010-01-10 00:00:00.0"));
        // assertEquals(2, eventsItem1.size());
        //
        // List<Event> eventsItem2 =
        // eventManager.findEventsForItemBetween(item2.getKey(), Timestamp
        // .valueOf("2010-01-01 00:00:00.0"),
        // Timestamp.valueOf("2010-01-10 00:00:00.0"));
        // assertEquals(2, eventsItem2.size());
        //
        // List<Event> eventsItem3 =
        // eventManager.findEventsForItemBetween(item3.getKey(), Timestamp
        // .valueOf("2010-01-01 00:00:00.0"),
        // Timestamp.valueOf("2010-01-10 00:00:00.0"));
        // assertEquals(1, eventsItem3.size());
        //
        // List<Event> eventsItem4 =
        // eventManager.findEventsForItemBetween(item4.getKey(), Timestamp
        // .valueOf("2010-01-01 00:00:00.0"),
        // Timestamp.valueOf("2010-01-10 00:00:00.0"));
        // assertEquals(3, eventsItem4.size());

    }

    @Test
    public void dumpItems() {
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Map<String, Object>> list = jt.queryForList("select * from Agent");
        System.out.println("#######  " + list.size() + " agents");
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }

    @Test
    public void dumpEvents() {
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Map<String, Object>> list = jt.queryForList("select * from Event");
        System.out.println("#######  " + list.size() + " events");
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }

    // @Test
    // public void dumpEventItems() {
    // JdbcTemplate jt = new JdbcTemplate(dataSource);
    // List<Map<String, Object>> list =
    // jt.queryForList("select * from Event_Item");
    // System.out.println("#######  " + list.size() + " event items");
    // for (Map<String, Object> map : list) {
    // System.out.println(map);
    // }
    // }
}
