package org.klaster.robots.services;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.repositories.NotificationsRepository;
import org.klaster.robots.repositories.RobotsRepository;
import org.klaster.robots.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    @Qualifier("notificationsRepository")
    NotificationsRepository notificationsRepository;

    @Autowired
    @Qualifier("robotWithoutDefaultCurrentTaskBuilder")
    RobotBuilder robotWithoutDefaultCurrentTaskBuilder;

    @Autowired
    @Qualifier("taskWithDefaultEmptyTitleBuilder")
    TaskBuilder taskWithDefaultEmptyTitleBuilder;

    @Override
    public List<Task> getGeneralWaitingTasks() {
        return tasksRepository.findAll().stream()
                              .filter(t -> t.isWaiting() && t.isGeneral())
                              .collect(Collectors.toList());
    }

    @Override
    public List<Robot> getIdleRobots() {
        return robotsRepository.findAll().stream()
                               .filter(Robot::isIdle)
                               .collect(Collectors.toList());
    }

    @Override
    public boolean hasTaskInGeneralQueue(Task task) {
        return tasksRepository.findAll().stream().anyMatch(t -> t.getId().equals(task.getId()));
    }

    @Override
    public Robot getFirstIdleRobot() {
        return robotsRepository.findAll().stream()
                               .filter(Robot::isIdle)
                               .findFirst().orElse(null);
    }


    @Override
    public Task addGeneralTask(Task newTask) {
        saveTask(newTask);
        Robot robot = getFirstOrCreateAliveIdleRobot();
        addTaskToRobotAndStartIt(robot, newTask);
        return newTask;
    }

    @Override
    public Robot getFirstOrCreateAliveIdleRobot() {
        return getIdleRobots().isEmpty() ? createNewIdleRobot() : getFirstIdleRobot();
    }


    @Override
    public List<Robot> getAliveRobots() {
        return robotsRepository.findAll().stream()
                               .filter(r -> !r.isDead())
                               .collect(Collectors.toList());
    }

    @Override
    public Task createNewGeneralWaitingTask() {
        return saveTask(taskWithDefaultEmptyTitleBuilder.getTask());
    }

    @Override
    public Robot createNewIdleRobot() {
        return saveRobot(robotWithoutDefaultCurrentTaskBuilder.getRobot());
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
    public Task addTaskToRobot(Robot robot, Task newTask) {
        saveTask(newTask);
        saveRobot(robot);
        addTaskToRobotAndStartIt(robot, newTask);
        return newTask;
    }

    private void addTaskToRobotAndStartIt(Robot robot, Task newTask) {
        robot.addTaskAndSetAsCurrentIfPossible(newTask);
        saveRobot(robot);
        robot.startCurrentTask();
        saveRobot(robot);
    }

}
