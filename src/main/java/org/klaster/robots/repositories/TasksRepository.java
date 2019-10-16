package org.klaster.robots.repositories;

import org.klaster.robots.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/15/19
 * @project robots
 */
@Component("tasksRepository")
public interface TasksRepository extends JpaRepository<Task, Long> {
}
