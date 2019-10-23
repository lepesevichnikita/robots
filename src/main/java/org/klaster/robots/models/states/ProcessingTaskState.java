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
public class ProcessingTaskState extends TaskState {

    public ProcessingTaskState() { super(); }

    @Override
    public String getName() {
        return "PROCESSING";
    }

    public ProcessingTaskState(Task task) {
        super(task);
    }

    @Override
    public void start() {
        boolean executed = getTask().execute();
        if (executed) {
            getTask().setCurrent(false);
            changeCurrentState(new CompletedTaskState());
        }
    }

    @Override
    public void changeRobot(Robot newRobot) {
        notifyAboutFailedAttemptToProcessUnsupportedAction(Action.CHANGE_ROBOT.getName());
    }
}
