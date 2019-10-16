package org.klaster.robots.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.klaster.robots.builders.RobotsWithDefaultCurrentTaskBuilder;
import org.klaster.robots.builders.RobotsWithoutDefaultCurrentTaskBuilder;
import org.klaster.robots.builders.TasksWithDefaultSuicideTitleBuilder;
import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TasksBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

    private static RobotBuilder robotWithDefaultCurrentTaskBuilder;
    private static RobotBuilder robotWithoutDefaultCurrentTaskBuilder;
    private static TasksBuilder tasksWithDefaultSuicideTitleBuilder;

    @BeforeAll
    private static void init() {
        robotWithDefaultCurrentTaskBuilder    = new RobotsWithDefaultCurrentTaskBuilder();
        robotWithoutDefaultCurrentTaskBuilder = new RobotsWithoutDefaultCurrentTaskBuilder();
        tasksWithDefaultSuicideTitleBuilder   = new TasksWithDefaultSuicideTitleBuilder();
    }

    @Autowired
    ApplicationContext context;

    private static Stream<Arguments> tasksForAllRobotsWithoutRobotsCreating() {
        return Stream.of(
                Arguments.of(new Task(), 0, 1),
                Arguments.of(new Task(), 1, 2),
                Arguments.of(new Task(), 2, 3),
                Arguments.of(new Task(), 3, 4),
                Arguments.of(new Task(), 4, 5),
                Arguments.of(new Task(), 5, 6),
                Arguments.of(new Task(), 6, 7)
        );
    }

    private static Stream<Arguments> tasksForAllRobotsWithRobotsCreatingIfNeeded() {
        return Stream.of(
                Arguments.of(new Task()),
                Arguments.of(new Task()),
                Arguments.of(new Task()),
                Arguments.of(new Task()),
                Arguments.of(new Task()),
                Arguments.of(new Task()),
                Arguments.of(new Task())
        );
    }

    private static Stream<Arguments> tasksForConcreteIdleRobots() {
        return Stream.of(
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getRobot())
        );
    }

    private static Stream<Arguments> taskForConcreteWorkingRobots() {
        return Stream.of(
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getRobot())
        );
    }

    private static Stream<Arguments> suicideTasks() {
        return Stream.of(
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.withDescription("first suicide task").getTask()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.withDescription("second suicide task").getTask()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.withDescription("third suicide task").getTask()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.withDescription("fourth suicide task").getTask()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.withDescription("fifth suicide task").getTask())
        );
    }

    private static Stream<Arguments> suicideTaskForConcreteWorkingRobots() {
        return Stream.of(
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.getTask(),
                             robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.getTask(),
                             robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.getTask(),
                             robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.getTask(),
                             robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.getTask(),
                             robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.getTask(),
                             robotWithDefaultCurrentTaskBuilder.getRobot()),
                Arguments.of(tasksWithDefaultSuicideTitleBuilder.getTask(),
                             robotWithDefaultCurrentTaskBuilder.getRobot())
        );
    }

    @ParameterizedTest
    @MethodSource("tasksForAllRobotsWithoutRobotsCreating")
    void addNewTasksIntoGeneralQueue(Task newTask, int expectedPreviousTasksCount, int expectedActualTasksCount) {
        assertEquals(expectedPreviousTasksCount, trackerService.getGeneralWaitingTasks().size());
        assertNotNull(trackerService.saveTask(newTask).getId());
        assertEquals(expectedActualTasksCount, trackerService.getGeneralWaitingTasks().size());
    }

    @ParameterizedTest
    @MethodSource("tasksForAllRobotsWithRobotsCreatingIfNeeded")
    void createNewRobotsForNewGeneralTasksOnlyIfThereIsNoIdleRobots(Task newTask) {
        int previousIdleRobotsCount = trackerService.getAliveIdleRobots().size();
        int previousAllRobotsCount = trackerService.getAllAliveRobotsCount();
        trackerService.createGeneralTask(newTask);
        assertEquals(previousIdleRobotsCount, trackerService.getAliveIdleRobots().size());
        assertEquals(previousAllRobotsCount + 1, trackerService.getAllAliveRobotsCount());
    }

    @ParameterizedTest
    @MethodSource("tasksForConcreteIdleRobots")
    void addNewTaskForConcreteIdleRobot(Task newTask) {
        int previousIdleRobotsCount = trackerService.getAliveIdleRobots().size();
        int previousAllRobotsCount = trackerService.getAllAliveRobotsCount();
        Robot idleRobot = trackerService.createNewIdleRobot();
        trackerService.createTaskToRobot(idleRobot, newTask);
        assertEquals(previousIdleRobotsCount, trackerService.getAliveIdleRobots().size());
        assertEquals(previousAllRobotsCount + 1, trackerService.getAllAliveRobotsCount());
        assertTrue(newTask.isProcessing());
    }


    @ParameterizedTest
    @MethodSource("taskForConcreteWorkingRobots")
    void addNewTaskForConcreteWorkingRobot(Task newTask, Robot concreteWorkingRobot) {
        int previousAliveIdleRobotsCount = trackerService.getAliveIdleRobots().size();
        int previousAllAliveRobotsCount = trackerService.getAllAliveRobotsCount();
        trackerService.saveRobot(concreteWorkingRobot);
        trackerService.createTaskToRobot(concreteWorkingRobot, newTask);
        assertEquals(previousAliveIdleRobotsCount, trackerService.getAliveIdleRobots().size());
        assertEquals(previousAllAliveRobotsCount + 1, trackerService.getAllAliveRobotsCount());
        assertTrue(newTask.isWaiting());
    }

    @ParameterizedTest
    @MethodSource("suicideTasks")
    void killOneOfIdleRobotAfterGeneralTaskAdding(Task newSuicideTask) {
        int previousAllAliveRobotsCount = trackerService.getAllAliveRobotsCount();
        trackerService.createNewIdleRobot();
        trackerService.createGeneralTask(newSuicideTask);
        assertEquals(previousAllAliveRobotsCount, trackerService.getAllAliveRobotsCount());
    }

    @ParameterizedTest
    @MethodSource("suicideTasks")
    void killConcreteIdleRobot(Task newSuicideTask) {
        Robot idleRobot = trackerService.createNewIdleRobot();
        trackerService.createTaskToRobot(idleRobot, newSuicideTask);
        assertTrue(idleRobot.isDead());
    }

    @ParameterizedTest
    @MethodSource("suicideTaskForConcreteWorkingRobots")
    void doNotKillConcreteWorkingRobot(Task newSuicideTask, Robot concreteWorkingRobot) {
        trackerService.createTaskToRobot(concreteWorkingRobot, newSuicideTask);
        assertTrue(concreteWorkingRobot.isAlive());
    }
}
