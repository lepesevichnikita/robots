package org.klaster.model.state.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
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
 * BlockedUserStateTest
 *
 * @author Nikita Lepesevich
 */

public class BlockedUserStateTest {

  private User user;

  @BeforeMethod
  public void initialize() {
    final UserBuilder defaultUserBuilder = new DefaultUserBuilder();
    final LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    user = defaultUserBuilder.setLoginInfo(defaultLoginInfoBuilder.build())
                             .build();
    user.setCurrentState(new BlockedUserState(user));
  }

  @Test
  public void blockedUserCantAuthorize() {
    final LocalDateTime authorizedAt = LocalDateTime.now();
    user.getCurrentState()
        .authorizeUser(authorizedAt);
    assertThat(user.getLoginInfo()
                   .getLastAuthorizedAt(), not(equalTo(authorizedAt)));
  }

  @Test
  public void cantCreateEmployerProfile() {
    user.getCurrentState()
        .createEmployerProfile();
    assertThat(user.hasEmployerProfile(), equalTo(false));
  }

  @Test
  public void cantCreateFreelancerProfile() {
    user.getCurrentState()
        .createFreelancerProfile();
    assertThat(user.hasFreelancerProfile(), equalTo(false));
  }

  @Test
  public void cantGetAccessToEmployerProfile() {
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createEmployerProfile();
    user.setCurrentState(new BlockedUserState(user));
    assertThat(user.getCurrentState()
                   .getAccessToEmployerProfile(), nullValue());
  }


  @Test
  public void cantGetAccessToFreelancerProfile() {
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createFreelancerProfile();
    user.setCurrentState(new BlockedUserState(user));
    assertThat(user.getCurrentState()
                   .getAccessToFreelancerProfile(), nullValue());
  }
}