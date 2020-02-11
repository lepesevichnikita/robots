package org.klaster.model.state.job;

import org.klaster.model.context.Job;

/**
 * StartedJobState
 *
 * @author Nikita Lepesevich
 */

public class StartedJobState extends AbstractJobState {

  public StartedJobState(Job context) {
    super(context);
  }

  @Override
  public void finishJob() {
    getContext().setCurrentState(new FinishedJobState(getContext()));
    final String message = String.format("Job #%s was finished", getContext());
    logger.info(message);
  }
}
