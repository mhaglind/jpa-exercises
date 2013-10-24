package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "T_JOB")
public class Job {

    @Id
    @GeneratedValue
    Long id;

    @ElementCollection
    @CollectionTable(name = "T_STATE_CHANGES")
    Set<StateChange> stateChanges = new HashSet<StateChange>();

    public void changeState(StateChange.State newState) {
        stateChanges.add(new StateChange(newState, new Date()));
    }
}
