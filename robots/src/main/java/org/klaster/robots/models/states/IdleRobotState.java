package org.klaster.robots.models.states;

import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.Action;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class IdleRobotState extends RobotState {

    public IdleRobotState() { super(); }

    public IdleRobotState(Robot robot) {
        super(robot);
    }

    @Override
    public String getName() {
        return "IDLE";
    }

    @Override
    public void startCurrentTask() {
        notifyAboutFailedAttemptToProcessUnsupportedAction(Action.START_CURRENT_TASK.getName());
    }

    @Override
    public void addTaskAndSetAsCurrentIfPossible(Task newCurrentTask) {
        newCurrentTask.changeRobot(getRobot());
        getRobot().addTask(newCurrentTask);

        newCurrentTask.setCurrent(true);
        newCurrentTask.changeCurrentState(new ProcessingTaskState());
        changeCurrentState(new WorkingRobotState());
    }
}
