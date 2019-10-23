package org.klaster.robots.builders;

import org.springframework.stereotype.Component;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Component("taskWithDefaultEmptyTitleBuilder")
public class TaskWithDefaultEmptyTitleBuilder extends AbstractTaskBuilder {

    public TaskWithDefaultEmptyTitleBuilder() { super(); }

}
