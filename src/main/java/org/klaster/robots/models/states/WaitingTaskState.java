package org.klaster.robots.models.states;

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
public class WaitingTaskState extends TaskState {

    public WaitingTaskState() { super(); }

    public WaitingTaskState(Task task) { super(task); }

    @Override
    public String getName() {
        return "WAITING";
    }

    @Override
    public void start() {
        notifyAboutFailedAttemptToProcessUnsupportedAction(Action.START.getName());
    }

    @Override
    public void changeRobot(Robot newRobot) {
        getTask().setCurrent(false);
        getTask().setRobot(newRobot);
    }
}
