package org.klaster.robots.models.states;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.abstracts.TaskState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.FailedActionNotification;
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
public class WaitingTaskStateTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TaskBuilder taskWithDefaultEmptyTitleBuilder;

    @Autowired
    private RobotBuilder robotWithoutDefaultCurrentTaskBuilder;

    private Task waitingTask;

    @DataProvider(name = "newStates")
    private static Object[][] newStates() {
        return new Object[][]{
                {new WaitingTaskState(), false},
                {new ProcessingTaskState(), true},
                {new CompletedTaskState(), true}
        };
    }

    @DataProvider(name = "newStatesWithNotifications")
    private static Object[][] newStatesWithNotifications() {
        return new Object[][]{
                {new WaitingTaskState(), 1},
                {new ProcessingTaskState(), 2},
                {new CompletedTaskState(), 2}
        };
    }

    @BeforeMethod
    private void reset() {
        waitingTask = taskWithDefaultEmptyTitleBuilder.getTask();
    }

    @Test(dataProvider = "newStates")
    public void changeCurrentStateToStateOfNonEqualClassOnly(TaskState newState, boolean expectedIsCurrent) {
        waitingTask.changeCurrentState(newState);
        assertEquals(expectedIsCurrent, newState.isCurrent());
    }


    @Test(dataProvider = "newStates")
    public void addNonEqualStateToStates(TaskState newState, boolean expectedHasNewState) {
        waitingTask.changeCurrentState(newState);
        assertEquals(expectedHasNewState, waitingTask.getStates().contains(newState));
    }

    @Test(dataProvider = "newStatesWithNotifications")
    public void notifyAboutStateChangingOnlyIfNewStateNonEqualToCurrentState(TaskState newState,
                                                                             int expectedNotificationsNumber) {
        waitingTask.changeCurrentState(newState);
        assertEquals(expectedNotificationsNumber, waitingTask.getNotifications().size());
    }

    @Test
    public void dontAllowToStart() {
        waitingTask.start();
        assertTrue(waitingTask.isWaiting());
    }

    @Test
    public void notifyAboutFailedAttemptToStart() {
        waitingTask.start();
        assertTrue(waitingTask.containsNotificationOfType(FailedActionNotification.class));
    }

    @Test
    public void storeCorrectName() {
        assertEquals("WAITING", waitingTask.getCurrentState().getName());
    }

    @Test
    public void allowToChangeRobot() {
        Robot newRobot = robotWithoutDefaultCurrentTaskBuilder.getRobot();
        waitingTask.changeRobot(newRobot);
        assertEquals(newRobot, waitingTask.getRobot());
    }

    @Test
    public void setNotCurrentIfRobotChange() {
        waitingTask.changeRobot(robotWithoutDefaultCurrentTaskBuilder.getRobot());
        assertFalse(waitingTask.isCurrent());
    }

    @Test
    public void setPassedToConstructorRobotAsParentEntity() {
        TaskState waitingTaskState = new WaitingTaskState(waitingTask);
        assertEquals(waitingTask, waitingTaskState.getTask());
    }
}