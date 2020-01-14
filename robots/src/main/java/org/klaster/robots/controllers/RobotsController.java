package org.klaster.robots.controllers;

import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.repositories.RobotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/17/19
 * @project robots
 */
@Controller
public class RobotsController {

    @Autowired
    @Qualifier("robotsRepository")
    RobotsRepository robotsRepository;

    @Autowired
    TrackerService trackerService;

    @GetMapping("/robot/all")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("robots");
        modelAndView.addObject("robots", robotsRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/robot/{id}")
    public ModelAndView getById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Robot> foundRobot = robotsRepository.findById(id);
        if (foundRobot.isPresent()) {
            modelAndView.addObject("robot", foundRobot.get());
            modelAndView.addObject("tasks",
                                   foundRobot.get().getTasks()
                                             .stream()
                                             .sorted(Comparator.comparing(Task::getCreatedAt))
                                             .collect(Collectors.toList()));
            modelAndView.setViewName("robot");
        }
        else {
            modelAndView.setViewName("redirect:/robot/all");
        }
        return modelAndView;
    }

    @PostMapping("/robot")
    public ModelAndView createRobot() {
        trackerService.createNewIdleRobot();
        return new ModelAndView("redirect:/robot/all");
    }

}
