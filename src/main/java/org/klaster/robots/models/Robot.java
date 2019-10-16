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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Task getCurrentTask() {
        Task result = null;
        if (tasks != null) {
            result =
                    StreamSupport.stream(tasks.spliterator(), false)
                                 .filter(t -> t.isProcessing())
                                 .findFirst()
                                 .orElse(null);
        }
        return result;
    }

    public void setCurrentTask(Task currentTask) {
        assert (getCurrentTask() == null);
        currentTask.setRobot(this);
        currentTask.setStatus(Task.Status.PROCESSING);
        if (tasks != null) {
            tasks.add(currentTask);
        }
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
}
