package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Event {

  @Id
  private Long id;

  private String title;

  private Date date;

  @ManyToMany(mappedBy = "eventsRegisteredFor")
  private Set<Person> participants;

  protected Event() {
    participants = new HashSet<Person>();
  }

  public Event(String title, Date date) {
    this();
    setTitle(title);
    setDate(date);
  }

  public Long getId() {
    return id;
  }

  @SuppressWarnings("unused")
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

  public Set<Person> getParticipants() {
    return participants;
  }

  public void setParticipants(Set<Person> participants) {
    this.participants = participants;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((title == null) ? 0 : title.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Event other = (Event) obj;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }

}