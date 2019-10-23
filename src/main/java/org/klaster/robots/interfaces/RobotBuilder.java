package org.klaster.robots.interfaces;

import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;

import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public interface RobotBuilder {

    RobotBuilder setTasks(Set<Task> tasks);

    RobotBuilder setCurrentTask(Task currentTask);

    RobotBuilder setCurrentState(RobotState state);

    RobotBuilder reset();

    Robot getRobot();
}
