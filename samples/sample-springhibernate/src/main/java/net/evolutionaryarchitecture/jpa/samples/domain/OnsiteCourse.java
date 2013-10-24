package net.evolutionaryarchitecture.jpa.samples.domain;

public class OnsiteCourse extends Course {

	Person contact;

	String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Person getContact() {
		return contact;
	}

	public void setContact(Person contact) {
		this.contact = contact;
	}

	public String toString() {
		return "Onsite Course at " + getCompanyName() + ": " + getTitle();
	}
}
