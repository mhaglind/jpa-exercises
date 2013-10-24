package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Course extends Event {

  private String instructor;

  protected Course() {
    super();
  }

  public Course(String title, Date date, String instructor) {
    super(title, date);
    setInstructor(instructor);
  }

  public String getInstructor() {
    return instructor;
  }

  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Course [");
    if (getInstructor() != null)
      builder.append("getInstructor()=").append(getInstructor())
          .append(", ");
    if (getTitle() != null)
      builder.append("getTitle()=").append(getTitle()).append(", ");
    if (getDate() != null)
      builder.append("getDate()=").append(getDate());
    builder.append("]");
    return builder.toString();
  }

}
