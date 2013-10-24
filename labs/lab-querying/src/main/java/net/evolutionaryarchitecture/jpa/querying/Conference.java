package net.evolutionaryarchitecture.jpa.querying;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_CONFERENCES")
public class Conference extends Event {

    String venue;

    protected Conference() {

    }

    public Conference(String title, Date date, String venue) {
        super(title, date);
        this.venue = venue;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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
