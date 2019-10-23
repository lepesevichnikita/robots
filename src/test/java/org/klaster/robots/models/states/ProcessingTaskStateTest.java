package org.klaster.robots.models.states;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.abstracts.TaskState;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.notifications.FailedActionNotification;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/22/19
 * @project robot
 */
@SpringBootTest
public class ProcessingTaskStateTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("robotWithoutDefaultCurrentTaskBuilder")
    private RobotBuilder robotBuilder;

    @Autowired
    @Qualifier("taskWithDefaultEmptyTitleBuilder")
    private TaskBuilder taskBuilder;

    private Task processingTask;

    @DataProvider(name = "newStates")
    private static Object[][] newStates() {
        return new Object[][]{
                {new ProcessingTaskState(), false},
                {new WaitingTaskState(), true},
                {new CompletedTaskState(), true}
        };
    }

    @DataProvider(name = "newStatesWithNotifications")
    private static Object[][] newStatesWithNotifications() {
        return new Object[][]{
                {new ProcessingTaskState(), 2},
                {new WaitingTaskState(), 3},
                {new CompletedTaskState(), 3}
        };
    }

    @BeforeMethod(alwaysRun = true)
    private void reset() {
        processingTask = taskBuilder.setCurrentState(new ProcessingTaskState()).getTask();
        processingTask = Mockito.spy(processingTask);
        processingTask.getCurrentState().setContext(processingTask);
        when(processingTask.execute()).thenReturn(true);
    }

    @Test(dataProvider = "newStates")
    public void changeCurrentStateToStateOfNonEqualClassOnly(TaskState newState, boolean expectedIsCurrent) {
        processingTask.changeCurrentState(newState);
        assertEquals(expectedIsCurrent, newState.isCurrent());
    }

    @Test(dataProvider = "newStates")
    public void addNonEqualStateToStates(TaskState newState, boolean expectedHasNewState) {
        processingTask.changeCurrentState(newState);
        assertEquals(expectedHasNewState, processingTask.getStates().contains(newState));
    }

    @Test(dataProvider = "newStatesWithNotifications")
    public void notifyAboutStateChangingOnlyIfNewStateNonEqualToCurrentState(TaskState newState,
                                                                             int expectedNotificationsNumber) {
        processingTask.changeCurrentState(newState);
        assertEquals(expectedNotificationsNumber, processingTask.getNotifications().size());
    }

    @Test
    public void dontChangeStateIfTaskWasNotExecuted() {
        when(processingTask.execute()).thenReturn(false);
        processingTask.start();
        assertTrue(processingTask.isProcessing());
    }

    @Test
    public void dontNotifyAboutFailedAttemptToStart() {
        processingTask.start();
        assertFalse(processingTask.containsNotificationOfType(FailedActionNotification.class));
    }

    @Test
    public void changeTaskStateToCompletedIfTaskExecuted() {
        processingTask.start();
        assertTrue(processingTask.isCompleted());
    }

    @Test
    public void storeCorrectName() {
        assertEquals("PROCESSING", processingTask.getCurrentState().getName());
    }

    @Test
    public void denyToChangeRobot() {
        Robot newRobot = robotBuilder.getRobot();
        processingTask.changeRobot(newRobot);
        assertNotEquals(newRobot, processingTask.getRobot());
    }

    @Test
    public void notifyAboutFailedAttemptToChangeRobot() {
        processingTask.changeRobot(robotBuilder.getRobot());
        assertTrue(processingTask.containsNotificationOfType(FailedActionNotification.class));
    }

    @Test
    public void setPassedToConstructorRobotAsParentEntity() {
        TaskState processingTaskState = new ProcessingTaskState(processingTask);
        assertEquals(processingTask, processingTaskState.getTask());
    }

}