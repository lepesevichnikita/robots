package org.klaster.model.state.user;

/*
 * workrest
 *
 * 07.02.2020
 *
 */

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDateTime;
import org.klaster.builder.DefaultLoginInfoBuilder;
import org.klaster.builder.DefaultUserBuilder;
import org.klaster.builder.LoginInfoBuilder;
import org.klaster.builder.UserBuilder;
import org.klaster.model.context.User;
import org.klaster.model.controller.EmployerProfile;
import org.klaster.model.entity.FreelancerProfile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * VerifiedUserStateTest
 *
 * @author Nikita Lepesevich
 */

public class VerifiedUserStateTest {

  private User user;

  @BeforeMethod
  public void initialize() {
    UserBuilder defaultUserBuilder = new DefaultUserBuilder();
    LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    user = defaultUserBuilder.setLoginInfo(defaultLoginInfoBuilder.build())
                             .build();
    user.setCurrentState(new VerifiedUserState(user));
  }

  @Test
  public void createsEmployerProfileFirsTimeOnly() {
    user.getCurrentState()
        .createEmployerProfile();
    EmployerProfile oldEmployerProfile = user.getEmployerProfile();
    user.getCurrentState()
        .createEmployerProfile();
    assertThat(user.getEmployerProfile(), equalTo(oldEmployerProfile));
  }

  @Test
  public void createsFreelancerProfile() {
    user.getCurrentState()
        .createFreelancerProfile();
    assertThat(user.hasFreelancerProfile(), equalTo(true));
  }

  @Test
  public void createsFreelancerProfileSecondTimeOnly() {
    user.getCurrentState()
        .createFreelancerProfile();
    FreelancerProfile oldFreelancerProfile = user.getFreelancerProfile();
    user.getCurrentState()
        .createFreelancerProfile();
    assertThat(user.getFreelancerProfile(), equalTo(oldFreelancerProfile));
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
  public void canGetAccessToEmployerProfile() {
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createEmployerProfile();
    assertThat(user.getCurrentState()
                   .getAccessToEmployerProfile(), equalTo(user.getEmployerProfile()));
  }

  @Test
  public void canGetAccessToFreelancerProfile() {
    user.setCurrentState(new VerifiedUserState(user));
    user.getCurrentState()
        .createFreelancerProfile();
    assertThat(user.getCurrentState()
                   .getAccessToFreelancerProfile(), equalTo(user.getFreelancerProfile()));
  }
}