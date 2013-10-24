package net.evolutionaryarchitecture.jpa.samples.service;

import java.sql.SQLException;
import java.util.Collection;

import net.evolutionaryarchitecture.jpa.samples.domain.Country;
import net.evolutionaryarchitecture.jpa.samples.domain.Course;
import net.evolutionaryarchitecture.jpa.samples.domain.Event;
import net.evolutionaryarchitecture.jpa.samples.domain.Person;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class EventManagerHibernateImpl implements EventManager {

	HibernateTemplate ht;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.ht = new HibernateTemplate(sessionFactory);
	}

	public void update(Event event) {
		ht.update(event);
	}

	public void save(Event event) {
		ht.save(event);
	}

	public void save(Country country) {
		ht.save(country);
	}

	public Collection<Country> findAllCountries() {
		return ht.find("from Country");
	}

	public Country findCountry(String code) {
		return (Country) ht.get(Country.class, code);
	}

	public Event findEvent(Long id) {
		return (Event) ht.get(Event.class, id);
	}

	public Event findEventWithParticipants(final Long eventId) {
		return (Event) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Event anEvent = (Event) session.createCriteria(Event.class)
						.setFetchMode("participants", FetchMode.JOIN).add(
								Expression.eq("id", eventId)).uniqueResult();
				return anEvent;
			}
		});		
	}

	public Collection<Event> findAllEvents() {
		return ht.find("from Event");
	}

	public Collection<Person> findAllPersons() {
		return ht.find("from Person");
	}

	public Collection<Course> findCoursesForPerson(long id) {		
		return ht.find("select c from Course c join c.participants p where p.id =" + id);
	}

	public Person findPerson(long id) {
		return (Person) ht.get(Person.class, id);
	}
}
