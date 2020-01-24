package org.klaster.robots.models.dto;

import static org.junit.Assert.assertEquals;

import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.models.contexts.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/22/19
 * @project robots
 */
@SpringBootTest
public class TaskDTOTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TaskBuilder taskWithDefaultEmptyTitleBuilder;

    @Autowired
    private RobotBuilder robotWithoutDefaultCurrentTaskBuilder;

    private Task testTask;
    private TaskDTO taskDTO;

    @BeforeMethod
    private void init() {
        testTask =
            taskWithDefaultEmptyTitleBuilder.setTitle("task")
                                            .setDescription("task service")
                                            .setRobot(robotWithoutDefaultCurrentTaskBuilder.getRobot())
                                            .getTask();
        taskDTO = TaskDTO.fromTask(testTask);
    }

    @Test
    public void createTaskDTOWithEqualTitle() {
        assertEquals(taskDTO.getTitle(), testTask.getTitle());
    }

    @Test
    public void createTaskDTOWithEqualDescription() {
        assertEquals(taskDTO.getDescription(), testTask.getDescription());
    }

    @Test
    public void createTaskDTOWithEqualRobot() {
        assertEquals(taskDTO.getRobot(), testTask.getRobot());
    }

    @Test
    public void createTaskWithEqualTitle() {
        assertEquals(taskDTO.toTask().getTitle(), testTask.getTitle());
    }

    @Test
    public void createTaskWithEqualDescription() {
        assertEquals(taskDTO.toTask().getDescription(), testTask.getDescription());
    }

    @Test
    public void createTaskWithEqualRobot() {
        assertEquals(taskDTO.toTask().getRobot(), testTask.getRobot());
    }

}