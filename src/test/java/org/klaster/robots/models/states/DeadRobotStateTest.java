package org.klaster.robots.models.states;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.NotificationAboutAttemptToProccessUnsupportedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/22/19
 * @project robots
 */
@SpringBootTest
public class DeadRobotStateTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RobotBuilder robotWithoutDefaultCurrentTaskBuilder;

    @Autowired
    private TaskBuilder taskWithDefaultEmptyTitleBuilder;

    private Robot deadRobot;

    @DataProvider(name = "newStates")
    private static Object[][] newStates() {
        return new Object[][]{
                {new WorkingRobotState(), false},
                {new DeadRobotState(), false},
                {new IdleRobotState(), false}
        };
    }

    @DataProvider(name = "newStatesWithNotifications")
    private static Object[][] newStatesWithNotifications() {
        return new Object[][]{
                {new WorkingRobotState(), 3},
                {new DeadRobotState(), 2},
                {new IdleRobotState(), 3}
        };
    }

    @BeforeMethod
    private void reset() {
        deadRobot = robotWithoutDefaultCurrentTaskBuilder.setCurrentState(new DeadRobotState()).getRobot();
    }

    @Test(dataProvider = "newStates")
    public void denyToChangeState(RobotState newState, boolean expectedIsCurrent) {
        deadRobot.changeCurrentState(newState);
        assertEquals(expectedIsCurrent, newState.isCurrent());
    }


    @Test(dataProvider = "newStates")
    public void dontAddNewStateToStates(RobotState newState, boolean expectedHasNewState) {
        deadRobot.changeCurrentState(newState);
        assertEquals(expectedHasNewState, deadRobot.getStates().contains(newState));
    }

    @Test(dataProvider = "newStatesWithNotifications")
    public void notifyAboutFailedAttemptToChangeStateToAnyStateExceptCurrent(RobotState newState,
                                                                             int expectedNotificationNumber) {
        deadRobot.changeCurrentState(newState);
        assertEquals(expectedNotificationNumber, deadRobot.getNotifications().size());
    }

    @Test
    public void dontAllowToStartCurrentTask() {
        deadRobot.startCurrentTask();
        assertTrue(deadRobot.isDead());
    }

    @Test
    public void notifyAboutFailedAttemptToStartCurrentTask() {
        deadRobot.startCurrentTask();
        assertTrue(deadRobot.containsNotificationOfType(NotificationAboutAttemptToProccessUnsupportedAction.class));
    }

    @Test
    public void storeCorrectName() {
        assertEquals("DEAD", deadRobot.getCurrentState().getName());
    }

    @Test
    public void denyDoAddNewTask() {
        Task newTask = taskWithDefaultEmptyTitleBuilder.getTask();
        deadRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertFalse(newTask.isCurrent());
    }

    @Test
    public void dontAddNewTaskToTasks() {
        Task newTask = taskWithDefaultEmptyTitleBuilder.getTask();
        deadRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertTrue(deadRobot.getTasks().isEmpty());
    }

    @Test
    public void notifyAboutFailedAttemptToAddNewTask() {
        Task newTask = taskWithDefaultEmptyTitleBuilder.getTask();
        deadRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertTrue(deadRobot.containsNotificationOfType(NotificationAboutAttemptToProccessUnsupportedAction.class));
    }

    @Test
    public void setPassedToConstructorRobotAsParentEntity() {
        RobotState deadRobotState = new DeadRobotState(deadRobot);
        assertEquals(deadRobot, deadRobotState.getRobot());
    }
}