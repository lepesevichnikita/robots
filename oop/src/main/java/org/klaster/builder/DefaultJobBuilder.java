package org.klaster.builder;/*
 * practice
 *
 * 11.02.2020
 *
 */

import java.util.LinkedHashSet;
import java.util.Set;
import org.klaster.model.context.Job;
import org.klaster.model.context.User;
import org.klaster.model.entity.EmployerProfile;
import org.klaster.model.entity.JobSkill;

/**
 * DefaultJobBuilder builds new job with default employer as a new verified user
 *
 * @author Nikita Lepesevich
 */

public class DefaultJobBuilder implements JobBuilder {

  private EmployerProfile employerProfile;
  private Set<JobSkill> skills;
  private String description;

  public DefaultJobBuilder() {
    reset();
  }

  @Override
  public JobBuilder setEmployerProfile(EmployerProfile employerProfile) {
    this.employerProfile = employerProfile;
    return this;
  }

  @Override
  public JobBuilder setSkills(Set<JobSkill> skills) {
    this.skills = skills;
    return this;
  }

  @Override
  public JobBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public void reset() {
    User user = new DefaultUserBuilder().build();
    user.getCurrentState()
        .verifyUser();
    user.getCurrentState()
        .createEmployerProfile();
    employerProfile = user.getEmployerProfile();
    skills = new LinkedHashSet<>();
    description = "";
  }

  @Override
  public Job build() {
    Job job = new Job(description, employerProfile);
    job.setSkills(skills);
    return job;
  }
}
