package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Cacheable
public class Conference extends Event {

  private String venue;

  @OneToMany
  private Set<Person> organizers;

  protected Conference() {
  }

  public Conference(String title, Date date, String venue) {
    super(title, date);
    setOrganizers(new HashSet<Person>());
    this.venue = venue;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public void addOrganizer(Person organizer) {
    getOrganizers().add(organizer);
  }

  private void setOrganizers(Set<Person> organizers) {
    this.organizers = organizers;
  }

  public Set<Person> getOrganizers() {
    return organizers;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Conference [");
    if (getVenue() != null)
      builder.append("getVenue()=").append(getVenue()).append(", ");
    if (getTitle() != null)
      builder.append("getTitle()=").append(getTitle()).append(", ");
    if (getDate() != null)
      builder.append("getDate()=").append(getDate());
    builder.append("]");
    return builder.toString();
  }

}
