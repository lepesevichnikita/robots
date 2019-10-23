package org.klaster.robots.controllers;

import org.klaster.robots.interfaces.TrackerService;
import org.klaster.robots.repositories.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/17/19
 * @project robots
 */
@Controller
public class NotificationController {
    @Autowired
    @Qualifier("notificationsRepository")
    private NotificationsRepository notificationsRepository;

    @Autowired
    private TrackerService trackerService;

    @GetMapping("/")
    public ModelAndView all(Model model) {
        model.addAttribute("notifications", this.notificationsRepository.findAll());
        return new ModelAndView("notifications");
    }

}
