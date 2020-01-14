package org.klaster.robots.models.notifications;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/21/19
 * @project robots
 */
public class Action {
    public static final Action ADD_TASK = new Action("add task");
    public static final Action CHANGE_ROBOT = new Action("change robot");
    public static final Action START = new Action("start");
    public static final Action START_CURRENT_TASK = new Action("start current task");

    private String name;

    public Action(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
