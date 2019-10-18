package org.klaster.robots.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class NotificationAboutStateChange extends Notification{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "old_state_id", referencedColumnName = "id")
    private State oldState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_state_id", referencedColumnName = "id")
    private State newState;

    @Override
    public String getMessage() {
        return "State changed from " + oldState.getName() + " to " + newState.getName();
    }

    public State getOldState() {
        return oldState;
    }

    public void setOldState(State oldState) {
        this.oldState = oldState;
    }

    public State getNewState() {
        return newState;
    }

    public void setNewState(State newState) {
        this.newState = newState;
    }
}
