package org.klaster.robots.builders;

import org.springframework.stereotype.Component;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Component("robotWithDefaultCurrentTaskBuilder")
public class RobotWithDefaultCurrentTaskBuilder extends AbstractRobotBuilder {

    public RobotWithDefaultCurrentTaskBuilder() { super(); }
}
