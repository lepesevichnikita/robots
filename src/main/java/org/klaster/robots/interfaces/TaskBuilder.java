package org.klaster.robots.interfaces;

import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public interface TaskBuilder {

    TaskBuilder setTitle(String title);
    TaskBuilder setDescription(String description);
    TaskBuilder setRobot(Robot robot);
    TaskBuilder setStatus(Task.Status status);
    TaskBuilder reset();

    Task getTask();
}
