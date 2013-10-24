package net.evolutionaryarchitecture.jpa.samples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_ORDER")
public class Order {

    @Id
    String orderNr;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    List<OrderItem> items = new ArrayList<OrderItem>();

    public void addOrderItem(String productCode, int quantity) {
        items.add(new OrderItem(productCode, quantity));
    }
}
