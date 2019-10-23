package org.klaster.robots.models.dto;

import org.klaster.robots.models.contexts.Robot;
import org.klaster.robots.models.contexts.Task;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/22/19
 * @project robots
 */
public class TaskDTO {
    private Task task;

    public TaskDTO() {
        task = new Task();
    }

    public static TaskDTO fromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setRobot(task.getRobot());
        return taskDTO;
    }

    public Task toTask() {
        return task;
    }

    public String getTitle() {
        return task.getTitle();
    }

    public void setTitle(String title) {
        task.setTitle(title);
    }

    public String getDescription() {
        return task.getDescription();
    }

    public void setDescription(String description) {
        task.setDescription(description);
    }

    public Robot getRobot() {
        return task.getRobot();
    }

    public void setRobot(Robot robot) {
        task.setRobot(robot);
    }
}
