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
public class RobotWithDefaultCurrentTaskBuilder implements RobotBuilder {

    private Task currentTask;
    private Robot.Status status;
    private Set<Task> tasks;

    public RobotWithDefaultCurrentTaskBuilder() { reset(); }

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
        tasks = new HashSet<>();
        currentTask = new Task();
        status = Robot.Status.ALIVE;
        return this;
    }

    private void setFields(Robot robot) {
        robot.setTasks(tasks);
        robot.setCurrentTask(currentTask);
        robot.setStatus(status);
    }

}
