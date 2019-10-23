package org.klaster.robots.models.notifications;

import org.klaster.robots.models.abstracts.Notification;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Entity
public class CreatedNotification extends Notification {

    @Override
    public String getMessage() {
        return getSubscribableName() + " was created";
    }
}
