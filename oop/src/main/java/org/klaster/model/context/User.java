package org.klaster.model.context;

import org.klaster.model.controller.EmployerProfile;
import org.klaster.model.entity.FreelancerProfile;
import org.klaster.model.entity.LoginInfo;
import org.klaster.model.entity.PersonalData;
import org.klaster.model.state.user.UnverifiedUserState;
import org.klaster.model.state.user.UserState;

/**
 * User
 *
 * @author Nikita Lepesevich
 */

public class User extends AbstractContext<UserState> {

  private final LoginInfo loginInfo;
  private FreelancerProfile freelancerProfile;
  private EmployerProfile employerProfile;
  private PersonalData personalData;

  public User(LoginInfo loginInfo) {
    this.loginInfo = loginInfo;
    this.setCurrentState(new UnverifiedUserState(this));
  }

  public EmployerProfile getEmployerProfile() {
    return employerProfile;
  }

  public void setEmployerProfile(EmployerProfile employerProfile) {
    this.employerProfile = employerProfile;
  }

  public LoginInfo getLoginInfo() {
    return loginInfo;
  }

  public FreelancerProfile getFreelancerProfile() {
    return freelancerProfile;
  }

  public void setFreelancerProfile(FreelancerProfile freelancerProfile) {
    this.freelancerProfile = freelancerProfile;
  }

  public boolean hasFreelancerProfile() {
    return freelancerProfile != null;
  }

  public boolean hasEmployerProfile() {
    return employerProfile != null;
  }

  public PersonalData getPersonalData() {
    return personalData;
  }

  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
  }

  public boolean hasPersonalData() {
    return personalData != null;
  }
}
