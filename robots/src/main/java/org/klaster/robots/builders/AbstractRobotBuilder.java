package org.klaster.robots.builders;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.states.IdleRobotState;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/21/19
 * @project robots
 */
public abstract class AbstractRobotBuilder implements RobotBuilder {
    private Task currentTask;
    private Set<Task> tasks;
    private RobotState currentState;

    public AbstractRobotBuilder() { reset(); }

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
    public RobotBuilder setCurrentState(RobotState currentState) {
        this.currentState = currentState;
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
        resetTasks();
        resetCurrentState();
        resetCurrentTask();
        return this;
    }

    private void setFields(Robot robot) {
        robot.setTasks(tasks);
        robot.changeCurrentState(currentState);
        if (currentTask != null) {
            robot.addTaskAndSetAsCurrentIfPossible(currentTask);
        }
    }

    protected void resetTasks() {
        setTasks(new HashSet<>());
    }

    protected void resetCurrentState() {
        setCurrentState(new IdleRobotState());
    }

    protected void resetCurrentTask() {
        setCurrentTask(new TaskWithDefaultEmptyTitleBuilder().getTask());
    }
}
