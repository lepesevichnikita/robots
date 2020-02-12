package org.klaster.model.controller;

/*
 * workrest
 *
 * 05.02.2020
 *
 */

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isA;

import org.klaster.builder.DefaultLoginInfoBuilder;
import org.klaster.builder.DefaultUserBuilder;
import org.klaster.builder.LoginInfoBuilder;
import org.klaster.builder.UserBuilder;
import org.klaster.model.context.User;
import org.klaster.model.entity.FileInfo;
import org.klaster.model.entity.PersonalData;
import org.klaster.model.state.user.BlockedUserState;
import org.klaster.model.state.user.DeletedUserState;
import org.klaster.model.state.user.UnverifiedUserState;
import org.klaster.model.state.user.VerifiedUserState;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * AdministratorTest
 *
 * @author Nikita Lepesevich
 */

public class AdministratorTest {

  private static final String DOCUMENT_NAME = "passport";
  private static final String DOCUMENT_NUMBER = "8781279A001PB8";
  private static final String FIST_NAME = "John";
  private static final String LAST_NAME = "Konnor";
  private static FileInfo DOCUMENT_SCAN = new FileInfo("randomhash", "path/to/file");
  private Administrator administrator;
  private UserBuilder defaultUserBuilder;

  @BeforeMethod
  public void initialize() {
    final LoginInfoBuilder defaultLoginInfoBuilder = new DefaultLoginInfoBuilder();
    administrator = new Administrator(defaultLoginInfoBuilder.build());
    defaultUserBuilder = new DefaultUserBuilder();
  }

  @Test
  public void blocksUser() {
    final User user = defaultUserBuilder.build();
    administrator.blockUser(user);
    assertThat(user.getCurrentState(), isA(BlockedUserState.class));
  }

  @Test
  public void deletesUser() {
    final User user = defaultUserBuilder.build();
    administrator.deleteUser(user);
    assertThat(user.getCurrentState(), isA(DeletedUserState.class));
  }

  @Test
  public void unblocksUser() {
    final User user = defaultUserBuilder.build();
    administrator.blockUser(user);
    administrator.unblockUser(user);
    assertThat(user.getCurrentState(), isA(UnverifiedUserState.class));
  }

  @Test
  public void cantVerifyUserIfItHasNoPersonalData() {
    final User user = defaultUserBuilder.build();
    administrator.verifyUser(user);
    assertThat(administrator.getCurrentState(user), isA(UnverifiedUserState.class));
  }

  @Test
  public void verifiesUserIfItHasPersonalData() {
    final User user = defaultUserBuilder.build();
    user.setPersonalData(new PersonalData(DOCUMENT_NAME, DOCUMENT_NUMBER, FIST_NAME, LAST_NAME, DOCUMENT_SCAN));
    administrator.verifyUser(user);
    assertThat(administrator.getCurrentState(user), isA(VerifiedUserState.class));
  }

  @Test
  public void considersPersonalDataAtVerification() {
    final User user = defaultUserBuilder.build();
    user.setPersonalData(new PersonalData(DOCUMENT_NAME, DOCUMENT_NUMBER, FIST_NAME, LAST_NAME, DOCUMENT_SCAN));
    administrator.verifyUser(user);
    assertThat(user.getPersonalData(), hasProperty("consideredBy", equalTo(administrator)));
  }
}
