package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public class HibernateEventPublicationService implements EventPublicationService {

    private static final int EVENT_FETCH_SIZE = 10;

    @Autowired
    SessionFactory sessionFactory;

    // @SuppressWarnings("unchecked")
    // @Override
//    public List<Event> findEventsForItemBetween(String key, Date from, Date to) {
//        Session session = getSession();
//        Criteria criteria = session.createCriteria(Event.class).add(
//                Restrictions.between("eventTimestamp", from, to))
//                .createCriteria("items").add(Restrictions.eq("key", key));
//        criteria.setFetchSize(EVENT_FETCH_SIZE);
//        return criteria.list();
//    }

    @Override
    @Transactional
    public String registerEvent(String event, Date eventTimestamp, String... keysRelatedItems) {
        Session session = getSession();
        Event eventEntity = new Event(event, eventTimestamp);
        session.persist(eventEntity);
        Set<Agent> items = new HashSet<Agent>();
        for (String itemKey : keysRelatedItems) {
            items.add((Agent) session.createCriteria(Agent.class).add(Restrictions.eq("key", itemKey))
                    .uniqueResult());
        }
        eventEntity.agents = items;
        session.flush();
        return eventEntity.getKey();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
