package org.klaster.model.controller;/*
 * practice
 *
 * 11.02.2020
 *
 */

import org.klaster.model.context.User;
import org.klaster.model.state.user.BlockedUserState;
import org.klaster.model.state.user.DeletedUserState;
import org.klaster.model.state.user.UnverifiedUserState;
import org.klaster.model.state.user.VerifiedUserState;

/**
 * UserController
 *
 * @author Nikita Lepesevich
 */

public interface UserController extends ContextController<User> {

  default void deleteUser(User user) {
    user.setCurrentState(new DeletedUserState(user));
  }

  default void blockUser(User user) {
    user.setCurrentState(new BlockedUserState(user));
  }

  default void unblockUser(User user) {
    user.setCurrentState(new UnverifiedUserState(user));
  }

  default void verifyUser(User user) {
    user.setCurrentState(new VerifiedUserState(user));
  }

}
