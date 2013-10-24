package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OnsiteCourse extends Course {

  @ManyToOne
  private Person technicalContact;

  @ManyToOne
  private Person billingContact;

  @Column(name = "COMPANY_NAME")
  private String companyName;

  protected OnsiteCourse() {
    super();
  }

  public OnsiteCourse(String title, Date date, String instructor,
      String companyName) {
    super(title, date, instructor);
    setCompanyName(companyName);
    this.companyName = companyName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Person getTechnicalContact() {
    return technicalContact;
  }

  public void setTechnicalContact(Person contact) {
    this.technicalContact = contact;
  }

  public void setBillingContact(Person billingContact) {
    this.billingContact = billingContact;
  }

  public Person getBillingContact() {
    return billingContact;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OnsiteCourse [");
    if (getCompanyName() != null)
      builder.append("getCompanyName()=").append(getCompanyName())
          .append(", ");
    if (getTitle() != null)
      builder.append("getTitle()=").append(getTitle()).append(", ");
    if (getDate() != null)
      builder.append("getDate()=").append(getDate()).append(", ");
    if (getInstructor() != null)
      builder.append("getInstructor()=").append(getInstructor());
    builder.append("]");
    return builder.toString();
  }

}
