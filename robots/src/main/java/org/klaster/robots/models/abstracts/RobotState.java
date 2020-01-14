package org.klaster.robots.models.abstracts;

import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;

import javax.persistence.MappedSuperclass;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@MappedSuperclass
public abstract class RobotState extends State {
    public RobotState(Robot robot) {
        super(robot);
    }

    public RobotState() {
        super();
    }

    public abstract void startCurrentTask();

    public abstract void addTaskAndSetAsCurrentIfPossible(Task newCurrentTask);

    public Robot getRobot() {
        return (Robot) getContext();
    }

    public boolean hasRobot() {
        return getRobot() != null;
    }
}
