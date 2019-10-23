package org.klaster.robots.controllers;

import org.klaster.robots.interfaces.TaskBuilder;
import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.models.dto.TaskDTO;
import org.klaster.robots.repositories.RobotsRepository;
import org.klaster.robots.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/17/19
 * @project tasks
 */
@Controller
public class TasksController {

    @Autowired
    @Qualifier("tasksRepository")
    TasksRepository tasksRepository;

    @Autowired
    TrackerService trackerService;

    @Autowired
    @Qualifier("robotsRepository")
    RobotsRepository robotsRepository;

    @Autowired
    TaskBuilder taskWithDefaultEmptyTitleBuilder;

    @GetMapping("/task/all")
    public ModelAndView getAll(Map<Object, Object> model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tasks");
        modelAndView.addObject("tasks", tasksRepository.findAll());
        modelAndView.addObject("newTask", new TaskDTO());
        modelAndView.addObject("allRobots", robotsRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/task")
    public ModelAndView createTask(@ModelAttribute("newTask") TaskDTO newTaskDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/task/all");
        }
        Task newTask = newTaskDTO.toTask();
        if (newTask.hasRobot()) {
            trackerService.addTaskToRobot(newTaskDTO.getRobot(), newTask);
        }
        else {
            trackerService.addGeneralTask(newTask);
        }
        return new ModelAndView("redirect:/task/all");
    }
}
