package org.klaster.robots.interfaces;

import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

import java.util.List;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */
public interface TrackerService {

    List<Robot> getAliveIdleRobots();
    List<Robot> getAliveWorkingRobots();
    List<Robot> getAllAliveRobots();
    List<Task> getGeneralWaitingTasks();

    Robot createNewIdleRobot();
    Robot getFirstAliveIdleRobot();
    Robot getFirstAliveWorkingRobot();
    Robot getFirstOrCreateAliveIdleRobot();
    Robot saveRobot(Robot newRobot);

    Task createGeneralTask(Task newTask);
    Task createNewGeneralWaitingTask();
    Task createTaskToRobot(Robot idleRobot, Task newTask);
    Task getFirstGeneralWaitingTask();
    Task saveTask(Task newTask);

    int getAllAliveRobotsNumber();
}
