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
    private Set<Task> tasks;
    private Task      currentTask;

    public RobotWithDefaultCurrentTaskBuilder() { reset(); }

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
    public Robot getResult() {
        Robot result = new Robot();
        result.setTasks(tasks);
        result.setCurrentTask(currentTask);
        reset();
        return result;
    }

    @Override
    public RobotBuilder reset() {
        tasks       = new HashSet<>();
        currentTask = new Task();
        return this;
    }
}
