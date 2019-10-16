package org.klaster.robots.services;

import com.sun.org.apache.xpath.internal.Arg;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.klaster.robots.builders.RobotWithDefaultCurrentTaskBuilder;
import org.klaster.robots.builders.RobotWithoutDefaultCurrentTaskBuilder;
import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.Task;
import org.klaster.robots.models.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @BeforeAll
    private static void init() {
        robotWithDefaultCurrentTaskBuilder = new RobotWithDefaultCurrentTaskBuilder();
        robotWithoutDefaultCurrentTaskBuilder = new RobotWithoutDefaultCurrentTaskBuilder();
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
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithoutDefaultCurrentTaskBuilder.getResult())
        );
    }

    private static Stream<Arguments> taskForConcreteWorkingRobots() {
        return Stream.of(
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getResult()),
                Arguments.of(new Task(), robotWithDefaultCurrentTaskBuilder.getResult())
        );
    }

    @ParameterizedTest
    @MethodSource("tasksForAllRobotsWithoutRobotsCreating")
    void addsNewTasksIntoGeneralQueue(Task newTask, int expectedPrevTasksCount, int expectedActualTasksCount) {
        assertEquals(expectedPrevTasksCount, trackerService.getGeneralWaitingTasks().size());
        assertNotNull(trackerService.saveTask(newTask).getId());
        assertEquals(expectedActualTasksCount, trackerService.getGeneralWaitingTasks().size());
    }

    @ParameterizedTest
    @MethodSource("tasksForAllRobotsWithRobotsCreatingIfNeeded")
    void createsNewRobotsForNewGeneralTasksOnlyIfThereIsNoIdleRobots(Task newTask) {
        int prevIdleRobotsCount = trackerService.getIdleRobots().size();
        long prevAllRobotsCount = trackerService.getRobotsCount();
        trackerService.createGeneralTask(newTask);
        assertEquals(prevAllRobotsCount + 1, trackerService.getRobotsCount());
        assertEquals(prevIdleRobotsCount, trackerService.getIdleRobots().size());
    }

    @ParameterizedTest
    @MethodSource("tasksForConcreteIdleRobots")
    void addsNewTaskForConcreteIdleRobot(Task newTask) {
        int prevIdleRobotsCount = trackerService.getIdleRobots().size();
        long prevAllRobotsCount = trackerService.getRobotsCount();
        Robot idleRobot = trackerService.createRobot();
        trackerService.createTaskForRobot(idleRobot, newTask);
        assertEquals(prevAllRobotsCount + 1, trackerService.getRobotsCount());
        assertEquals(prevIdleRobotsCount, trackerService.getIdleRobots().size());
        assertEquals(true, newTask.isProcessing());
    }


    @ParameterizedTest
    @MethodSource("taskForConcreteWorkingRobots")
    void addsNewTaskForConcreteWorkingRobot(Task newTask, Robot concreteWorkingRobot) {
        int prevIdleRobotsCount = trackerService.getIdleRobots().size();
        long prevAllRobotsCount = trackerService.getRobotsCount();
        trackerService.saveRobot(concreteWorkingRobot);
        trackerService.createTaskForRobot(concreteWorkingRobot, newTask);
        assertEquals(prevIdleRobotsCount, trackerService.getIdleRobots().size());
        assertEquals(true, newTask.isWaiting());
    }

}
