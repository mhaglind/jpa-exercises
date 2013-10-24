package net.evolutionaryarchitecture.jpa.inheritance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_PUBLIC_COURSES")
public class PublicCourse extends Course {

    String classRoom;

    protected PublicCourse() {
        super();
    }

    public PublicCourse(String title, Date date, String instructor,
            String classRoom) {
        super(title, date, instructor);
        this.classRoom = classRoom;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PublicCourse [");
        if (classRoom != null)
            builder.append("classRoom=").append(classRoom).append(", ");
        if (getClassRoom() != null)
            builder.append("getClassRoom()=").append(getClassRoom())
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
