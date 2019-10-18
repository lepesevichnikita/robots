package org.klaster.robots.controllers;

import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.Task;
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

    @GetMapping("/task/all")
    public ModelAndView getAll(Map<Object, Object> model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tasks");
        modelAndView.addObject("tasks", tasksRepository.findAll());
        modelAndView.addObject("newTask", new Task());
        modelAndView.addObject("aliveIdleRobots", trackerService.getAliveIdleRobots());
        return modelAndView;
    }

    @PostMapping("/task")
    public ModelAndView createTask(@ModelAttribute("newTask") Task newTask, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/task/all");
        }
        trackerService.createGeneralTask(newTask);
        return new ModelAndView("redirect:/task/all");
    }

}
