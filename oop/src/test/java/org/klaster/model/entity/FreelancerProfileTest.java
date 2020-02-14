package org.klaster.model.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.klaster.builder.Builder;
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

  private static final int RECOMMENDED_JOBS_COUNT = 5;
  private FreelancerProfile freelancerProfile;
  private JobBuilder defaultJobBuilder;
  private JobsRecommendationService jobsRecommendationService = new JobsRecommendationService();

  @BeforeClass
  public void createSkills() {
    defaultJobBuilder = new DefaultJobBuilder();
    jobsRecommendationService = new JobsRecommendationService();
    List<String> skillNames = Arrays.asList("first", "second", "third", "fourth", "sixth", "seventh", "eighth", "ninth", "tenth");
    SkillsCache.getInstance()
               .getOrCreateByNames(skillNames)
               .stream()
               .map(Collections::singleton)
               .map(defaultJobBuilder::setSkills)
               .map(Builder::build)
               .forEach(jobsRecommendationService::add);
    defaultJobBuilder.reset();
  }

  @BeforeMethod
  public void initialize() {
    UserBuilder defaultUserBuilder = new DefaultUserBuilder();
    LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    User user = defaultUserBuilder.setLoginInfo(defaultLoginInfoBuilder.build())
                                        .build();
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createFreelancerProfile();
    freelancerProfile = user.getFreelancerProfile();
    defaultLoginInfoBuilder.reset();
  }

  @Test
  public void findsJobWithSameSkills() {
    List<String> skillNames = Arrays.asList("first", "fourth");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getByNames(skillNames);
    Job newJob = defaultJobBuilder.setSkills(skills)
                                  .build();
    jobsRecommendationService.add(newJob);
    freelancerProfile.setSkills(skills);
    assertThat(freelancerProfile.getRecommendedJobs(jobsRecommendationService, RECOMMENDED_JOBS_COUNT)
                                .get(0), equalTo(newJob));
  }

  @Test
  public void findsJobWithSameSkillsAsFirst() {
    List<String> skillNames = Arrays.asList("first", "fourth");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getByNames(skillNames);
    Job newJob = defaultJobBuilder.setSkills(skills)
                                  .build();
    jobsRecommendationService.add(newJob);
    freelancerProfile.setSkills(skills);
    final int expectedRecommendedJobsCount = 4;
    assertThat(freelancerProfile.getRecommendedJobs(jobsRecommendationService, RECOMMENDED_JOBS_COUNT)
                                .size(), equalTo(expectedRecommendedJobsCount));
  }

  @Test
  public void findsLimitedCountOfJobs() {
    List<String> skillNames = Arrays.asList("first", "fourth", "second", "third", "five");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getByNames(skillNames);
    Job newJob = defaultJobBuilder.setSkills(skills)
                                  .build();
    jobsRecommendationService.add(newJob);
    freelancerProfile.setSkills(skills);
    assertThat(freelancerProfile.getRecommendedJobs(jobsRecommendationService, RECOMMENDED_JOBS_COUNT)
                                .size(), equalTo(RECOMMENDED_JOBS_COUNT));
  }

  @Test
  public void returnsEmptyCollectionIfThereIsNoJobsWithSameSkills() {
    List<String> skillNames = Arrays.asList("eleventh", "twelfth");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getOrCreateByNames(skillNames);
    freelancerProfile.setSkills(skills);
    assertThat(freelancerProfile.getRecommendedJobs(jobsRecommendationService, RECOMMENDED_JOBS_COUNT), empty());
  }
}