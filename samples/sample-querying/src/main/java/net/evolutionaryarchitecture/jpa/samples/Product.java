package net.evolutionaryarchitecture.jpa.samples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PRODUCTS")
public class Product extends IdentifiableEntity {

    @Basic(optional = false)
    @Column(name = "PRODUCT_KEY", unique = false)
    String productKey;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_MANUFACTURED_PRODUCTS", 
            joinColumns = { @JoinColumn(name = "PRODUCT_ID") }, 
            inverseJoinColumns = { @JoinColumn(name = "MANUFACTURER_ID") })
    List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product [");
        if (getId() != null)
            builder.append("id=").append(getId()).append(", ");
        if (productKey != null)
            builder.append("productKey=").append(productKey);
        builder.append("]").append("\n");
        return builder.toString();
    }
        
}
