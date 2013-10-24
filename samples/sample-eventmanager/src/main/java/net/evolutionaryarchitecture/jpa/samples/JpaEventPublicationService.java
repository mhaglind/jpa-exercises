package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaEventPublicationService implements EventPublicationService {

    private static final int EVENT_FETCH_SIZE = 10;

    @PersistenceContext
    EntityManager entityManager;

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

        Event eventEntity = new Event(event, eventTimestamp);
        entityManager.persist(eventEntity);
        Set<Agent> items = new HashSet<Agent>();
        for (String itemKey : keysRelatedItems) {
            items.add((Agent) entityManager
    				.createQuery("from Agent where key = :key")
    				.setParameter("key", itemKey).getSingleResult());
        }
        eventEntity.agents = items;
        entityManager.flush();
        return eventEntity.getKey();
    }

}
