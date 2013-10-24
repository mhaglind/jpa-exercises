package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Officer {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String note;

    int rank;

    @ManyToOne(cascade=CascadeType.PERSIST)
    Officer superiorOfficer;

    protected Officer() {
        super();
    }

    public Officer(String name, int rank, String note) {
        this();
        setName(name);
        setRank(rank);
        setNote(note);
    }

    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Officer getSuperiorOfficer() {
        return superiorOfficer;
    }

    public void setSuperiorOfficer(Officer superiorOfficer) {
        this.superiorOfficer = superiorOfficer;
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
        Officer other = (Officer) obj;
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
        builder.append("Officer [");
        if (id != null)
            builder.append("id=").append(id).append(", ");
        if (name != null)
            builder.append("name=").append(name).append(", ");
        if (note != null)
            builder.append("note=").append(note).append(", ");
        builder.append("rank=").append(rank).append(", ");
        if (superiorOfficer != null)
            builder.append("superiorOfficer=").append(superiorOfficer);
        builder.append("]");
        return builder.toString();
    }

}
