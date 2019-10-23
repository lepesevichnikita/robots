package org.klaster.robots.services;

import org.klaster.robots.builders.RobotWithDefaultCurrentTaskBuilder;
import org.klaster.robots.builders.TaskWithDefaultEmptyTitleBuilder;
import org.klaster.robots.builders.TaskWithDefaultSuicideTitleBuilder;
import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */

@SpringBootTest
public class DefaultTrackerServiceTest extends AbstractTestNGSpringContextTests {
    private static final int THREAD_POOL_SIZE = 4;
    private static final int INVOCATION_COUNT = 8;
    private static final int TIME_OUT = 10000;

    private static RobotBuilder robotWithDefaultCurrentTaskBuilder;
    private static TaskBuilder tasksWithDefaultSuicideTitleBuilder;
    private static TaskBuilder tasksWithDefaultEmptyTitleBuilder;

    @Autowired
    @Qualifier("defaultTrackerService")
    private TrackerService trackerService;

    @BeforeClass
    private static void init() {
        robotWithDefaultCurrentTaskBuilder = new RobotWithDefaultCurrentTaskBuilder();
        tasksWithDefaultSuicideTitleBuilder = new TaskWithDefaultSuicideTitleBuilder();
        tasksWithDefaultEmptyTitleBuilder = new TaskWithDefaultEmptyTitleBuilder();
    }

    @Test
    public void addNewTasksIntoGeneralQueue() {
        Task createdGeneralWaitingTask = trackerService.createNewGeneralWaitingTask();
        assertTrue(trackerService.hasTaskInGeneralQueue(createdGeneralWaitingTask));
    }

    @Test(threadPoolSize = THREAD_POOL_SIZE, invocationCount = INVOCATION_COUNT, timeOut = TIME_OUT)
    public void increaseNumberOfTasksWhenCreateGeneralTask() {
        int previousNumberOfGeneralWaitingTasks = trackerService.getGeneralWaitingTasks().size();
        trackerService.createNewGeneralWaitingTask();
        assertTrue(previousNumberOfGeneralWaitingTasks < trackerService.getGeneralWaitingTasks().size());
    }

    @Test
    public void createNewRobotToNewGeneralTasksCauseThereIsNoIdleRobots() {
        int previousAliveRobotsNumber = trackerService.getAliveRobots().size();
        trackerService.addGeneralTask(tasksWithDefaultEmptyTitleBuilder.getTask());
        assertEquals(previousAliveRobotsNumber + 1, trackerService.getAliveRobots().size());
    }

    @Test
    public void createNewRobotToGeneralTaskButNotIncreaseIdleRobotsNumber() {
        int previousIdleRobotsNumber = trackerService.getIdleRobots().size();
        trackerService.addGeneralTask(tasksWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(previousIdleRobotsNumber >= trackerService.getIdleRobots().size());
    }

    @Test
    public void addNewTaskToConcreteIdleRobotAndSetItProcessing() {
        Robot idleRobot = trackerService.createNewIdleRobot();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(idleRobot, newTask);
        assertTrue(newTask.isProcessing());
    }

    @Test
    public void addNewTaskToConcreteIdleRobotAndSetRobotWorking() {
        Robot idleRobot = trackerService.createNewIdleRobot();
        trackerService.addTaskToRobot(idleRobot, tasksWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(idleRobot.isWorking());
    }

    @Test
    public void addNewTaskToConcreteWorkingRobotAndLeaveItWaiting() {
        Robot workingRobot = robotWithDefaultCurrentTaskBuilder.getRobot();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(workingRobot, newTask);
        assertTrue(newTask.isWaiting());
    }

    @Test
    public void addNewTaskToConcreteWorkingRobotAndLeavePreviousTaskAsCurrent() {
        Robot workingRobot = robotWithDefaultCurrentTaskBuilder.getRobot();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.saveRobot(workingRobot);
        trackerService.addTaskToRobot(workingRobot, newTask);
        assertNotEquals(newTask, workingRobot.getCurrentTask());
    }

    @Test
    public void killOneOfIdleRobotAfterGeneralSuicideTaskAdding() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        int previousAliveRobotsNumber = trackerService.getAliveRobots().size();
        trackerService.createNewIdleRobot();
        trackerService.addGeneralTask(newSuicideTask);
        assertEquals(previousAliveRobotsNumber, trackerService.getAliveRobots().size());
    }

    @Test
    public void killIdleRobot() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        Robot idleRobot = trackerService.createNewIdleRobot();
        trackerService.addTaskToRobot(idleRobot, newSuicideTask);
        assertTrue(idleRobot.isDead());
    }

    @Test
    public void dontKillWorkingRobot() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        Robot workingRobot = robotWithDefaultCurrentTaskBuilder.getRobot();
        trackerService.addTaskToRobot(workingRobot, newSuicideTask);
        assertTrue(workingRobot.isWorking());
    }

    @Test
    public void addTaskToExistedWorkingRobot() {
        Robot workingRobot = trackerService.saveRobot(robotWithDefaultCurrentTaskBuilder.getRobot());
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(workingRobot, newTask);
        assertTrue(workingRobot.getTasks().contains(newTask));
    }

    @Test
    public void dontRunTaskAddedToExistedWorkingRobot() {
        Robot workingRobot = trackerService.saveRobot(robotWithDefaultCurrentTaskBuilder.getRobot());
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(workingRobot, newTask);
        assertTrue(workingRobot.getTasks().contains(newTask));
    }
}
