package net.evolutionaryarchitecture.jpa.lifecycle;

import javax.persistence.EntityManager;

public class PersonService {

	public void updatePerson(Person person) {
		EntityManager em = JpaUtil.getEntityManagerFactory()
				.createEntityManager();
		em.getTransaction().begin();

		em.merge(person);

		em.getTransaction().commit();
		em.close();
	}

	public void deletePerson(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory()
				.createEntityManager();
		em.getTransaction().begin();
		Person person = (Person) em.find(Person.class, id);
		em.remove(person);
		em.getTransaction().commit();
		em.close();
	}

	public Long addPerson(String firstName, String lastName, String description) {
		EntityManager em = JpaUtil.getEntityManagerFactory()
				.createEntityManager();
		em.getTransaction().begin();
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setDescription(description);
		em.persist(person);
		em.getTransaction().commit();
		em.close();
		return person.getId();
	}

	public Person findPerson(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory()
				.createEntityManager();

		Person person = (Person) em.find(Person.class, id);

		em.close();

		if (person == null) {
			throw new RuntimeException("No such person " + id);
		}

		return person;
	}

}
