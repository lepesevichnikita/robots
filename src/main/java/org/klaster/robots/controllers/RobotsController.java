package org.klaster.robots.controllers;

import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;
import org.klaster.robots.repositories.RobotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
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
    public ModelAndView getAll(Model model) {
        model.addAttribute("robots", robotsRepository.findAll());
        return new ModelAndView("robots");
    }

    @GetMapping("/robot/{id}")
    public ModelAndView getById(Model model, @PathVariable("id") Long id) {
        Robot robot = robotsRepository.findById(id).get();
        model.addAttribute("robot", robot);
        model.addAttribute("tasks",
                           robot.getTasks()
                                .stream()
                                .sorted(Comparator.comparing(Task::getCreatedAt))
                                .collect(Collectors.toList()));
        return new ModelAndView("robot");
    }

    @PostMapping("/robot")
    public ModelAndView createRobot() {
        trackerService.createNewIdleRobot();
        return new ModelAndView("redirect:/robot/all");
    }

}
