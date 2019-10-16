package org.klaster.robots.builders;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Component("robotWithDefaultCurrentTaskBuilder")
public class RobotsWithDefaultCurrentTaskBuilder implements RobotBuilder {

    private Task         currentTask;
    private Robot.Status status;
    private Set<Task>    tasks;

    public RobotsWithDefaultCurrentTaskBuilder() { reset(); }

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
        robot.setCurrentTask(currentTask);
        robot.setStatus(status);
        reset();
        return robot;
    }

    @Override
    public RobotBuilder reset() {
        tasks       = new HashSet<>();
        currentTask = new Task();
        status      = Robot.Status.ALIVE;
        return this;
    }
}
