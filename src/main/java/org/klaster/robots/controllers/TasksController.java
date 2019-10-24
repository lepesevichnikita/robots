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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("tasks");
        modelAndView.addObject("tasks", tasksRepository.findAll());
        modelAndView.addObject("newTask", new TaskDTO());
        modelAndView.addObject("aliveRobots", trackerService.getAliveRobots());
        return modelAndView;
    }

    @GetMapping("/task/{id}")
    public ModelAndView getById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Task> foundTask = tasksRepository.findById(id);
        if (foundTask.isPresent()) {
            modelAndView.addObject("task", foundTask.get());
            modelAndView.setViewName("task");
        } else {
            modelAndView.setViewName("redirect:/task/all");
        }
        return modelAndView;
    }

    @PostMapping("/task")
    public ModelAndView createTask(@ModelAttribute("newTask") TaskDTO newTaskDTO) {
        Task newTask = newTaskDTO.toTask();
        if (newTask.hasRobot()) {
            trackerService.addTaskToRobot(newTaskDTO.getRobot(), newTask);
        } else {
            trackerService.addGeneralTask(newTask);
        }
        return new ModelAndView("redirect:/task/" + newTask.getId());
    }
}
