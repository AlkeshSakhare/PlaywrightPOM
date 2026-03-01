package com.opencart.demo.testcases;

import com.opencart.demo.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

  @Test(priority = 1)
  public void verifyLoginPage() {
    loginPage = homePage.gotoLoginPage();
    String actualTitle = loginPage.getLoginPageTitle();
    Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
  }

  // @Test(priority = 2)
  public void verifyForgottenPasswordLink() {

    Assert.assertTrue(loginPage.isforgottenPasswordLinkDisplayed(),
        "Forgotten Password Link is missing");
  }

  //@Test(priority = 3)
  public void verifyLogin() {

    boolean isLogoutPresent = loginPage.doLogin(properties.getProperty("email").trim(),
        properties.getProperty("password").trim());
    Assert.assertTrue(isLogoutPresent, "Login failed");

  }
}
