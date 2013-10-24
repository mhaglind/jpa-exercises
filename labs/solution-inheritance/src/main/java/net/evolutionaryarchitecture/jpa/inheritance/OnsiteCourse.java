package net.evolutionaryarchitecture.jpa.inheritance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ONSITE_COURSES")
public class OnsiteCourse extends Course {

    @ManyToOne
    Person contact;

    String companyName;

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

    public Person getContact() {
        return contact;
    }

    public void setContact(Person contact) {
        this.contact = contact;
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
