package org.klaster.robots.builders;

import org.klaster.robots.interfaces.TasksBuilder;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public class TasksWithDefaultSuicideTitleBuilder implements TasksBuilder {
    private Robot       robot;
    private Task.Status status;
    private String      description;
    private String      title;

    public TasksWithDefaultSuicideTitleBuilder() { reset(); }

    @Override
    public TasksBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public TasksBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public TasksBuilder withRobot(Robot robot) {
        assert (robot.getId() != null);
        this.robot = robot;
        return this;
    }

    @Override
    public TasksBuilder withStatus(Task.Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Task getTask() {
        Task task = new Task();
        if (robot != null) {
            task.setRobot(robot);
        }
        task.setTitle(title);
        task.setStatus(status);
        task.setDescription(description);
        return task;
    }

    @Override
    public TasksBuilder reset() {
        robot       = null;
        title       = "suicide";
        description = "";
        status      = Task.Status.WAITING;
        return this;
    }
}
