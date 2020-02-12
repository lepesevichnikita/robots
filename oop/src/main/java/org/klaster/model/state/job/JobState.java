package org.klaster.model.state.job;

import java.time.LocalDateTime;
import java.util.Set;
import org.klaster.model.context.Job;
import org.klaster.model.entity.Skill;
import org.klaster.model.state.general.State;

/**
 * JobState
 *
 * @author Nikita Lepesevich
 */

public interface JobState extends State<Job> {

  boolean isOverDeadlines();

  void updateJob(String description, Set<Skill> skills, LocalDateTime endDateTime);
}

