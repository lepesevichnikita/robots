package org.klaster.robots.models;

import javax.persistence.MappedSuperclass;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@MappedSuperclass
public abstract class TaskState extends State {
    public TaskState(Context context) {
        super(context);
    }
}
