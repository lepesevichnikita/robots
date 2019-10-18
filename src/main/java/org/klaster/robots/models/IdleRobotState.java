package org.klaster.robots.models;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class IdleRobotState extends RobotState{

    public IdleRobotState(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "IDLE";
    }

    @Override
    public void executeCurrentTask() {
        changeState(new WorkingRobotState(getContext()));
        if (hasRobot()) {
            Robot robot = getRobot();
            if (robot.hasCurrentTask()) {
                robot.getCurrentTask().execute();
            }
        }
    }

    @Override
    public void setCurrentTask(Task newCurrentTask) {

    }
}
