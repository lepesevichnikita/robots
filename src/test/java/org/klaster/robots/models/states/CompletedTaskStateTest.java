package org.klaster.robots.models.states;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.abstracts.TaskState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.NotificationAboutAttemptToProccessUnsupportedAction;
import org.klaster.robots.models.notifications.NotificationAboutFailedAttemptToChangeState;
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
public class CompletedTaskStateTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TaskBuilder taskWithDefaultEmptyTitleBuilder;

    @Autowired
    private RobotBuilder robotWithoutDefaultCurrentTaskBuilder;

    private Task completedTask;

    @DataProvider(name = "newStates")
    private static Object[][] newStates() {
        return new Object[][]{
                {new CompletedTaskState(), false},
                {new WaitingTaskState(), false},
                {new ProcessingTaskState(), false}
        };
    }

    @DataProvider(name = "newStatesWithNotifications")
    private static Object[][] newStatesWithNotifications() {
        return new Object[][]{
                {new CompletedTaskState(), false},
                {new WaitingTaskState(), true},
                {new ProcessingTaskState(), true}
        };
    }

    @BeforeMethod
    private void reset() {
        completedTask = taskWithDefaultEmptyTitleBuilder.setCurrentState(new CompletedTaskState()).getTask();
    }


    @Test(dataProvider = "newStates")
    public void denyToChangeState(TaskState newState, boolean expectedIsCurrent) {
        completedTask.changeCurrentState(newState);
        assertEquals(expectedIsCurrent, newState.isCurrent());
    }


    @Test(dataProvider = "newStates")
    public void dontAddNewStateToStates(TaskState newState, boolean expectedHasNewState) {
        completedTask.changeCurrentState(newState);
        assertEquals(expectedHasNewState, completedTask.getStates().contains(newState));
    }


    @Test(dataProvider = "newStatesWithNotifications")
    public void notifyAboutFailedAttemptToChangeState(TaskState newState,
                                                      boolean expectedHasNotificationAboutFailedAttemptToChangeState) {
        completedTask.changeCurrentState(newState);
        assertEquals(expectedHasNotificationAboutFailedAttemptToChangeState,
                     completedTask.containsNotificationOfType(NotificationAboutFailedAttemptToChangeState.class));
    }

    @Test
    public void denyToStart() {
        completedTask.start();
        assertTrue(completedTask.isCompleted());
    }

    @Test
    public void notifyAboutFailedAttemptToStart() {
        completedTask.start();
        assertTrue(completedTask.containsNotificationOfType(NotificationAboutAttemptToProccessUnsupportedAction.class));
    }

    @Test
    public void storeCorrectName() {
        assertEquals("COMPLETED", completedTask.getCurrentState().getName());
    }

    @Test
    public void denyToChangeRobot() {
        Robot newRobot = robotWithoutDefaultCurrentTaskBuilder.getRobot();
        completedTask.changeRobot(newRobot);
        assertNotEquals(newRobot, completedTask.getRobot());
    }

    @Test
    public void notifyAboutFailedAttemptToChangeRobot() {
        completedTask.changeRobot(robotWithoutDefaultCurrentTaskBuilder.getRobot());
        assertTrue(completedTask.containsNotificationOfType(NotificationAboutAttemptToProccessUnsupportedAction.class));
    }

    @Test
    public void setPassedToConstructorRobotAsParentEntity() {
        TaskState completedTaskState = new CompletedTaskState(completedTask);
        assertEquals(completedTask, completedTaskState.getTask());
    }

}