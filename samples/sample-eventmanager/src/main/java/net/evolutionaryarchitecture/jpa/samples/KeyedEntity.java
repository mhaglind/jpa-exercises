package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class KeyedEntity {

    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false, unique = true)
    String key;

    public KeyedEntity() {
        key = KeyUtil.generateGuid(4, 5);
    }
    
    public String getKey() {
        return key;
    }

    public long getId() {
    	return id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyedEntity other = (KeyedEntity) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }        
    
}
