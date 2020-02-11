package org.klaster.model.state.job;

import org.klaster.model.context.Job;

/**
 * FinishedJobState
 *
 * @author Nikita Lepesevich
 */

public class FinishedJobState extends AbstractJobState {

  public FinishedJobState(Job context) {
    super(context);
  }

  @Override
  public boolean isOverDeadlines() {
    return getContext().getEndDateTime() != null && getCreatedAt().isAfter(getContext().getEndDateTime());
  }
}
