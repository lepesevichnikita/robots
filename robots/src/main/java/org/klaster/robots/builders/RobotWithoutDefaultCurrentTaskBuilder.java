package org.klaster.robots.builders;

import org.springframework.stereotype.Component;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Component("robotWithoutDefaultCurrentTaskBuilder")
public class RobotWithoutDefaultCurrentTaskBuilder extends AbstractRobotBuilder {
    public RobotWithoutDefaultCurrentTaskBuilder() { super(); }

    @Override
    protected void resetCurrentTask() {
        setCurrentTask(null);
    }
}
