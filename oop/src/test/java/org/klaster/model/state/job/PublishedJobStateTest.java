package org.klaster.model.state.job;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
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
 * PublishedJobStateTest
 *
 * @author Nikita Lepesevich
 */

public class PublishedJobStateTest {

  private Job job;

  @BeforeMethod
  public void initialize() {
    JobBuilder defaultJobBuilder = new DefaultJobBuilder();
    job = defaultJobBuilder.build();
  }

  @Test
  public void cantFinishJob() {
    job.getCurrentState()
       .finishJob();
    assertThat(job.getCurrentState(), isA(PublishedJobState.class));
  }

  @Test
  public void cantDeleteJob() {
    job.getCurrentState()
       .deleteJob();
    assertThat(job.getCurrentState(), isA(DeletedJobState.class));
  }

  @Test
  public void canStartJob() {
    job.getCurrentState()
       .startJob();
    assertThat(job.getCurrentState(), isA(StartedJobState.class));
  }

  @Test
  public void jobCantBeRuined() {
    assertThat(job.getCurrentState()
                  .isOverDeadlines(), is(false));
  }

  @Test
  public void canUpdateJob() {
    final String newDescription = "New description";
    final String newSkillName = "New skill";
    final Set<JobSkill> newSkills = new LinkedHashSet<>();
    final LocalDateTime newEndDateTime = LocalDateTime.now();
    newSkills.add(new JobSkill(job, new Skill(newSkillName)));
    job.getCurrentState()
       .updateJob(newDescription, newSkills, newEndDateTime);
    assertThat(job, allOf(
        hasProperty("endDateTime", equalTo(newEndDateTime)),
        hasProperty("description", equalTo(newDescription)),
        hasProperty("skills", equalTo(newSkills))
    ));
  }
}