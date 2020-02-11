package org.klaster.model.state.job;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import org.klaster.builder.DefaultJobBuilder;
import org.klaster.builder.JobBuilder;
import org.klaster.model.context.Job;
import org.klaster.model.entity.JobSkill;
import org.klaster.model.entity.Skill;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/*
 * practice
 *
 * 11.02.2020
 *
 */

/**
 * DeletedJobStateTest
 *
 * @author Nikita Lepesevich
 */

public class DeletedJobStateTest {

  private Job job;

  @BeforeMethod
  public void initialize() {
    JobBuilder defaultJobBuilder = new DefaultJobBuilder();
    job = defaultJobBuilder.build();
    job.getCurrentState()
       .deleteJob();
  }

  @Test
  public void cantFinishJob() {
    job.getCurrentState()
       .finishJob();
    assertThat(job.getCurrentState(), isA(DeletedJobState.class));
  }

  @Test
  public void cantStartJob() {
    job.getCurrentState()
       .startJob();
    assertThat(job.getCurrentState(), isA(DeletedJobState.class));
  }

  @Test
  public void jobCantBeRuined() {
    assertThat(job.getCurrentState()
                  .isOverDeadlines(), is(false));
  }

  @Test
  public void cantUpdateJob() {
    final String newDescription = "New description";
    final String newSkillName = "New skill";
    final Set<JobSkill> newSkills = new LinkedHashSet<>();
    final LocalDateTime newEndDateTime = LocalDateTime.now();
    newSkills.add(new JobSkill(job, new Skill(newSkillName)));
    job.getCurrentState()
       .updateJob(newDescription, newSkills, newEndDateTime);
    assertThat(job, allOf(
        not(hasProperty("endDateTime", equalTo(newEndDateTime))),
        not(hasProperty("description", equalTo(newDescription))),
        not(hasProperty("skills", equalTo(newSkills)))
    ));
  }
}