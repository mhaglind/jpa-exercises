package net.evolutionaryarchitecture.jpa.simplejpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

public class EventManager {

	public static void main(String[] args) {
		EventManager mgr = new EventManager();

		if (args[0].equals("store")) {

			Long id = mgr.createAndStoreEvent("My Event " + Math.random(),
					new Date());
			System.out.println("Persisted object with id: " + id);

		} else if (args[0].equals("list")) {

			List<Event> events = mgr.listEvents();

			// TODO: Print out you events. Comment out below code or write
			// your own:
			// for (Event theEvent: events) {
			// System.out.println("Event: " + theEvent.getTitle() +
			// " Time: "
			// + theEvent.getDate());
			// }

		} else if (args[0].equals("clear")) {
			mgr.deleteAllEvents();
			System.out.println("Deleted all events");
		}

		JpaUtil.getEntityManagerFactory().close();
	}

	private Long createAndStoreEvent(String title, Date theDate) {

		// TODO: Use JPA to save an Event object to the database
		// Return the key of the entity created. You can call the convenience
		// method createEntityManager below to get an entity manager.

		return null;
	}

	private List<Event> listEvents() {

		// TODO: Use JPA to retrieve a list of all events
		// Return the list

		return null;
	}

	private void deleteAllEvents() {

		// TODO: Use JPA to retrieve a list of all events,
		// then call em.remove(xxx) on each object.
		
	}

	/**
	 * Convenience method to create a JPA Entity Manager.
	 * Uses settings in META-INF/persistence.xml
	 */
	private EntityManager createEntityManager() {
		return JpaUtil.getEntityManagerFactory().createEntityManager();
	}

}