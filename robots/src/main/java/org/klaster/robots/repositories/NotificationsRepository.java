package org.klaster.robots.repositories;

import org.klaster.robots.models.abstracts.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/17/19
 * @project robots
 */
@Component("notificationsRepository")
public interface NotificationsRepository extends JpaRepository<Notification, Long> {
}
