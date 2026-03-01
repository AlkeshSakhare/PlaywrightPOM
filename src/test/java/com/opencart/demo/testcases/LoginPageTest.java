package com.opencart.demo.testcases;

import com.opencart.demo.constants.AppConstants;
import com.opencart.demo.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

  @BeforeMethod
  public void pageSetUp() {
    homePage = new HomePage(page);
    loginPage = homePage.gotoLoginPage();
  }

  @Test
  public void verifyLoginPage() {
    String actualTitle = loginPage.getLoginPageTitle();
    Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
  }

  @Test
  public void verifyForgottenPasswordLink() {
    Assert.assertTrue(loginPage.isforgottenPasswordLinkDisplayed(),
        "Forgotten Password Link is missing");
  }

  @Test
  public void verifyLogin() {
    boolean isLogoutPresent = loginPage.doLogin(properties.getProperty("email").trim(),
        properties.getProperty("password").trim());
    Assert.assertTrue(isLogoutPresent, "Login failed");

  }
}
