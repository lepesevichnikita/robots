package org.klaster.robots.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/15/19
 * @project robots
 */
@Entity
public class Robot extends Subscribable {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "robot")
    private Set<Task> tasks;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ALIVE;

    public Task getCurrentTask() {
        Task currentTask = null;
        if (hasTasks()) {
            currentTask =
                    StreamSupport.stream(tasks.spliterator(), false)
                                 .filter(t -> t.isProcessing())
                                 .findFirst()
                                 .orElse(null);
        }
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        addTask(currentTask);
        if (!hasCurrentTask()) {
            currentTask.setStatus(Task.Status.PROCESSING);
        }
    }

    public boolean hasCurrentTask() {
        return getCurrentTask() != null;
    }

    public Task addTask(Task task) {
        task.setRobot(this);
        createTasksHashSetIfNotExists();
        tasks.add(task);
        return task;
    }

    private void createTasksHashSetIfNotExists() {
        if (!hasTasks()) {
            setTasks(new HashSet<>());
        }
    }

    public Boolean isIdle() {
        return !hasCurrentTask();
    }

    public Boolean isWorking() {
        return !isIdle();
    }

    public void setTasks(Set<Task> tasks) {
        if (!hasTasks()) {
            this.tasks = tasks;
        } else {
            resetTasks(tasks);
        }
    }

    private void resetTasks(Set<Task> tasks) {
        this.tasks.retainAll(tasks);
        this.tasks.addAll(tasks);
    }

    private boolean hasTasks() {
        return this.tasks != null;
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

    private void notifyAboutStatusChange(Robot.Status oldStatus, Robot.Status newStatus) {
        NotificationAboutRobotStatusChange notificationAboutRobotStatusChange =
                new NotificationAboutRobotStatusChange();
        notificationAboutRobotStatusChange.setOldStatus(oldStatus);
        notificationAboutRobotStatusChange.setNewStatus(newStatus);
        addNotification(notificationAboutRobotStatusChange);
    }

    public Boolean isDead() {
        return status == Status.DEAD;
    }
    public Boolean isAlive() {
        return status == Status.ALIVE;
    }

    @Override
    public String getName() {
        return "Robot";
    }

    public enum Status {
        ALIVE, DEAD
    }
}
