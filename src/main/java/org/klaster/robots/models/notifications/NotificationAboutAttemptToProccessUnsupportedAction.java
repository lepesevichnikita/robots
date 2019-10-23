package org.klaster.robots.models.notifications;

import org.klaster.robots.models.abstracts.Notification;

import javax.persistence.Column;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/21/19
 * @project robots
 */
public class NotificationAboutAttemptToProccessUnsupportedAction extends Notification {
    @Column
    private String action;

    @Override
    public String getMessage() {
        return "Failed attemt to " + getAction();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
