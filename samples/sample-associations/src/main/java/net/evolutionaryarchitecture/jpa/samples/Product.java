package net.evolutionaryarchitecture.jpa.samples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {

    @Id
    @GeneratedValue
    Long id;
    
    String productKey;
    
    @ManyToMany(cascade=CascadeType.ALL)
    List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
}
