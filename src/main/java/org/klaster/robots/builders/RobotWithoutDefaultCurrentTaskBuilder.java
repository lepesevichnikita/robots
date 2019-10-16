package org.klaster.robots.builders;

import org.klaster.robots.interfaces.Builder;
import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public class RobotWithoutDefaultCurrentTaskBuilder implements RobotBuilder {
    private Task      currentTask;
    private Set<Task> tasks;

    public RobotWithoutDefaultCurrentTaskBuilder() { reset(); }

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
        if (currentTask != null) {
            result.setCurrentTask(currentTask);
        }
        reset();
        return result;
    }

    @Override
    public Builder reset() {
        currentTask = null;
        tasks       = new HashSet<>();
        return this;
    }
}
