package org.klaster.model.state.job;

import java.time.LocalDateTime;
import java.util.Set;
import org.klaster.model.context.Job;
import org.klaster.model.entity.JobSkill;
import org.klaster.model.state.general.AbstractState;

/**
 * AbstractJobState
 *
 * @author Nikita Lepesevich
 */

public abstract class AbstractJobState extends AbstractState<Job> implements JobState {

  public AbstractJobState(Job context) {
    super(context);
  }

  @Override
  public void deleteJob() {
    final String message = String.format("Failed delete to start job #%s", getContext());
    logger.warning(message);
  }

  @Override
  public void finishJob() {
    final String message = String.format("Failed finish to start job #%s", getContext());
    logger.warning(message);
  }

  @Override
  public void startJob() {
    final String message = String.format("Failed attempt to start job #%s", getContext());
    logger.warning(message);
  }

  @Override
  public boolean isOverDeadlines() {
    return false;
  }

  @Override
  public void updateJob(String description, Set<JobSkill> skills, LocalDateTime endDateTime) {
    final String message = String.format("Failed attempt to update job #%s%nDescription: %s%nSkills: %s%nEndDateTime: %s", getContext(), description, skills, endDateTime);
    logger.warning(message);
  }
}
