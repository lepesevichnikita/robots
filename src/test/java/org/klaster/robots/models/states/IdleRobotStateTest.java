package org.klaster.robots.models.states;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.NotificationAboutAttemptToProccessUnsupportedAction;
import org.klaster.robots.models.notifications.NotificationAboutCreating;
import org.klaster.robots.models.notifications.NotificationAboutStateChanging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/21/19
 * @project robots
 */
@SpringBootTest
public class IdleRobotStateTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private RobotBuilder robotWithoutDefaultCurrentTaskBuilder;

    @Autowired
    private TaskBuilder taskWithDefaultEmptyTitleBuilder;

    private Robot idleRobot;

    @DataProvider(name = "newStates")
    private static Object[][] newStates() {
        return new Object[][]{
                {new WorkingRobotState(), true},
                {new DeadRobotState(), true},
                {new IdleRobotState(), false}
        };
    }

    @DataProvider(name = "newStatesWithNotifications")
    private static Object[][] newStatesWithNotifications() {
        return new Object[][]{
                {new WorkingRobotState(), NotificationAboutStateChanging.class},
                {new DeadRobotState(), NotificationAboutStateChanging.class},
                {new IdleRobotState(), NotificationAboutCreating.class}
        };
    }

    @BeforeMethod
    private void reset() {
        idleRobot = robotWithoutDefaultCurrentTaskBuilder.getRobot();
    }

    @Test(dataProvider = "newStates")
    public void changeCurrentStateToStateOfNonEqualClassOnly(RobotState newState, boolean expectedIsCurrent) {
        idleRobot.changeCurrentState(newState);
        assertEquals(expectedIsCurrent, newState.isCurrent());
    }


    @Test(dataProvider = "newStates")
    public void addNonEqualStateToStates(RobotState newState, boolean expectedHasNewState) {
        idleRobot.changeCurrentState(newState);
        assertEquals(expectedHasNewState, idleRobot.getStates().contains(newState));
    }

    @Test(dataProvider = "newStatesWithNotifications")
    public void notifyAboutStateChangingOnlyIfNewStateNonEqualToCurrentState(RobotState newState,
                                                                             Class expectedNotificationType) {
        idleRobot.changeCurrentState(newState);
        assertTrue(idleRobot.containsNotificationOfType(expectedNotificationType));
    }

    @Test
    public void dontAllowToStartCurrentTask() {
        idleRobot.startCurrentTask();
        assertTrue(idleRobot.isIdle());
    }

    @Test
    public void notifyAboutFailedAttemptToStartCurrentTask() {
        idleRobot.startCurrentTask();
        assertTrue(idleRobot.containsNotificationOfType(NotificationAboutAttemptToProccessUnsupportedAction.class));
    }

    @Test
    public void storeCorrectName() {
        assertEquals("IDLE", idleRobot.getCurrentState().getName());
    }

    @Test
    public void changeCurrentStateToWorkingIfChangeCurrentTask() {
        idleRobot.addTaskAndSetAsCurrentIfPossible(taskWithDefaultEmptyTitleBuilder.getTask());
        assertTrue(idleRobot.isWorking());
    }

    @Test
    public void changeTaskStateToProcessing() {
        Task newTask = taskWithDefaultEmptyTitleBuilder.getTask();
        idleRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertTrue(newTask.isProcessing());
    }

    @Test
    public void setCurrentTrueForAddedTask() {
        Task newTask = taskWithDefaultEmptyTitleBuilder.getTask();
        idleRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertTrue(newTask.isCurrent());
    }

    @Test
    public void setAddedTaskAsCurrentForRobot() {
        Task newTask = taskWithDefaultEmptyTitleBuilder.getTask();
        idleRobot.addTaskAndSetAsCurrentIfPossible(newTask);
        assertEquals(newTask, idleRobot.getCurrentTask());
    }

    @Test
    public void setPassedToConstructorRobotAsParentEntity() {
        RobotState idleRobotState = new IdleRobotState(idleRobot);
        assertEquals(idleRobot, idleRobotState.getRobot());
    }
}
