package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IdentifiableEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    protected void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
