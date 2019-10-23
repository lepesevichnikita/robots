package org.klaster.robots.models.abstracts;

import org.klaster.robots.models.notifications.NotificationAboutFailedAttemptToChangeState;
import org.klaster.robots.models.notifications.NotificationAboutStateChanging;
import org.klaster.robots.models.notifications.NotificationAboutAttemptToProccessUnsupportedAction;

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
    private Context context;

    @Column
    private boolean isCurrent;

    public State(Context context) {
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
        NotificationAboutStateChanging notificationAboutStateChanging = new NotificationAboutStateChanging();
        notificationAboutStateChanging.setPreviousState(getContext().getCurrentState());
        notificationAboutStateChanging.setNewState(newState);
        getContext().addNotification(notificationAboutStateChanging);
    }

    protected void notifyAboutFailedAttemptToChangeState(State newState) {
        NotificationAboutFailedAttemptToChangeState notificationAboutFailedAttemptToChangeState =
                new NotificationAboutFailedAttemptToChangeState();
        notificationAboutFailedAttemptToChangeState.setPreviousState(getContext().getCurrentState());
        notificationAboutFailedAttemptToChangeState.setNewState(newState);
        getContext().addNotification(notificationAboutFailedAttemptToChangeState);
    }

    protected void notifyAboutFailedAttemptToProcessUnsupportedAction(String action) {
        NotificationAboutAttemptToProccessUnsupportedAction notificationAboutAttemptToProccessUnsupportedAction =
                new NotificationAboutAttemptToProccessUnsupportedAction();
        notificationAboutAttemptToProccessUnsupportedAction.setAction(action);
        getContext().addNotification(notificationAboutAttemptToProccessUnsupportedAction);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
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
