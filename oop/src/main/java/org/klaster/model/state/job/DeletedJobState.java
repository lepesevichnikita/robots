package org.klaster.model.state.job;

import org.klaster.model.context.Job;

/**
 * DeletedJobState
 *
 * @author Nikita Lepesevich
 */

public class DeletedJobState extends AbstractJobState {

  public DeletedJobState(Job context) {
    super(context);
  }
}
