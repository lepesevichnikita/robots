package org.klaster.robots.repositories;

import org.klaster.robots.models.contexts.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/15/19
 * @project robots
 */

@Component("robotsRepository")
public interface RobotsRepository extends JpaRepository<Robot, Long> {
}
