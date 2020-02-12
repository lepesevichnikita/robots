package org.klaster.model.controller;/*
 * practice
 *
 * 11.02.2020
 *
 */

import org.klaster.model.context.Job;
import org.klaster.model.state.job.DeletedJobState;
import org.klaster.model.state.job.FinishedJobState;
import org.klaster.model.state.job.StartedJobState;

/**
 * JobController
 *
 * @author Nikita Lepesevich
 */

public interface JobController extends ContextController<Job> {

  default void deleteJob(Job job) {
    job.setCurrentState(new DeletedJobState(job));
  }

  default void startJob(Job job) {
    job.setCurrentState(new StartedJobState(job));
  }

  default void finishJob(Job job) {
    job.setCurrentState(new FinishedJobState(job));
  }
}
