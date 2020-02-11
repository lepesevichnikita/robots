package org.klaster.model.state.job;

import java.time.LocalDateTime;
import java.util.Set;
import org.klaster.model.context.Job;
import org.klaster.model.entity.JobSkill;

/**
 * PublishedJobState
 *
 * @author Nikita Lepesevich
 */

public class PublishedJobState extends AbstractJobState {

  public PublishedJobState(Job context) {
    super(context);
  }

  @Override
  public void deleteJob() {
    getContext().setCurrentState(new DeletedJobState(getContext()));
    final String message = String.format("Job #%s was deleted", getContext());
    logger.info(message);
  }

  @Override
  public void startJob() {
    getContext().setCurrentState(new StartedJobState(getContext()));
    final String message = String.format("Job #%s was started", getContext());
    logger.info(message);
  }

  @Override
  public void updateJob(String description, Set<JobSkill> skills, LocalDateTime endDateTime) {
    getContext().setEndDateTime(endDateTime);
    getContext().setDescription(description);
    getContext().setSkills(skills);
    final String message = String.format("Job #%s was updated%nDescription: %s%nSkills: %s%nEnd datetime: %s", getContext(), description, skills, endDateTime);
    logger.info(message);
  }

}
