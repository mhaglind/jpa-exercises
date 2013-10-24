package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@EntityListeners(PersonValidator.class)
public class Person {

  @Id
  private Long id;

  @NotNull(message = "Age must be specified")
  @Min(value = 0, message = "Age must be greater than zero")
  private int age;

  private Name name;

  @Temporal(TemporalType.TIMESTAMP)
  @Past
  private Date createdWhen;

  @ElementCollection
  private Set<Address> addresses;

  @ElementCollection
  @CollectionTable(name = "T_PERSON_EMAIL_ADDR")
  private Set<String> emailAddresses;

  @ManyToMany
  @JoinTable(name = "T_EVENT_REGISTRATIONS")
  private Set<Event> eventsRegisteredFor;

  protected Person() {
    setCreatedWhen(new Date(System.currentTimeMillis() - 50));
    addresses = new HashSet<Address>();
    emailAddresses = new HashSet<String>();
    eventsRegisteredFor = new HashSet<Event>();
  }

  protected Person(Name name) {
    this();
    setName(name);
  }

  public void addRegistrationToEvent(Event event) {
    this.getEventsRegisteredFor().add(event);
    event.getParticipants().add(this);
  }

  public void removeRegistrationFromEvent(Event event) {
    this.getEventsRegisteredFor().remove(event);
    event.getParticipants().remove(this);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  private void setCreatedWhen(Date createdWhen) {
    this.createdWhen = createdWhen;
  }

  public Date getCreatedWhen() {
    return createdWhen;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }

  public Set<String> getEmailAddresses() {
    return emailAddresses;
  }

  public void setEmailAddresses(Set<String> emailAddresses) {
    this.emailAddresses = emailAddresses;
  }

  public Set<Event> getEventsRegisteredFor() {
    return eventsRegisteredFor;
  }

  @SuppressWarnings("unused")
  private void setEventsRegisteredFor(Set<Event> eventsRegisteredFor) {
    this.eventsRegisteredFor = eventsRegisteredFor;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    Person other = (Person) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Person [id=").append(id).append(", age=")
        .append(age).append(", name=").append(name)
        .append(", addresses=").append(addresses)
        .append(", emailAddresses=").append(emailAddresses)
        .append("]");
    return builder.toString();
  }

}