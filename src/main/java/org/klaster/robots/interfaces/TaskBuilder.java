package org.klaster.robots.interfaces;

import org.klaster.robots.models.abstracts.State;
import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public interface TaskBuilder {

    TaskBuilder setTitle(String title);

    TaskBuilder setDescription(String description);

    TaskBuilder setRobot(Robot robot);

    TaskBuilder setCurrentState(State currentState);

    TaskBuilder reset();

    Task getTask();
}
