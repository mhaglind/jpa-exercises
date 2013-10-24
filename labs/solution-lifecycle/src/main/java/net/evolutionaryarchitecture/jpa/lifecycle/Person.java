package net.evolutionaryarchitecture.jpa.lifecycle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 2103556520091552641L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Column(name = "first_name", nullable = false, length = 50)
	String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	String lastName;

	String description;

	@Version
	@Column(name = "optlockVersion")
	Integer version;

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
