package org.klaster.model.context;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.klaster.builder.Builder;
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

  private static final int RECOMMENDED_FREELANCER_PROFILES_NUMBER = 5;
  private Job job;
  private FreelancerProfileBuilder defaultFreelancerProfileBuilder;
  private FreelancersRecommendationService freelancersRecommendationService;

  @BeforeClass

  public void createSkills() {
    defaultFreelancerProfileBuilder = new DefaultFreelancerProfileBuilder();
    freelancersRecommendationService = new FreelancersRecommendationService();
    List<String> skillNames = Arrays.asList("first", "second", "third", "fourth", "sixth", "seventh", "eighth", "ninth", "tenth");
    SkillsCache.getInstance()
               .getOrCreateByNames(skillNames)
               .stream()
               .map(Collections::singleton)
               .map(defaultFreelancerProfileBuilder::setSkills)
               .map(Builder::build)
               .forEach(freelancersRecommendationService::add);
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
    List<String> skillNames = Arrays.asList("first", "fourth");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getByNames(skillNames);
    FreelancerProfile newFreelancerProfile = defaultFreelancerProfileBuilder.setSkills(skills)
                                                                            .build();
    freelancersRecommendationService.add(newFreelancerProfile);
    job.setSkills(skills);
    assertThat(job.getRecommendedFreelancerProfiles(freelancersRecommendationService, RECOMMENDED_FREELANCER_PROFILES_NUMBER)
                  .get(0), equalTo(newFreelancerProfile));
  }

  @Test
  public void findsJobWithSameSkillsAsFirst() {
    List<String> skillNames = Arrays.asList("first", "fourth");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getByNames(skillNames);
    FreelancerProfile newFreelancerProfile = defaultFreelancerProfileBuilder.setSkills(skills)
                                                                            .build();
    freelancersRecommendationService.add(newFreelancerProfile);
    job.setSkills(skills);
    final int expectedRecommendedFreelancersCount = 4;
    assertThat(job.getRecommendedFreelancerProfiles(freelancersRecommendationService, RECOMMENDED_FREELANCER_PROFILES_NUMBER)
                  .size(), equalTo(expectedRecommendedFreelancersCount));
  }

  @Test
  public void findsLimitedCountOfJobs() {
    List<String> skillNames = Arrays.asList("first", "fourth", "second", "third", "five");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getByNames(skillNames);
    FreelancerProfile newFreelancerProfile = defaultFreelancerProfileBuilder.setSkills(skills)
                                                                            .build();
    freelancersRecommendationService.add(newFreelancerProfile);
    job.setSkills(skills);
    assertThat(job.getRecommendedFreelancerProfiles(freelancersRecommendationService, RECOMMENDED_FREELANCER_PROFILES_NUMBER)
                  .size(), equalTo(RECOMMENDED_FREELANCER_PROFILES_NUMBER));
  }

  @Test
  public void returnsEmptyCollectionIfThereIsNoJobsWithSameSkills() {
    List<String> skillNames = Arrays.asList("eleventh", "twelfth");
    Set<Skill> skills = SkillsCache.getInstance()
                                   .getOrCreateByNames(skillNames);
    job.setSkills(skills);
    assertThat(job.getRecommendedFreelancerProfiles(freelancersRecommendationService, RECOMMENDED_FREELANCER_PROFILES_NUMBER), empty());
  }

}