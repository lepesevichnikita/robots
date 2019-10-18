package org.klaster.robots.models;

import javax.persistence.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */
@Entity
public class Task extends Subscribable {

    private static final String SUICIDE_TITLE = "suicide";

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.WAITING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="robot_id", referencedColumnName = "id")
    private Robot robot;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        if (status != newStatus) {
            notifyAboutStatusChange(status, newStatus);
        }
        status = newStatus;
    }

    private void notifyAboutStatusChange(Status oldStatus, Status newStatus) {
        NotificationAboutTaskStatusChange notificationAboutTaskStatusChange = new NotificationAboutTaskStatusChange();
        notificationAboutTaskStatusChange.setOldStatus(oldStatus);
        notificationAboutTaskStatusChange.setNewStatus(newStatus);
        addNotification(notificationAboutTaskStatusChange);
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void execute() {
        if (isProcessing()) {
            if (hasTitle() && title.equals(SUICIDE_TITLE)) {
                robot.setStatus(Robot.Status.DEAD);
                setStatus(Task.Status.COMPLETED);
            }
        }
    }

    public boolean hasTitle() {
        return !(title == null || title.isEmpty());
    }

    public boolean hasDescription() {
        return !(description == null || description.isEmpty());
    }

    @Override
    public String getName() {
        return "Task";
    }

    public enum Status {
        PROCESSING, COMPLETED, WAITING
    }

    public Boolean isGeneral() {
        return robot == null;
    }
    public Boolean isWaiting() { return status == Status.WAITING; }
    public Boolean isCompleted() { return status == Status.COMPLETED; }
    public Boolean isProcessing() { return status == Status.PROCESSING; }
}
