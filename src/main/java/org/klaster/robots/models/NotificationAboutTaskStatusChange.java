package org.klaster.robots.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Entity
public class NotificationAboutTaskStatusChange extends Notification {

    @Enumerated(EnumType.ORDINAL)
    private Task.Status oldStatus;

    @Enumerated(EnumType.ORDINAL)
    private Task.Status newStatus;

    @Override
    public String getMessage() {
        return "Status changed from " + oldStatus.name() + " to " + newStatus.name();
    }

    public void setOldStatus(Task.Status oldStatus) {
        this.oldStatus = oldStatus;
    }
    public void setNewStatus(Task.Status newStatus) {
        this.newStatus = newStatus;
    }
}
