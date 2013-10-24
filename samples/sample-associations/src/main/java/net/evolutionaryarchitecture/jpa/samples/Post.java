package net.evolutionaryarchitecture.jpa.samples;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue
    Long id;
    
    String postText;
    
    @ElementCollection
    Set<String> userComments = new HashSet<String>();
    
}
