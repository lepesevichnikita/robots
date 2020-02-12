package org.klaster.model.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

import java.util.Set;
import org.klaster.builder.DefaultJobBuilder;
import org.klaster.builder.DefaultLoginInfoBuilder;
import org.klaster.builder.DefaultUserBuilder;
import org.klaster.builder.JobBuilder;
import org.klaster.builder.LoginInfoBuilder;
import org.klaster.builder.UserBuilder;
import org.klaster.model.context.Job;
import org.klaster.model.context.User;
import org.klaster.model.state.user.VerifiedUserState;
import org.klaster.service.JobsRecommendationService;
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
 * FreelancerProfileTest
 *
 * @author Nikita Lepesevich
 */

public class FreelancerProfileTest {

  private static final long RECOMMENDED_JOBS_COUNT = 5;
  private FreelancerProfile freelancerProfile;
  private JobBuilder defaultJobBuilder;

  @BeforeClass
  public void createSkills() {
    defaultJobBuilder = new DefaultJobBuilder();
    SkillsCache.getInstance()
               .addAll("first", "second", "third", "fourth", "sixth", "seventh", "eighth", "ninth", "tenth")
               .stream()
               .map(skill -> SkillsCache.getInstance()
                                        .getAll(skill.getName()))
               .forEach(skillSet -> JobsRecommendationService.getInstance()
                                                             .add(defaultJobBuilder.setSkills(skillSet)
                                                                                   .build()));
  }

  @BeforeMethod
  public void initialize() {
    final UserBuilder defaultUserBuilder = new DefaultUserBuilder();
    final LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    final User user = defaultUserBuilder.setLoginInfo(defaultLoginInfoBuilder.build())
                                        .build();
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createFreelancerProfile();
    freelancerProfile = user.getFreelancerProfile();
    defaultLoginInfoBuilder.reset();
  }

  @Test
  public void findsJobWithSameSkills() {
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getAll("first", "fourth");
    Job newJob = defaultJobBuilder.setSkills(skills)
                                  .build();
    JobsRecommendationService.getInstance()
                             .add(newJob);
    freelancerProfile.setSkills(skills);
    assertThat(freelancerProfile.getRecommendedJobs(RECOMMENDED_JOBS_COUNT)
                                .get(0), equalTo(newJob));
  }

  @Test
  public void findsJobWithSameSkillsAsFirst() {
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getAll("first", "fourth");
    Job newJob = defaultJobBuilder.setSkills(skills)
                                  .build();
    JobsRecommendationService.getInstance()
                             .add(newJob);
    freelancerProfile.setSkills(skills);
    final int expectedRecommendedJobsCount = 4;
    assertThat(freelancerProfile.getRecommendedJobs(RECOMMENDED_JOBS_COUNT)
                                .size(), equalTo(expectedRecommendedJobsCount));
  }

  @Test
  public void returnsEmptyCollectionIfThereIsNoJobsWithSameSkills() {
    Set<Skill> skills = SkillsCache.getInstance()
                                   .addAll("eleventh", "twelfth");
    freelancerProfile.setSkills(skills);
    assertThat(freelancerProfile.getRecommendedJobs(RECOMMENDED_JOBS_COUNT), empty());
  }
}