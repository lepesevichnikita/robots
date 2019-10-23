package org.klaster.robots.models.states;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.repositories.RobotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/21/19
 * @project robots
 */
@SpringBootTest
public class WorkingRobotStateTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("robotWithDefaultCurrentTaskBuilder")
    private RobotBuilder robotBuilder;

    @Autowired
    @Qualifier("taskWithDefaultEmptyTitleBuilder")
    private TaskBuilder taskBuilder;

    @Autowired
    @Qualifier("robotsRepository")
    private RobotsRepository robotsRepository;

    private Robot idleRobot;

    @DataProvider(name = "newStates")
    private static Object[][] newStates() {
        return new Object[][]{
                {new WorkingRobotState(), false},
                {new IdleRobotState(), true},
                {new DeadRobotState(), true}
        };
    }

    @DataProvider(name = "newStatesWithNotification")
    private static Object[][] newStatesWithNotifications() {
        return new Object[][]{
                {new IdleRobotState(), 3},
                {new DeadRobotState(), 3},
                {new WorkingRobotState(), 2}
        };
    }

    @BeforeMethod
    private void reset() {
        idleRobot = robotBuilder.getRobot();
        robotsRepository.deleteAll();
    }

    @Test(dataProvider = "newStates")
    public void changeCurrentStateToStateOfNonEqualClassOnly(RobotState newState, boolean expectedIsCurrent) {
        idleRobot.changeCurrentState(newState);
        assertEquals(expectedIsCurrent, newState.isCurrent());
    }


    @Test(dataProvider = "newStates")
    public void addNonEqualStateToStates(RobotState newState, boolean expectedIsCurrent) {
        idleRobot.changeCurrentState(newState);
        assertEquals(expectedIsCurrent, idleRobot.getStates().contains(newState));
    }

    @Test(dataProvider = "newStatesWithNotifications")
    public void notifyAboutStateChangingOnlyIfNewStateNonEqualToCurrentState(RobotState newState,
                                                                             int expectedNotificationsCount) {
        idleRobot.changeCurrentState(newState);
        assertEquals(expectedNotificationsCount, idleRobot.getNotifications().size());
    }

    @Test
    public void allowStartCurrentTask() {
        idleRobot.startCurrentTask();
        assertTrue(idleRobot.getCurrentTask().isProcessing());
    }

    @Test
    public void leaveWorkingAsCurrentStateAfterCurrentTaskStarting() {
        idleRobot.startCurrentTask();
        assertTrue(idleRobot.getCurrentTask().isProcessing());
    }

    @Test
    public void storeCorrectName() {
        assertEquals("WORKING", idleRobot.getCurrentState().getName());
    }


    @Test
    public void dontSetCurrentTrueForAddedTask() {
        Task newTask = taskBuilder.getTask();
        idleRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertFalse(newTask.isCurrent());
    }

    @Test
    public void dontSetAddedTaskAsCurrentForRobot() {
        Task newTask = taskBuilder.getTask();
        idleRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertNotEquals(newTask, idleRobot.getCurrentTask());
    }

    @Test
    public void leaveNewTaskWaitingAfterAdding() {
        Task newTask = taskBuilder.getTask();
        idleRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertTrue(newTask.isWaiting());
    }

    @Test
    public void setRobotAsParentToTaskAfterAdding() {
        Task newTask = taskBuilder.getTask();
        idleRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertEquals(idleRobot, newTask.getRobot());
    }

    @Test
    public void setPassedToConstructorRobotAsParentEntity() {
        RobotState workingRobotState = new WorkingRobotState(idleRobot);
        assertEquals(idleRobot, workingRobotState.getRobot());
    }

}
