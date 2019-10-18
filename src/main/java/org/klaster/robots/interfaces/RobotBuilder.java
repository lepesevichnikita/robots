package org.klaster.robots.interfaces;

import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public interface RobotBuilder {

    RobotBuilder setTasks(Set<Task> tasks);
    RobotBuilder setCurrentTask(Task currentTask);
    RobotBuilder setStatus(Robot.Status status);
    RobotBuilder reset();

    Robot getRobot();
}
