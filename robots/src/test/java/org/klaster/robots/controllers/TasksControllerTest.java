package org.klaster.robots.controllers;

import org.klaster.robots.builders.TaskWithDefaultEmptyTitleBuilder;
import org.klaster.robots.interfaces.RobotBuilder;
import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.dto.TaskDTO;
import org.klaster.robots.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/24/19
 * @project robots
 */

@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc
@TestExecutionListeners(MockitoTestExecutionListener.class)
public class TasksControllerTest extends AbstractTestNGSpringContextTests {
    private static final int NUMBER_OF_TASKS = 10;

    @Autowired
    private TrackerService defaultTrackerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("tasksRepository")
    private TasksRepository tasksRepository;

    @BeforeClass
    private void init() {
        TaskBuilder taskBuilder = new TaskWithDefaultEmptyTitleBuilder();
        for (int number = 0; number < NUMBER_OF_TASKS; number++) {
            defaultTrackerService.addGeneralTask(taskBuilder.getTask());
        }
    }

    @Test
    public void getAllTasks() throws Exception {
        int expectedTasksNumber = tasksRepository.findAll().size();
        mockMvc.perform((get("/task/all")))
               .andExpect(status().isOk())
               .andExpect(view().name("tasks"))
               .andExpect(model().attribute("tasks", hasSize(expectedTasksNumber)))
               .andExpect(model().attribute("newTask", instanceOf(TaskDTO.class)))
               .andExpect(model().attribute("aliveRobots", hasSize(expectedTasksNumber)));
    }

    @Test
    public void getExistedTaskById() throws Exception {
        Task existedTask = tasksRepository.findAll().get(0);
        mockMvc.perform(get("/task/{id}", existedTask.getId()))
               .andExpect(status().isOk())
               .andExpect(view().name("task"))
               .andExpect(model().attribute("task", hasProperty("id", is(existedTask.getId()))))
               .andExpect(model().attribute("task", hasProperty("title", is(existedTask.getTitle()))))
               .andExpect(model().attribute("task", hasProperty("description", is(existedTask.getDescription()))));
    }

    @Test
    public void getAllTasksIfTaskNotFoundById() throws Exception {
        int notExistedTaskId = 1001;
        mockMvc.perform(get("/task/{id}", notExistedTaskId))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/task/all"));
    }

    @Test
    public void createGeneralTask() throws Exception {
        mockMvc.perform(post("/task") .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("title", "title")
                                .param("description", "description")
                                .sessionAttr("task", new TaskDTO()))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/task/" + getLastCreatedTask().getId()))
               .andExpect(redirectedUrl("/task/" + getLastCreatedTask().getId()));
    }

    @Test
    public void addTaskToRobot() throws Exception {
        TaskDTO newTask = new TaskDTO();
        newTask.setRobot(defaultTrackerService.createNewIdleRobot());
        mockMvc.perform(post("/task")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("title", "title")
                                .param("description", "description")
                                .sessionAttr("task", new TaskDTO()))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/task/" + getLastCreatedTask().getId()))
               .andExpect(redirectedUrl("/task/" + getLastCreatedTask().getId()));
    }

    private Task getLastCreatedTask() {
        return tasksRepository.findAll()
                              .stream()
                              .sorted(Comparator.comparing(Task::getCreatedAt).reversed())
                              .findFirst()
                              .orElse(null);
    }
}
