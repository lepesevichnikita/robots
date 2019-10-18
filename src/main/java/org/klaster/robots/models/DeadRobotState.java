package org.klaster.robots.models;

import javax.persistence.Entity;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
public class DeadRobotState extends RobotState{
    public DeadRobotState(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "DEAD";
    }

    @Override
    public void changeState(State newState) {
    }

    @Override
    public void executeCurrentTask() {
    }

    @Override
    public void setCurrentTask(Task newCurrentTask) {

    }
}
