package org.klaster.model.entity;

import java.util.List;
import java.util.Set;
import org.klaster.model.context.Job;
import org.klaster.model.context.User;
import org.klaster.service.JobsRecommendationService;

/**
 * FreelancerProfile
 *
 * @author Nikita Lepesevich
 */

public class FreelancerProfile extends AbstractProfile {

  private Set<Skill> skills;

  public FreelancerProfile(User owner) {
    super(owner);
  }

  public Set<Skill> getSkills() {
    return skills;
  }

  public void setSkills(Set<Skill> skills) {
    this.skills = skills;
  }

  public List<Job> getRecommendedJobs(JobsRecommendationService jobsRecommendationService, long limit) {
    return jobsRecommendationService.getRecommended(this, limit);
  }

}
