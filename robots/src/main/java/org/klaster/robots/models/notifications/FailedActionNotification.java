package org.klaster.robots.models.notifications;

import org.klaster.robots.models.abstracts.Notification;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/21/19
 * @project robots
 */
@Entity
public class FailedActionNotification extends Notification {
    @Column
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getMessage() {
        return "Failed attempt to " + getAction();
    }

}
