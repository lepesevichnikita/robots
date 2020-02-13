package org.klaster.model.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isA;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.klaster.builder.DefaultJobBuilder;
import org.klaster.builder.DefaultLoginInfoBuilder;
import org.klaster.builder.DefaultUserBuilder;
import org.klaster.builder.LoginInfoBuilder;
import org.klaster.builder.UserBuilder;
import org.klaster.model.context.Job;
import org.klaster.model.context.User;
import org.klaster.model.state.job.DeletedJobState;
import org.klaster.model.state.job.FinishedJobState;
import org.klaster.model.state.job.PublishedJobState;
import org.klaster.model.state.job.StartedJobState;
import org.klaster.model.state.user.VerifiedUserState;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * workrest
 *
 * 07.02.2020
 *
 */

/**
 * EmployerProfileTest
 *
 * @author Nikita Lepesevich
 */

public class EmployerProfileTest {

  private EmployerProfile employerProfile;
  private static final String NEW_JOB_DESCRIPTION = "New job description";
  private static LocalDateTime newEndDateTime = LocalDateTime.now()
                                                             .plus(1, ChronoUnit.DAYS);

  @BeforeMethod
  public void initialize() {
    final UserBuilder defaultUserBuilder = new DefaultUserBuilder();
    final LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    final User user = defaultUserBuilder.setLoginInfo(defaultLoginInfoBuilder.build())
                                  .build();
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createEmployerProfile();
    employerProfile = user.getEmployerProfile();
  }

  @Test
  public void createsJob() {
    employerProfile.createJob(NEW_JOB_DESCRIPTION, newEndDateTime);
    assertThat(employerProfile.getJobs(), hasItem(allOf(
        hasProperty("description", equalTo(NEW_JOB_DESCRIPTION)),
        hasProperty("endDateTime", equalTo(newEndDateTime)),
        hasProperty("employerProfile", equalTo(employerProfile)))
    ));
  }

  @Test
  public void deletesOwnJob() {
    final Job createdJob = employerProfile.createJob(NEW_JOB_DESCRIPTION, newEndDateTime);
    employerProfile.deleteJob(createdJob);
    assertThat(createdJob.getCurrentState(), isA(DeletedJobState.class));
  }

  @Test
  public void finishesOwnJob() {
    final Job createdJob = employerProfile.createJob(NEW_JOB_DESCRIPTION, newEndDateTime);
    employerProfile.finishJob(createdJob);
    assertThat(createdJob.getCurrentState(), isA(FinishedJobState.class));
  }


  @Test
  public void startsOwnJob() {
    final Job createdJob = employerProfile.createJob(NEW_JOB_DESCRIPTION, newEndDateTime);
    employerProfile.startJob(createdJob);
    assertThat(createdJob.getCurrentState(), isA(StartedJobState.class));
  }


  @Test
  public void cantDeleteUnfamiliarJob() {
    final Job createdJob = new DefaultJobBuilder().build();
    employerProfile.deleteJob(createdJob);
    assertThat(createdJob.getCurrentState(), isA(PublishedJobState.class));
  }

  @Test
  public void cantFinishUnfamiliarJob() {
    final Job createdJob = new DefaultJobBuilder().build();
    employerProfile.finishJob(createdJob);
    assertThat(createdJob.getCurrentState(), isA(PublishedJobState.class));
  }


  @Test
  public void startsUnfamiliarJob() {
    final Job createdJob = new DefaultJobBuilder().build();
    employerProfile.startJob(createdJob);
    assertThat(createdJob.getCurrentState(), isA(PublishedJobState.class));
  }
}