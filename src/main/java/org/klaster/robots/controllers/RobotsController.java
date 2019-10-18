package org.klaster.robots.controllers;

import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.repositories.RobotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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
    public String getAll(Map<Object, Object> model) {
        model.put("robots", robotsRepository.findAll());
        return "robots";
    }

    @PostMapping("/robot")
    public ModelAndView createRobot() {
        trackerService.createNewIdleRobot();
        return new ModelAndView("redirect:/robot/all");
    }

}
