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
public class RobotsWithoutDefaultCurrentTaskBuilder implements RobotBuilder {
    private Task      currentTask;
    private Status    status;
    private Set<Task> tasks;

    public RobotsWithoutDefaultCurrentTaskBuilder() { reset(); }

    @Override
    public RobotBuilder withTasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    @Override
    public RobotBuilder withCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
        return this;
    }

    @Override
    public RobotBuilder withStatus(Robot.Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Robot getRobot() {
        Robot robot = new Robot();
        robot.setTasks(tasks);
        if (currentTask != null) {
            robot.setCurrentTask(currentTask);
        }
        reset();
        return robot;
    }

    @Override
    public RobotBuilder reset() {
        currentTask = null;
        tasks       = new HashSet<>();
        status      = Status.ALIVE;
        return this;
    }
}
