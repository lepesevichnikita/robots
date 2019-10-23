package org.klaster.robots.models.abstracts;

import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;

import javax.persistence.MappedSuperclass;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@MappedSuperclass
public abstract class TaskState extends State {
    public TaskState(Task task) {
        super(task);
    }

    public TaskState() {
        super();
    }


    public Task getTask() {
        return hasTask() ? (Task) getContext() : null;
    }

    public boolean hasTask() {
        return hasContext();
    }

    public abstract void start();

    public abstract void changeRobot(Robot newRobot);
}
