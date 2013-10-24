package net.evolutionaryarchitecture.jpa.samples.domain;

import java.util.*;

public class Event {
    
	private Long id;
    private String title;
    private Date date;
    private String notes;
    private Country country;
    private Set<Person> participants;
    private Set<String> subjects;
    
    public Event() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Person> getParticipants() {
        return participants;
    }
	
    public Set<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<String> subjects) {
		this.subjects = subjects;
	}

	public void setParticipants(Set<Person> participants) {
        this.participants = participants;
    }

	public String toString() {
		return "Event " + getTitle();
	}
}