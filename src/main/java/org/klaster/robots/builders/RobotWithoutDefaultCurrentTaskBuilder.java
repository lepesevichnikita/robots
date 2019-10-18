package org.klaster.robots.builders;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Robot.Status;
import org.klaster.robots.models.Task;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public class RobotWithoutDefaultCurrentTaskBuilder implements RobotBuilder {

    private Set<Task> tasks;
    private Status status;
    private Task currentTask;

    public RobotWithoutDefaultCurrentTaskBuilder() { reset(); }

    @Override
    public RobotBuilder setTasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    @Override
    public RobotBuilder setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
        return this;
    }

    @Override
    public RobotBuilder setStatus(Robot.Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Robot getRobot() {
        Robot robot = new Robot();
        setFields(robot);
        reset();
        return robot;
    }

    @Override
    public RobotBuilder reset() {
        currentTask = null;
        tasks = new HashSet<>();
        status = Status.ALIVE;
        return this;
    }

    private void setFields(Robot robot) {
        robot.setTasks(tasks);
        setCurrentTaskToRobotIfHasIt(robot);
        robot.setStatus(status);
    }

    private void setCurrentTaskToRobotIfHasIt(Robot robot) {
        if (currentTask != null) {
            robot.setCurrentTask(currentTask);
        }
    }
}
