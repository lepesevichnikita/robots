package org.klaster.robots.interfaces;

import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

import java.util.List;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */
public interface TrackerService {
    List<Task> getGeneralWaitingTasks();

    List<Robot> getAliveIdleRobots();

    List<Robot> getAliveWorkingRobots();

    Task getFirstGeneralWaitingTask();

    Robot getFirstAliveIdleRobot();

    Robot getFirstAliveWorkingRobot();

    Task createGeneralTask(Task newTask);

    Robot getFirstOrCreateAliveIdleRobot();

    int getAllAliveRobotsCount();

    List<Robot> getAllAliveRobots();

    Task createNewTask();

    Robot createNewIdleRobot();

    Task saveTask(Task newTask);

    Robot saveRobot(Robot newRobot);

    Task createTaskToRobot(Robot idleRobot, Task newTask);
}
