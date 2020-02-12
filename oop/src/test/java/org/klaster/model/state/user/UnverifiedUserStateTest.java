package org.klaster.model.state.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import java.time.LocalDateTime;
import org.klaster.builder.DefaultLoginInfoBuilder;
import org.klaster.builder.DefaultUserBuilder;
import org.klaster.builder.LoginInfoBuilder;
import org.klaster.builder.UserBuilder;
import org.klaster.model.context.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/*
 * workrest
 *
 * 07.02.2020
 *
 */

/**
 * UnverifiedUserStateTest
 *
 * @author Nikita Lepesevich
 */

public class UnverifiedUserStateTest {

  private User user;

  @BeforeMethod
  public void initialize() {
    UserBuilder defaultUserBuilder = new DefaultUserBuilder();
    LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    user = defaultUserBuilder.setLoginInfo(defaultLoginInfoBuilder.build())
                             .build();
  }

  @Test
  public void doesntCreateEmployerProfile() {
    user.getCurrentState()
        .createEmployerProfile();
    assertThat(user.hasEmployerProfile(), equalTo(false));
  }

  @Test
  public void doesntCreateCustomerProfile() {
    user.getCurrentState()
        .createFreelancerProfile();
    assertThat(user.hasFreelancerProfile(), equalTo(false));
  }

  @Test
  public void authorizesUser() {
    LocalDateTime expectedAuthorizedAt = LocalDateTime.now();
    user.getCurrentState()
        .authorizeUser(expectedAuthorizedAt);
    assertThat(user.getLoginInfo()
                   .getLastAuthorizedAt(), equalTo(expectedAuthorizedAt));
  }

  @Test
  public void cantGetAccessToEmployerProfile() {
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createEmployerProfile();
    user.setCurrentState(new BlockedUserState((user)));
    assertThat(user.getCurrentState()
                   .getAccessToEmployerProfile(), nullValue());
  }

  @Test
  public void cantGetAccessToFreelancerProfile() {
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createFreelancerProfile();
    user.setCurrentState(new BlockedUserState((user)));
    assertThat(user.getCurrentState()
                   .getAccessToFreelancerProfile(), nullValue());
  }
}