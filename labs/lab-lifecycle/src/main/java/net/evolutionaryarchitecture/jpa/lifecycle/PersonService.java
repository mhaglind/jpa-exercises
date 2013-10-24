package net.evolutionaryarchitecture.jpa.lifecycle;

public class PersonService {

	public void updatePerson(Person person) {
		// TODO: Store the updated person in the database.
		// Use JpaUtil.getEntityManagerFactory().createEntityManager()
		// to get an entity manager.
	}

	public void deletePerson(long id) {
		// TODO: Find the person and delete the row.
	}

	public Long addPerson(String firstName, String lastName, String description) {
		// TODO: Create a new person row
		return null;
	}

	public Person findPerson(long id) {
		// TODO: Retrieve a person (or throw exception if it doesnt exist)
		return null;
	}

}
