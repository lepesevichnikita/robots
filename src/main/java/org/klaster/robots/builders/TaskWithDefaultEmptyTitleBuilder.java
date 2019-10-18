package org.klaster.robots.builders;

import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public class TaskWithDefaultEmptyTitleBuilder implements TaskBuilder {

    private Robot robot;
    private Task.Status status;
    private String description;
    private String title;

    public TaskWithDefaultEmptyTitleBuilder() { reset(); }

    @Override
    public TaskBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public TaskBuilder setRobot(Robot robot) {
        this.robot = robot;
        return this;
    }

    @Override
    public TaskBuilder setStatus(Task.Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Task getTask() {
        Task task = new Task();
        setFields(task);
        reset();
        return task;
    }

    @Override
    public TaskBuilder reset() {
        robot = null;
        title = "";
        description = "";
        status = Task.Status.WAITING;
        return this;
    }

    private void setFields(Task task) {
        setRobotToTaskIfHasIt(task);
        task.setTitle(title);
        task.setStatus(status);
        task.setDescription(description);
    }

    private void setRobotToTaskIfHasIt(Task task) {
        if (robot != null) {
            task.setRobot(robot);
        }
    }

}
