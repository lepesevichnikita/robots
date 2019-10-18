package org.klaster.robots.models;

import javax.persistence.MappedSuperclass;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@MappedSuperclass
public abstract class RobotState extends State {
    public RobotState(Context context) {
        super(context);
    }

    public abstract void executeCurrentTask();
    public abstract void setCurrentTask(Task newCurrentTask);

    public Robot getRobot() {
        return hasContext() ? (Robot)getContext() : null;
    }

    public boolean hasRobot() {
        return getRobot() != null;
    }
}
