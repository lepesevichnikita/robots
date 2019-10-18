package org.klaster.robots.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Entity
public class NotificationAboutRobotStatusChange extends Notification {

    @Enumerated(EnumType.ORDINAL)
    private Robot.Status oldStatus;

    @Enumerated(EnumType.ORDINAL)
    private Robot.Status newStatus;

    @Override
    public String getMessage() {
        return "Status changed from " + oldStatus.name() + " to " + newStatus.name();
    }

    public Robot.Status getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Robot.Status oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Robot.Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Robot.Status newStatus) {
        this.newStatus = newStatus;
    }

}
