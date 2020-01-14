package org.klaster.robots.services;

import org.klaster.robots.builders.TaskWithDefaultEmptyTitleBuilder;
import org.klaster.robots.builders.TaskWithDefaultSuicideTitleBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */

@SpringBootTest
public class DefaultTrackerServiceTest extends AbstractTestNGSpringContextTests {
    private static final int THREAD_POOL_SIZE = 4;
    private static final int INVOCATION_COUNT = 4;
    private static final int TIME_OUT = 10000;

    private static TaskBuilder tasksWithDefaultSuicideTitleBuilder;
    private static TaskBuilder tasksWithDefaultEmptyTitleBuilder;

    private Robot idleRobot;

    @Autowired
    @Qualifier("defaultTrackerService")
    private TrackerService trackerService;

    @BeforeClass
    private static void init() {
        tasksWithDefaultSuicideTitleBuilder = new TaskWithDefaultSuicideTitleBuilder();
        tasksWithDefaultEmptyTitleBuilder = new TaskWithDefaultEmptyTitleBuilder();
    }

    @BeforeMethod
    private void reset() {
        idleRobot = trackerService.createNewIdleRobot();
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

    @Test(threadPoolSize = THREAD_POOL_SIZE, invocationCount = INVOCATION_COUNT, timeOut = TIME_OUT)
    public void createNewRobotToNewGeneralTasksCauseThereIsNoIdleRobots() {
        int previousAliveRobotsNumber = trackerService.getAliveRobots().size();
        trackerService.addGeneralTask(tasksWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(trackerService.getAliveRobots().size() >= previousAliveRobotsNumber);
    }

    @Test
    public void createNewRobotToGeneralTaskButNotIncreaseIdleRobotsNumber() {
        int previousIdleRobotsNumber = trackerService.getIdleRobots().size();
        trackerService.addGeneralTask(tasksWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(previousIdleRobotsNumber >= trackerService.getIdleRobots().size());
    }

    @Test
    public void addNewTaskToConcreteIdleRobotAndSetItProcessing() {
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(idleRobot, newTask);
        assertTrue(newTask.isProcessing());
    }

    @Test
    public void addNewTaskToConcreteIdleRobotAndSetRobotWorking() {
        trackerService.addTaskToRobot(idleRobot, tasksWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(idleRobot.isWorking());
    }

    @Test
    public void addNewTaskToConcreteWorkingRobotAndLeaveItWaiting() {
        changeIdleRobotToWorking();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(idleRobot, newTask);
        assertTrue(newTask.isWaiting());
    }

    @Test
    public void addNewTaskToConcreteWorkingRobotAndLeavePreviousTaskAsCurrent() {
        changeIdleRobotToWorking();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(idleRobot, newTask);
        assertNotEquals(idleRobot.getCurrentTask(), newTask);
    }

    @Test
    public void killOneOfIdleRobotAfterGeneralSuicideTaskAdding() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        trackerService.createNewIdleRobot();
        trackerService.addGeneralTask(newSuicideTask);
        assertTrue(newSuicideTask.getRobot().isDead());
    }

    @Test
    public void killIdleRobot() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        trackerService.addTaskToRobot(idleRobot, newSuicideTask);
        assertTrue(idleRobot.isDead());
    }

    @Test
    public void dontKillWorkingRobot() {
        Task newSuicideTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        changeIdleRobotToWorking();
        trackerService.addTaskToRobot(idleRobot, newSuicideTask);
        assertTrue(idleRobot.isWorking());
    }

    @Test
    public void addTaskToExistedWorkingRobot() {
        changeIdleRobotToWorking();
        Task newTask = tasksWithDefaultEmptyTitleBuilder.getTask();
        trackerService.addTaskToRobot(idleRobot, newTask);
        assertTrue(idleRobot.getTasks().contains(newTask));
    }

    @Test
    public void dontRunSuicideTaskAddedToExistedWorkingRobot() {
        changeIdleRobotToWorking();
        Task newTask = tasksWithDefaultSuicideTitleBuilder.getTask();
        trackerService.addTaskToRobot(idleRobot, newTask);
        assertTrue(newTask.isWaiting());
    }

    @Test(threadPoolSize = THREAD_POOL_SIZE, invocationCount = INVOCATION_COUNT, timeOut = TIME_OUT)
    public void denyToAddTasksAfterRobotDeath() {
        trackerService.addTaskToRobot(idleRobot, tasksWithDefaultSuicideTitleBuilder.getTask());
        int expectedTasksCount = 1;
        assertTrue(idleRobot.getTasks().size() <= expectedTasksCount);
    }

    private void changeIdleRobotToWorking() {
        idleRobot.addTaskAndSetAsCurrentIfPossible(tasksWithDefaultEmptyTitleBuilder.getTask());
        trackerService.saveRobot(idleRobot);
    }

}
