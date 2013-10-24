package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_FACTORIES")
public class Factory extends IdentifiableEntity {

    @Basic(optional = false)
    @Column(name = "NAME", unique = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUSINESS_UNIT_ID")
    BusinessUnit unit;

    public Factory() {
        super();
    }

    public Factory(String name, BusinessUnit unit) {
        this.name = name;
        this.unit = unit;
    }
}
