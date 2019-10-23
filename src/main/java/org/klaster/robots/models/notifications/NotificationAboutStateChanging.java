package org.klaster.robots.models.notifications;

import org.klaster.robots.models.abstracts.Notification;
import org.klaster.robots.models.abstracts.State;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/20/19
 * @project robots
 */
@Entity
public class NotificationAboutStateChanging extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_state_id", referencedColumnName = "id")
    private State previousState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_state_id", referencedColumnName = "id")
    private State newState;

    @Override
    public String getMessage() {
        return "State changed from " + previousState.getName() + " to " + newState.getName();
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public State getNewState() {
        return newState;
    }

    public void setNewState(State newState) {
        this.newState = newState;
    }
}
