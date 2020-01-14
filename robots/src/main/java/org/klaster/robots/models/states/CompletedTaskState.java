package org.klaster.robots.models.states;

import org.klaster.robots.models.abstracts.State;
import org.klaster.robots.models.abstracts.TaskState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.Action;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class CompletedTaskState extends TaskState {

    public CompletedTaskState() {super();}

    public CompletedTaskState(Task task) {
        super(task);
    }

    @Override
    public void start() {
        notifyAboutFailedAttemptToProcessUnsupportedAction(Action.START.getName());
    }

    @Override
    public void changeRobot(Robot newRobot) {
        notifyAboutFailedAttemptToProcessUnsupportedAction(Action.CHANGE_ROBOT.getName());
    }

    @Override
    public void changeCurrentState(State newState) {
        if (!getContext().isCurrentState(newState.getClass())) {
            notifyAboutFailedAttemptToChangeState(newState);
        }
    }

    @Override
    public String getName() {
        return "COMPLETED";
    }
}
