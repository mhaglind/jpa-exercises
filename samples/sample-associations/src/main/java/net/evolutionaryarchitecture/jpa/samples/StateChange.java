package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class StateChange {

    @Temporal(TemporalType.TIMESTAMP)
    @OrderBy
    Date changedWhen;

    public enum State {
        CREATED, ONGOING, DONE, ARCHIVED
    }

    @Enumerated(EnumType.STRING)
    State newState;

    protected StateChange() {    
    }
    
    public StateChange(State newState, Date when) {
        this.newState = newState;
        this.changedWhen = when;
    }
}
