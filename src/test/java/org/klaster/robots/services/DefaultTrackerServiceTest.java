package org.klaster.robots.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.klaster.robots.builders.RobotWithDefaultCurrentTaskBuilder;
import org.klaster.robots.builders.RobotWithoutDefaultCurrentTaskBuilder;
import org.klaster.robots.builders.TaskWithDefaultEmptyTitleBuilder;
import org.klaster.robots.builders.TaskWithDefaultSuicideTitleBuilder;
import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;
import org.klaster.robots.repositories.RobotsRepository;
import org.klaster.robots.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DefaultTrackerServiceTest {
    @Autowired
    @Qualifier("defaultTrackerService")
    TrackerService trackerService;

    @Autowired
    @Qualifier("robotsRepository")
    RobotsRepository robotsRepository;

    @Autowired
    @Qualifier("tasksRepository")
    TasksRepository tasksRepository;

    private static RobotBuilder robotWithDefaultCurrentTaskBuilder;
    private static RobotBuilder robotWithoutDefaultCurrentTaskBuilder;
    private static TaskBuilder tasksWithDefaultSuicideTitleBuilder;
    private static TaskBuilder tasksWithDefaultEmptyTitleBuilder;

    @BeforeAll
    private static void init() {
        robotWithDefaultCurrentTaskBuilder = new RobotWithDefaultCurrentTaskBuilder();
        robotWithoutDefaultCurrentTaskBuilder = new RobotWithoutDefaultCurrentTaskBuilder();
        tasksWithDefaultSuicideTitleBuilder = new TaskWithDefaultSuicideTitleBuilder();
        tasksWithDefaultEmptyTitleBuilder = new TaskWithDefaultEmptyTitleBuilder();
    }

    @AfterEach
    private void reset() {
        robotsRepository.deleteAll();
        tasksRepository.deleteAll();
    }

    @Autowired
    private ApplicationContext context;

    private static Stream<Arguments> robotsAndStatuses() {
        return Stream.of(
                Arguments.of(robotWithoutDefaultCurrentTaskBuilder.getRobot(), Robot.Status.DEAD, 2),
                Arguments.of(robotWithoutDefaultCurrentTaskBuilder.setStatus(Robot.Status.DEAD).getRobot(),
                             Robot.Status.DEAD, 2),
                Arguments.of(robotWithoutDefaultCurrentTaskBuilder.getRobot(), Robot.Status.ALIVE, 1),
                Arguments.of(robotWithoutDefaultCurrentTaskBuilder.setStatus(Robot.Status.DEAD).getRobot(),
                             Robot.Status.ALIVE, 3)
        );
    }

    private static Stream<Arguments> tasksAndStatuses() {
        return Stream.of(
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.getTask(), Task.Status.PROCESSING, 2),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.getTask(), Task.Status.COMPLETED, 2),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.getTask(), Task.Status.WAITING, 1),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.setStatus(Task.Status.PROCESSING).getTask(),
                             Task.Status.PROCESSING, 2),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.setStatus(Task.Status.PROCESSING).getTask(),
                             Task.Status.WAITING, 3),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.setStatus(Task.Status.PROCESSING).getTask(),
                             Task.Status.COMPLETED, 3),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.setStatus(Task.Status.COMPLETED).getTask(),
                             Task.Status.COMPLETED, 2),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.setStatus(Task.Status.COMPLETED).getTask(),
                             Task.Status.WAITING, 3),
                Arguments.of(tasksWithDefaultEmptyTitleBuilder.setStatus(Task.Status.COMPLETED).getTask(),
                             Task.Status.PROCESSING, 3)
        );
    }

    @Test
    void addNewTasksIntoGeneralQueue() {
        int previousTasksNumber = trackerService.getGeneralWaitingTasks().size();
        trackerService.createNewGeneralWaitingTask();
        assertEquals(previousTasksNumber + 1, trackerService.getGeneralWaitingTasks().size());
    }

    @Test
    void createNewRobotForNewGeneralTasksCauseThereIsNoIdleRobots() {
        int previousAllRobotsNumber = trackerService.getAllAliveRobotsNumber();
        trackerService.createGeneralTask(tasksWithDefaultEmptyTitleBuilder.getTask());
        assertEquals(previousAllRobotsNumber + 1, trackerService.getAllAliveRobotsNumber());
    }

    @Test
    void createNewRobotForGeneralTaskButNotIncreaseIdleRobotsNumber() {
        int previousIdleRobotsNumber = trackerService.getAliveIdleRobots().size();
        trackerService.createGeneralTask(tasksWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(previousIdleRobotsNumber >= trackerService.getAliveIdleRobots().size());
    }

    @Test
    void addNewTaskForConcreteIdleRobotAndSetItProcessing() {
        Robot idleRobot = trackerService.createNewIdleRobot();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.createTaskToRobot(idleRobot, newTask);
        assertTrue(newTask.isProcessing());
    }

    @Test
    void addNewTaskForConcreteIdleRobotAndSetRobotWorking() {
        Robot idleRobot = trackerService.createNewIdleRobot();
        trackerService.createTaskToRobot(idleRobot, tasksWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(idleRobot.isWorking());
    }

    @Test
    void addNewTaskForConcreteWorkingRobotAndLeaveItWaiting() {
        Robot workingRobot = robotWithDefaultCurrentTaskBuilder.getRobot();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.saveRobot(workingRobot);
        trackerService.createTaskToRobot(workingRobot, newTask);
        assertTrue(newTask.isWaiting());
    }

    @Test
    void addNewTaskForConcreteWorkingRobotAndLeavePreviousTaskAsCurrent() {
        Robot workingRobot = robotWithDefaultCurrentTaskBuilder.getRobot();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.saveRobot(workingRobot);
        trackerService.createTaskToRobot(workingRobot, newTask);
        assertNotEquals(newTask, workingRobot.getCurrentTask());
    }

    @Test
    void killOneOfIdleRobotAfterGeneralTaskAdding() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        int previousAllAliveRobotsNumber = trackerService.getAllAliveRobotsNumber();
        trackerService.createNewIdleRobot();
        trackerService.createGeneralTask(newSuicideTask);
        assertEquals(previousAllAliveRobotsNumber, trackerService.getAllAliveRobotsNumber());
    }

    @Test
    void illIdleRobot() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        Robot idleRobot = trackerService.createNewIdleRobot();
        trackerService.createTaskToRobot(idleRobot, newSuicideTask);
        assertTrue(idleRobot.isDead());
    }

    @Test
    void doNotKillWorkingRobot() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        Robot workingRobot = robotWithDefaultCurrentTaskBuilder.getRobot();
        trackerService.createTaskToRobot(workingRobot, newSuicideTask);
        assertTrue(workingRobot.isAlive());
    }

    @ParameterizedTest
    @MethodSource("robotsAndStatuses")
    void createNotificationAfterRobotStatusChanged(Robot robot,
                                                   Robot.Status newStatus,
                                                   int expectedNotificationsCount) {
        robot.setStatus(newStatus);
        trackerService.saveRobot(robot);
        assertEquals(expectedNotificationsCount, robot.getNotifications().size());
    }

    @ParameterizedTest
    @MethodSource("tasksAndStatuses")
    void createNotificationAfterTasksStateChanged(Task task, Task.Status newStatus, int expectedNotificationsCount) {
        task.setStatus(newStatus);
        assertEquals(expectedNotificationsCount, task.getNotifications().size());
    }

    @Test
    void createNotificationIfRobotWasCreated() {
        Robot newRobot = trackerService.createNewIdleRobot();
        assertEquals(1, newRobot.getNotifications().size());
    }

    @Test
    void createNotificationIfTaskWasCreated() {
        Task newTask = trackerService.createNewGeneralWaitingTask();
        assertEquals(1, newTask.getNotifications().size());
    }
}
