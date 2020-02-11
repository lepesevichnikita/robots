package org.klaster.model.controller;/*
 * practice
 *
 * 11.02.2020
 *
 */

import org.klaster.model.context.User;

/**
 * UserController
 *
 * @author Nikita Lepesevich
 */

public interface UserController extends ContextController<User> {

  default void deleteUser(User user) {
    user.getCurrentState()
        .deleteUser();
  }

  default void blockUser(User user) {
    user.getCurrentState()
        .blockUser();
  }

  default void unblockUser(User user) {
    user.getCurrentState()
        .unblockUser();
  }

  default void verifyUser(User user) {
    user.getCurrentState()
        .verifyUser();
  }

}
