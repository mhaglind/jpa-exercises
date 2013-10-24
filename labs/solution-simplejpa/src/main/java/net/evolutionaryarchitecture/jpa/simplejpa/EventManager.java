package net.evolutionaryarchitecture.jpa.simplejpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

public class EventManager {

	public static void main(String[] args) {

		JpaUtil.getEntityManagerFactory().createEntityManager();

		EventManager mgr = new EventManager();

		if (args[0].equals("store")) {

			System.out.println("Persisted object with id: "
					+ mgr.createAndStoreEvent("My Event " + Math.random(),
							new Date()));

		} else if (args[0].equals("list")) {

			@SuppressWarnings("rawtypes")
			List events = mgr.listEvents();
			for (int i = 0; i < events.size(); i++) {
				Event theEvent = (Event) events.get(i);
				System.out.println("Event: " + theEvent.getTitle() + " Time: "
						+ theEvent.getDate());
			}

		} else if (args[0].equals("clear")) {
			mgr.deleteAllEvents();
			System.out.println("Deleted all events");
		}

		JpaUtil.getEntityManagerFactory().close();

	}

	private Long createAndStoreEvent(String title, Date theDate) {

		EntityManager em = createEntityManager();
		em.getTransaction().begin();

		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);

		em.persist(theEvent);

		em.getTransaction().commit();
		em.close();

		return theEvent.getId();
	}

	@SuppressWarnings("rawtypes")
	private List listEvents() {

		EntityManager em = createEntityManager();
		em.getTransaction().begin();

		List result = em.createQuery("from Event").getResultList();

		em.getTransaction().commit();
		em.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	private void deleteAllEvents() {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();

		for (Event event : (List<Event>) em.createQuery("from Event")
				.getResultList()) {
			em.remove(event);
		}

		em.getTransaction().commit();
		em.close();

	}

	/**
	 * Convenience method to create a JPA Entity Manager.
	 * Uses settings in META-INF/persistence.xml
	 */
	private EntityManager createEntityManager() {
		return JpaUtil.getEntityManagerFactory().createEntityManager();
	}

}