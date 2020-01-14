package org.klaster.robots.models.contexts;

import org.klaster.robots.models.abstracts.RobotState;
import org.klaster.robots.models.abstracts.SubscribableContext;
import org.klaster.robots.models.states.DeadRobotState;
import org.klaster.robots.models.states.IdleRobotState;
import org.klaster.robots.models.states.WorkingRobotState;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/15/19
 * @project robots
 */
@Entity
public class Robot extends SubscribableContext {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "robot")
    private Set<Task> tasks;

    public Task getCurrentTask() {
        Task currentTask = null;
        if (hasTasks()) {
            currentTask =
                    tasks.stream()
                         .filter(Task::isCurrent)
                         .findFirst()
                         .orElse(null);
        }
        return currentTask;
    }

    public boolean hasCurrentTask() {
        return getCurrentTask() != null;
    }

    public void addTask(Task task) {
        createTasksHashSetIfNotExists();
        tasks.add(task);
    }

    private void createTasksHashSetIfNotExists() {
        if (!hasTasks()) {
            setTasks(new HashSet<>());
        }
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        if (!hasTasks()) {
            this.tasks = tasks;
        }
        else {
            resetTasks(tasks);
        }
    }

    private void resetTasks(Set<Task> tasks) {
        this.tasks.retainAll(tasks);
        this.tasks.addAll(tasks);
    }

    public boolean hasTasks() {
        return this.tasks != null;
    }

    public boolean isDead() {
        return isCurrentState(DeadRobotState.class);
    }

    public boolean isWorking() {
        return isCurrentState(WorkingRobotState.class);
    }

    public boolean isIdle() {
        return isCurrentState(IdleRobotState.class);
    }

    public void addTaskAndSetAsCurrentIfPossible(Task newTask) {
        ((RobotState) getCurrentState()).addTaskAndSetAsCurrentIfPossible(newTask);
    }

    public void startCurrentTask() {
        ((RobotState) getCurrentState()).startCurrentTask();
    }

    @Override
    public String getName() {
        return "Robot";
    }

    @Override
    public RobotState getDefaultState() {
        return new IdleRobotState();
    }
}
