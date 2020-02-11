package org.klaster.model.state.general;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.klaster.model.context.Context;

/**
 * AbstractState
 *
 * @author Nikita Lepesevich
 */

public abstract class AbstractState<C extends Context> implements State<C> {

  protected final Logger logger = Logger.getLogger(getClass().getName());

  private final C context;

  private final LocalDateTime createdAt;

  public AbstractState(C context) {
    this.context = context;
    this.createdAt = LocalDateTime.now();
  }

  @Override
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public C getContext() {
    return context;
  }
}
