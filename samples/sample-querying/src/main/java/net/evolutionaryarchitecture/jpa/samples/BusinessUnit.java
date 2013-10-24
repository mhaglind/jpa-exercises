package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_BUSINESS_UNITS")
public class BusinessUnit extends IdentifiableEntity {

    @Basic(optional = false)
    @Column(name = "NAME", unique = true)
    String name;

    protected BusinessUnit() {
        super();
    }

    public BusinessUnit(String name) {
        this.name = name;
    }
}
