package org.klaster.robots.models.states;

import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.abstracts.State;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.Action;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class DeadRobotState extends RobotState {

    public DeadRobotState() {super();}

    public DeadRobotState(Robot robot) {
        super(robot);
    }

    @Override
    public void changeCurrentState(State newState) {
        if (!getContext().isCurrentState(newState.getClass())) {
            notifyAboutFailedAttemptToChangeState(newState);
        }
    }

    @Override
    public String getName() {
        return "DEAD";
    }

    @Override
    public void startCurrentTask() {
        notifyAboutFailedAttemptToProcessUnsupportedAction(Action.START_CURRENT_TASK.getName());
    }

    @Override
    public void addTaskAndSetAsCurrentIfPossible(Task newCurrentTask) {
        notifyAboutFailedAttemptToProcessUnsupportedAction(Action.ADD_TASK_TO_ROBOT.getName());
    }
}
