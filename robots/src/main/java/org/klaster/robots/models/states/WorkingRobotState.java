package org.klaster.robots.models.states;

import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class WorkingRobotState extends RobotState {

    public WorkingRobotState() { super(); }

    public WorkingRobotState(Robot robot) {
        super(robot);
    }

    @Override
    public String getName() {
        return "WORKING";
    }

    @Override
    public void startCurrentTask() {
        getRobot().getCurrentTask().changeCurrentState(new ProcessingTaskState());
        getRobot().getCurrentTask().start();
    }

    @Override
    public void addTaskAndSetAsCurrentIfPossible(Task newCurrentTask) {
        newCurrentTask.changeRobot(getRobot());
        getRobot().addTask(newCurrentTask);
    }
}
