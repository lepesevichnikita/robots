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
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "robot")
    private Set<Task> tasks;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ALIVE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Task getCurrentTask() {
        Task currentTask = null;
        if (tasks != null) {
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
        if (getCurrentTask() == null) {
            currentTask.setStatus(Task.Status.PROCESSING);
        }
    }

    public Task addTask(Task task) {
        task.setRobot(this);
        if (tasks == null) {
            setTasks(new HashSet<>());
        }
        tasks.add(task);
        return task;
    }

    public Boolean isIdle() {
        return getCurrentTask() == null;
    }

    public Boolean isWorking() {
        return !isIdle();
    }

    public void setTasks(Set<Task> tasks) {
        if (this.tasks == null) {
            this.tasks = tasks;
        } else {
            this.tasks.retainAll(tasks);
            this.tasks.addAll(tasks);
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean isDead() {
        return status == Status.DEAD;
    }

    public Boolean isAlive() {
        return status == Status.ALIVE;
    }

    public enum Status {
        ALIVE, DEAD
    }
}
