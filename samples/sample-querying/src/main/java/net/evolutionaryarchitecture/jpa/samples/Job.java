package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_JOBS")
public class Job extends IdentifiableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    Product productToManufacture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANUFACTURER_ID")
    Manufacturer whereToManufacture;

    @Basic(optional = false)
    @Column(name = "AMOUNT")
    int amountToProduce;

    @ElementCollection
    @CollectionTable(name = "T_STATE_CHANGES", joinColumns = { @JoinColumn(name = "JOB_ID") })
    Set<StateChange> stateChanges = new HashSet<StateChange>();

    public void changeState(StateChange.State newState) {
        stateChanges.add(new StateChange(newState, new Date()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Job [");
        if (getId() != null)
            builder.append("id=").append(getId()).append(", ");
        if (productToManufacture != null)
            builder.append("\n\t").append("productToManufacture=").append(productToManufacture).append(", ");
        if (whereToManufacture != null)
            builder.append("\n\t").append("whereToManufacture=").append(whereToManufacture).append(", ");
        builder.append("\n\t").append("amountToProduce=").append(amountToProduce).append(", ");
        if (stateChanges != null)
            builder.append("\n\t").append("stateChanges=").append(stateChanges);
        builder.append("\n").append("]");
        return builder.toString();
    }

}
