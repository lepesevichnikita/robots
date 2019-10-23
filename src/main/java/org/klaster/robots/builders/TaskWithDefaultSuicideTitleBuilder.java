package org.klaster.robots.builders;

import org.springframework.stereotype.Component;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Component("taskWithDefaultSuicideTitleBuilder")
public class TaskWithDefaultSuicideTitleBuilder extends AbstractTaskBuilder {
    @Override
    protected void resetTitle() {
        setTitle("suicide");
    }
}
