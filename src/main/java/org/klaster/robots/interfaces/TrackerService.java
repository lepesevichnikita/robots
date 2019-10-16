package org.klaster.robots.interfaces;

import org.klaster.robots.models.Task;
import org.klaster.robots.models.Robot;

import java.util.List;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */
public interface TrackerService {
    List<Task> getGeneralWaitingTasks();
    List<Robot> getIdleRobots();
    List<Robot> getWorkingRobots();

    Task getFirstWaitingTask();
    Robot getFirstIdleRobot();
    Robot getFirstWorkingRobot();

    Task createGeneralTask(Task newTask);

    long getRobotsCount();

    Task createTask();

    org.klaster.robots.models.Robot createRobot();

    Task saveTask(Task newTask);

    Robot saveRobot(Robot newRobot);

    Task createTaskForRobot(Robot idleRobot, Task newTask);
}
