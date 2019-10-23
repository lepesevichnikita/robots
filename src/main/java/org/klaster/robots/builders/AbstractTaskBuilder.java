package org.klaster.robots.builders;

import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.abstracts.State;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.states.WaitingTaskState;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/21/19
 * @project robots
 */
public abstract class AbstractTaskBuilder implements TaskBuilder {
    private Robot robot;
    private String description;
    private String title;
    private State currentState;

    public AbstractTaskBuilder() { reset(); }

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
    public TaskBuilder setCurrentState(State currentState) {
        this.currentState = currentState;
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
        resetRobot();
        resetTitle();
        resetDescription();
        resetCurrentState();
        return this;
    }

    protected void resetCurrentState() {
        setCurrentState(new WaitingTaskState());
    }

    protected void resetDescription() {
        setDescription("");
    }

    protected void resetTitle() {
        setTitle("");
    }

    protected void resetRobot() {
        setRobot(null);
    }

    private void setFields(Task task) {
        task.setTitle(title);
        task.setDescription(description);
        task.changeCurrentState(currentState);
        task.setRobot(robot);
    }
}
