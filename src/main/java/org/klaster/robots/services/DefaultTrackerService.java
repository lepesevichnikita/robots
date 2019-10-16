package org.klaster.robots.services;

import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;
import org.klaster.robots.repositories.RobotsRepository;
import org.klaster.robots.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */

@Service("defaultTrackerService")
public class DefaultTrackerService implements TrackerService {
    @Autowired
    @Qualifier("robotsRepository")
    RobotsRepository robotsRepository;

    @Autowired
    @Qualifier("tasksRepository")
    TasksRepository tasksRepository;

    @Override
    public List<Task> getGeneralWaitingTasks() {
        List<Task> generalWaitingTasks = StreamSupport.stream(tasksRepository.findAll().spliterator(), false)
                                                      .filter(t -> t.isWaiting() && t.isGeneral())
                                                      .collect(Collectors.toList());
        return generalWaitingTasks;
    }

    @Override
    public List<Robot> getAliveIdleRobots() {
        List<Robot> idleRobots = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                              .filter(r -> r.isIdle() && r.isAlive())
                                              .collect(Collectors.toList());
        return idleRobots;
    }

    @Override
    public List<Robot> getAliveWorkingRobots() {
        List<Robot> workingRobots = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                                 .filter(r -> r.isWorking())
                                                 .collect(Collectors.toList());
        return workingRobots;
    }

    @Override
    public Task getFirstGeneralWaitingTask() {
        Task firstWaitingTask = StreamSupport.stream(tasksRepository.findAll().spliterator(), false)
                                             .filter(t -> t.isWaiting() && t.isGeneral())
                                             .findFirst().orElse(null);
        return firstWaitingTask;
    }

    @Override
    public Robot getFirstAliveIdleRobot() {
        Robot firstIdleRobot = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                            .filter(r -> r.isIdle() && r.isAlive())
                                            .findFirst().orElse(null);
        return firstIdleRobot;
    }

    @Override
    public Robot getFirstAliveWorkingRobot() {
        Robot firstWorkingRobot = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                               .filter(r -> r.isWorking() && r.isAlive())
                                               .findFirst().orElse(null);
        return firstWorkingRobot;
    }


    @Override
    public Task createGeneralTask(Task newTask) {
        Robot robot = getFirstOrCreateAliveIdleRobot();
        robot.setCurrentTask(newTask);
        robot.getCurrentTask().execute();
        saveRobot(robot);
        saveTask(newTask);
        return newTask;
    }

    @Override
    public Robot getFirstOrCreateAliveIdleRobot() {
        Robot aliveIdleRobot = getAliveIdleRobots().size() > 0 ? getFirstAliveIdleRobot() : createNewIdleRobot();
        return aliveIdleRobot;
    }


    @Override
    public int getAllAliveRobotsCount() {
        int allAliveRobotsCount = getAllAliveRobots().size();
        return allAliveRobotsCount;
    }

    @Override
    public List<Robot> getAllAliveRobots() {
        List<Robot> allAliveRobots = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                                  .filter(r -> r.isAlive())
                                                  .collect(Collectors.toList());
        return allAliveRobots;
    }

    @Override
    public Task createNewTask() {
        return saveTask(new Task());
    }

    @Override
    public Robot createNewIdleRobot() {
        return saveRobot(new Robot());
    }

    @Override
    public Task saveTask(Task newTask) {
        return tasksRepository.saveAndFlush(newTask);
    }

    @Override
    public Robot saveRobot(Robot newRobot) {
        return robotsRepository.saveAndFlush(newRobot);
    }

    @Override
    public Task createTaskToRobot(Robot robot, Task newTask) {
        robot.setCurrentTask(newTask);
        robot.getCurrentTask().execute();
        saveRobot(robot);
        saveTask(newTask);
        return newTask;
    }
}
