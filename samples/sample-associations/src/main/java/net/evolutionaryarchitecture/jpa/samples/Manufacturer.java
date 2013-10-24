package net.evolutionaryarchitecture.jpa.samples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="manufacturers")
    List<Product> productsManufactured = new ArrayList<Product>();
}
