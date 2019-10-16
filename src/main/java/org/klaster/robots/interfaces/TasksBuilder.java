package org.klaster.robots.interfaces;

import org.klaster.robots.models.Robot;
import org.klaster.robots.models.Task;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public interface TasksBuilder {
    TasksBuilder withTitle(String title);

    TasksBuilder withDescription(String description);

    TasksBuilder withRobot(Robot robot);

    TasksBuilder withStatus(Task.Status status);

    TasksBuilder reset();

    Task getTask();
}
