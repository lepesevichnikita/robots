package org.klaster.model.state.user;

import java.time.LocalDateTime;
import org.klaster.model.context.User;
import org.klaster.model.controller.EmployerProfile;
import org.klaster.model.entity.FreelancerProfile;
import org.klaster.model.state.general.AbstractState;

/**
 * AbstractUserState
 *
 * @author Nikita Lepesevich
 */

public abstract class AbstractUserState extends AbstractState<User> implements UserState {

  public AbstractUserState(User context) {
    super(context);
  }

  @Override
  public FreelancerProfile getAccessToFreelancerProfile() {
    return null;
  }

  @Override
  public EmployerProfile getAccessToEmployerProfile() {
    return null;
  }

  @Override
  public void authorizeUser(LocalDateTime authorizedAt) {
    final String message = String.format("Failed attempt to authorize user #%s at%s", getContext(), authorizedAt);
    logger.warning(message);
  }

  @Override
  public void createEmployerProfile() {
    final String message = String.format("Failed attempt to create employer profile for user #%s", getContext());
    logger.warning(message);
  }

  @Override
  public void createFreelancerProfile() {
    final String message = String.format("Failed attempt to create freelancer profile for user #%s", getContext());
    logger.warning(message);
  }

}
