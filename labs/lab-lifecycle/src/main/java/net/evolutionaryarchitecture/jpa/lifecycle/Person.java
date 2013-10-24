package net.evolutionaryarchitecture.jpa.lifecycle;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 2103556520091552641L;

	long id;

	String firstName;

	String lastName;

	String description;

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
