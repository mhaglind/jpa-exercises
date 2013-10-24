package net.evolutionaryarchitecture.jpa.samples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_MANUFACTURERS")
public class Manufacturer extends IdentifiableEntity {

    @Basic(optional = false)
    @Column(name = "NAME", unique = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTORY_ID")
    Factory productionLocation;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "manufacturers")
    List<Product> productsManufactured = new ArrayList<Product>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Manufacturer [");
        if (getId() != null)
            builder.append("id=").append(getId()).append(", ");
        if (name != null)
            builder.append("name=").append(name);
        builder.append("]");
        return builder.toString();
    }

}
