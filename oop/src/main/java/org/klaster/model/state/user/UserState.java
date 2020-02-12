package org.klaster.model.state.user;

import java.time.LocalDateTime;
import org.klaster.model.context.User;
import org.klaster.model.controller.EmployerProfile;
import org.klaster.model.entity.FreelancerProfile;
import org.klaster.model.state.general.State;

/**
 * UserState
 *
 * @author Nikita Lepesevich
 */

public interface UserState extends State<User> {

  void authorizeUser(LocalDateTime authorizedAt);

  void createEmployerProfile();

  void createFreelancerProfile();

  FreelancerProfile getAccessToFreelancerProfile();

  EmployerProfile getAccessToEmployerProfile();
}
