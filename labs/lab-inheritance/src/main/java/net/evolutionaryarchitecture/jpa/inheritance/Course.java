package net.evolutionaryarchitecture.jpa.inheritance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_COURSES")
public class Course extends Event {

    String instructor;

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
