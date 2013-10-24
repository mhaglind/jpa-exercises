package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue
    long id;

    String productCode;
    int quantity;

    protected OrderItem() {
        super();
    }

    public OrderItem(String productCode, int quantity) {
        this();
        this.productCode = productCode;
        this.quantity = quantity;
    }
}
