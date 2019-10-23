package org.klaster.robots.models.contexts;

import org.klaster.robots.models.abstracts.Context;
import org.klaster.robots.models.abstracts.TaskState;
import org.klaster.robots.models.states.CompletedTaskState;
import org.klaster.robots.models.states.DeadRobotState;
import org.klaster.robots.models.states.ProcessingTaskState;
import org.klaster.robots.models.states.WaitingTaskState;

import javax.persistence.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */
@Entity
public class Task extends Context {

    private static final String SUICIDE_TITLE = "suicide";

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "robot_id", referencedColumnName = "id")
    private Robot robot;

    @Column
    private boolean isCurrent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void start() {
        ((TaskState) getCurrentState()).start();
    }

    public boolean execute() {
        boolean executed = false;
        if (hasTitle() && title.equals(SUICIDE_TITLE)) {
            robot.changeCurrentState(new DeadRobotState());
            executed = true;
        }
        return executed;
    }

    public boolean hasTitle() {
        return !(title == null || title.isEmpty());
    }

    public boolean hasDescription() {
        return !(description == null || description.isEmpty());
    }

    public void changeRobot(Robot newRobot) {
        ((TaskState) getCurrentState()).changeRobot(newRobot);
    }

    public Boolean hasRobot() {
        return robot != null;
    }

    public Boolean isGeneral() {
        return !hasRobot();
    }

    public Boolean isWaiting() {
        return isCurrentState(WaitingTaskState.class);
    }

    public Boolean isCompleted() {
        return isCurrentState(CompletedTaskState.class);
    }

    public Boolean isProcessing() {
        return isCurrentState(ProcessingTaskState.class);
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    @Override
    public String getName() {
        return "Task";
    }

    @Override
    public TaskState getDefaultState() {
        return new WaitingTaskState();
    }

}
