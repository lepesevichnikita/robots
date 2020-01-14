package org.klaster.robots.interfaces;

import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;

import java.util.List;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */
public interface TrackerService {

    List<Robot> getIdleRobots();

    List<Robot> getAliveRobots();

    List<Task> getGeneralWaitingTasks();

    Robot createNewIdleRobot();

    Robot getFirstIdleRobot();

    Robot getFirstOrCreateAliveIdleRobot();

    Robot saveRobot(Robot newRobot);

    Task addGeneralTask(Task newTask);

    Task addTaskToRobot(Robot idleRobot, Task newTask);

    Task createNewGeneralWaitingTask();

    boolean hasTaskInGeneralQueue(Task task);

    Task saveTask(Task newTask);

}
