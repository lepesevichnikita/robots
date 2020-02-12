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
 * DeletedUserStateTest
 *
 * @author Nikita Lepesevich
 */

public class DeletedUserStateTest {

  private User user;

  @BeforeMethod
  public void initialize() {
    UserBuilder defaultUserBuilder = new DefaultUserBuilder();
    LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    user = defaultUserBuilder.setLoginInfo(defaultLoginInfoBuilder.build())
                             .build();
    user.setCurrentState(new DeletedUserState(user));
  }

  @Test
  public void cantAuthorize() {
    LocalDateTime authorizedAt = LocalDateTime.now();
    user.getCurrentState()
        .authorizeUser(authorizedAt);
    assertThat(user.getLoginInfo()
                   .getLastAuthorizedAt(), not(equalTo(authorizedAt)));
  }

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
    user.setCurrentState(new DeletedUserState(user));
    assertThat(user.getCurrentState()
                   .getAccessToEmployerProfile(), nullValue());
  }


  @Test
  public void cantGetAccessToFreelancerProfile() {
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createFreelancerProfile();
    user.setCurrentState(new DeletedUserState(user));
    assertThat(user.getCurrentState()
                   .getAccessToFreelancerProfile(), nullValue());
  }
}