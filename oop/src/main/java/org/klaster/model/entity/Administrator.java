package org.klaster.model.entity;

import org.klaster.model.controller.UserController;

/**
 * Administrator
 *
 * @author Nikita Lepesevich
 */

public class Administrator implements UserController {

  private final LoginInfo loginInfo;

  public Administrator(LoginInfo loginInfo) {
    this.loginInfo = loginInfo;
  }

  public LoginInfo getLoginInfo() {
    return loginInfo;
  }
}
