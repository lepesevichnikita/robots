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
    public List<Robot> getIdleRobots() {
        List<Robot> idleRobots = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                              .filter(r -> r.isIdle())
                                              .collect(Collectors.toList());
        return idleRobots;
    }

    @Override
    public List<Robot> getWorkingRobots() {
        List<Robot> workingRobots = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                                 .filter(r -> r.isWorking())
                                                 .collect(Collectors.toList());
        return workingRobots;
    }

    @Override
    public Task getFirstWaitingTask() {
        Task firstWaitingTask = StreamSupport.stream(tasksRepository.findAll().spliterator(), false)
                                             .filter(t -> t.isWaiting())
                                             .findFirst().orElse(null);
        return firstWaitingTask;
    }

    @Override
    public Robot getFirstIdleRobot() {
        Robot firstIdleRobot = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                           .filter(r -> r.isIdle())
                                           .findFirst().orElse(null);
        return firstIdleRobot;
    }

    @Override
    public Robot getFirstWorkingRobot() {
        Robot firstWorkingRobot = StreamSupport.stream(robotsRepository.findAll().spliterator(), false)
                                            .filter(r -> r.isWorking())
                                            .findFirst().orElse(null);
        return firstWorkingRobot;
    }


    @Override
    public Task createGeneralTask(Task newTask) {
        assert(newTask.getId() == null);
        Task result = newTask;
        Robot robot;
        if (getIdleRobots().size() > 0) {
            robot = getFirstIdleRobot();
        }
        else {
            robot = createRobot();
        }
        robot.setCurrentTask(result);
        saveTask(result);
        return result;
    }


    @Override
    public long getRobotsCount() {
        return robotsRepository.count();
    }

    @Override
    public Task createTask() {
        return saveTask(new Task());
    }

    @Override
    public Robot createRobot() {
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
    public Task createTaskForRobot(Robot robot, Task newTask) {
        assert(newTask.getId() == null);
        assert(robot.getId() != null);
        Task result = newTask;
        if (robot.isIdle()) {
            robot.setCurrentTask(result);
        } else {
            result.setRobot(robot);
        }
        saveTask(result);
        return result;
    }
}
