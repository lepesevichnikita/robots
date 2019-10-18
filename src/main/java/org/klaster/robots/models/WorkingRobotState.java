package org.klaster.robots.models;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class WorkingRobotState extends RobotState{
    public WorkingRobotState(Context context) {
        super(context);
    }

    @Override
    public void executeCurrentTask() {

    }

    @Override
    public void setCurrentTask(Task newCurrentTask) {

    }

    @Override
    public String getName() {
        return "WORKING";
    }
}
