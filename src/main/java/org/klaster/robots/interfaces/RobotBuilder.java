package org.klaster.robots.interfaces;

import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public interface RobotBuilder extends Builder<Robot> {
    RobotBuilder withTasks(Set<Task> tasks);

    RobotBuilder withCurrentTask(Task currentTask);
}
