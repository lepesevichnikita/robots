package org.klaster.service;

/*
 * practice
 *
 * 12.02.2020
 *
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.klaster.model.context.Job;
import org.klaster.model.entity.FreelancerProfile;
import org.klaster.model.entity.Skill;

/**
 * JobsRecommendationService
 *
 * @author Nikita Lepesevich
 */

public class JobsRecommendationService extends AbstractRecommendationService<FreelancerProfile, Job> {

  private FreelancerProfile currentFreelancer;

  public JobsRecommendationService() {
    super();
  }

  @Override
  public List<Job> getRecommended(FreelancerProfile source, long limit) {
    this.currentFreelancer = source;
    return getAll().stream()
                   .filter(job -> getSkillMatchesWithCurrentFreelancer(job) > 0)
                   .sorted(Comparator.comparingLong(this::getSkillMatchesWithCurrentFreelancer)
                                     .reversed())
                   .limit(limit)
                   .collect(Collectors.toList());
  }

  private long getSkillMatchesWithCurrentFreelancer(Job job) {
    List<Skill> currentFreelancerSkills = new LinkedList<>(currentFreelancer.getSkills());
    return job.getSkills()
              .stream()
              .filter(currentFreelancerSkills::contains)
              .count();
  }

}
