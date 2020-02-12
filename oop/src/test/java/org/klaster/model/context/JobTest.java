package org.klaster.model.context;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Set;
import org.klaster.builder.DefaultFreelancerProfileBuilder;
import org.klaster.builder.DefaultJobBuilder;
import org.klaster.builder.FreelancerProfileBuilder;
import org.klaster.builder.JobBuilder;
import org.klaster.model.entity.FreelancerProfile;
import org.klaster.model.entity.Skill;
import org.klaster.service.FreelancersRecommendationService;
import org.klaster.service.SkillsCache;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
 * practice
 *
 * 12.02.2020
 *
 */

/**
 * JobTest
 *
 * @author Nikita Lepesevich
 */

public class JobTest {

  private static final long RECOMMENDED_JOBS_COUNT = 5;
  private Job job;
  private FreelancerProfileBuilder defaultFreelancerProfileBuilder;

  @BeforeClass

  public void createSkills() {
    defaultFreelancerProfileBuilder = new DefaultFreelancerProfileBuilder();
    SkillsCache.getInstance()
               .addAll("first", "second", "third", "fourth", "sixth", "seventh", "eighth", "ninth", "tenth")
               .stream()
               .map(skill -> SkillsCache.getInstance()
                                        .getAll(skill.getName()))
               .forEach(skillSet -> FreelancersRecommendationService.getInstance()
                                                                    .add(defaultFreelancerProfileBuilder.setSkills(skillSet)
                                                                                                        .build())
               );
    defaultFreelancerProfileBuilder.reset();
  }

  @BeforeMethod
  public void initialize() {
    JobBuilder defaultJobBuilder = new DefaultJobBuilder();
    job = defaultJobBuilder.build();
    defaultFreelancerProfileBuilder.reset();
  }

  @Test
  public void findsJobWithSameSkills() {
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getAll("first", "fourth");
    FreelancerProfile newFreelancerProfile = defaultFreelancerProfileBuilder.setSkills(skills)
                                                                            .build();
    FreelancersRecommendationService.getInstance()
                                    .add(newFreelancerProfile);
    job.setSkills(skills);
    List<FreelancerProfile> recommendedFreelancerProfiles = job.getRecommendedFreelancerProfiles(RECOMMENDED_JOBS_COUNT);
    assertThat(job.getRecommendedFreelancerProfiles(RECOMMENDED_JOBS_COUNT)
                  .get(0), equalTo(newFreelancerProfile));
  }

  @Test
  public void findsJobWithSameSkillsAsFirst() {
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getAll("first", "fourth");
    FreelancerProfile newFreelancerProfile = defaultFreelancerProfileBuilder.setSkills(skills)
                                                                            .build();
    FreelancersRecommendationService.getInstance()
                                    .add(newFreelancerProfile);
    job.setSkills(skills);
    final int expectedRecommendedJobsCount = 4;
    assertThat(job.getRecommendedFreelancerProfiles(RECOMMENDED_JOBS_COUNT)
                  .size(), equalTo(expectedRecommendedJobsCount));
  }

  @Test
  public void returnsEmptyCollectionIfThereIsNoJobsWithSameSkills() {
    Set<Skill> skills = SkillsCache.getInstance()
                                   .addAll("eleventh", "twelfth");
    job.setSkills(skills);
    assertThat(job.getRecommendedFreelancerProfiles(RECOMMENDED_JOBS_COUNT), empty());
  }

}