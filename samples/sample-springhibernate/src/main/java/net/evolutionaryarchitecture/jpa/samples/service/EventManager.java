package net.evolutionaryarchitecture.jpa.samples.service;

import java.util.Collection;

import net.evolutionaryarchitecture.jpa.samples.domain.Country;
import net.evolutionaryarchitecture.jpa.samples.domain.Course;
import net.evolutionaryarchitecture.jpa.samples.domain.Event;
import net.evolutionaryarchitecture.jpa.samples.domain.Person;


public interface EventManager {

	Collection<Event> findAllEvents();

	Event findEvent(Long id);
	
	/**
	 * Eager-load participants
	 */
	Event findEventWithParticipants(Long id);
	
	void update(Event event);

	void save(Event event);

	Collection<Country> findAllCountries();

	Country findCountry(String code);

	void save(Country country);

	Collection<Person> findAllPersons();

	Person findPerson(long id);

	Collection<Course> findCoursesForPerson(long id);

	

}
