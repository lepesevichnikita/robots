package org.klaster.robots.models.abstracts;

import org.klaster.robots.models.notifications.FailedActionNotification;
import org.klaster.robots.models.notifications.FailedAttemptToChangeState;
import org.klaster.robots.models.notifications.StateChangedNotification;

import javax.persistence.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "context_id", referencedColumnName = "id")
    private SubscribableContext context;

    @Column
    private boolean isCurrent;

    public State(SubscribableContext context) {
        setContext(context);
    }

    public State() {
    }

    public void changeCurrentState(State newState) {
        if (!getContext().isCurrentState(newState.getClass())) {
            getContext().addState(newState);
            newState.setContext(getContext());
            notifyAboutStateChange(newState);
            setCurrent(false);
            newState.setCurrent(true);
        }
    }

    private void notifyAboutStateChange(State newState) {
        StateChangedNotification stateChangedNotification = new StateChangedNotification();
        stateChangedNotification.setPreviousState(getContext().getCurrentState());
        stateChangedNotification.setNewState(newState);
        getContext().addNotification(stateChangedNotification);
    }

    protected void notifyAboutFailedAttemptToChangeState(State newState) {
        FailedAttemptToChangeState failedAttemptToChangeState =
                new FailedAttemptToChangeState();
        failedAttemptToChangeState.setPreviousState(getContext().getCurrentState());
        failedAttemptToChangeState.setNewState(newState);
        getContext().addNotification(failedAttemptToChangeState);
    }

    protected void notifyAboutFailedAttemptToProcessUnsupportedAction(String action) {
        FailedActionNotification failedActionNotification =
                new FailedActionNotification();
        failedActionNotification.setAction(action);
        getContext().addNotification(failedActionNotification);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubscribableContext getContext() {
        return context;
    }

    public void setContext(SubscribableContext context) {
        this.context = context;
    }

    public boolean hasContext() {
        return context != null;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public abstract String getName();
}
