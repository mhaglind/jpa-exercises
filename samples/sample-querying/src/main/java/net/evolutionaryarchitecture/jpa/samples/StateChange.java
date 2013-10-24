package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;

import javax.persistence.Column;
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
    @Column(name = "CHANGED_WHEN")
    Date changedWhen;

    public enum State {
        STARTED, ONGOING, DONE
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "NEW_STATE")
    State newState;

    protected StateChange() {
    }

    public StateChange(State newState, Date when) {
        this.newState = newState;
        this.changedWhen = when;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (newState != null)
            builder.append(newState).append(" ");
        if (changedWhen != null)
            builder.append(changedWhen);
        builder.append("]");
        return builder.toString();
    }

}
