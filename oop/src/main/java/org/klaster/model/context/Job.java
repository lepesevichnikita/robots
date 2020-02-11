package org.klaster.model.context;

import java.time.LocalDateTime;
import java.util.Set;
import org.klaster.model.entity.EmployerProfile;
import org.klaster.model.entity.JobSkill;
import org.klaster.model.state.job.JobState;
import org.klaster.model.state.job.PublishedJobState;

/**
 * Job
 *
 * @author Nikita Lepesevich
 */

public class Job extends AbstractContext<JobState> {

  private String description;
  private final EmployerProfile employerProfile;
  private Set<JobSkill> skills;
  private LocalDateTime endDateTime;

  public Job(String description, EmployerProfile employerProfile) {
    this.description = description;
    this.employerProfile = employerProfile;
    this.setCurrentState(new PublishedJobState(this));
  }

  public Set<JobSkill> getSkills() {
    return skills;
  }

  public void setSkills(Set<JobSkill> skills) {
    this.skills = skills;
  }

  public EmployerProfile getEmployerProfile() {
    return employerProfile;
  }

  public String getDescription() {
    return description;
  }


  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(LocalDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
