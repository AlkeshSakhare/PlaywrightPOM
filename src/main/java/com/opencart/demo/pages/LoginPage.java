package com.opencart.demo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

  private Page page;

  private String emailIdTxt = "//input[@id='input-email']";
  private String passwordTxt = "//input[@id='input-password']";
  private String loginBtn = "//input[@value='Login']";
  private String forgottenPasswordLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
  private String logoutLink = "(//a[@class='list-group-item'][normalize-space()='Logout'])[1]";

  public LoginPage(Page page) {
    this.page = page;
  }

  public boolean doLogin(String email, String pwd) {
    page.fill(emailIdTxt, email);
    page.fill(passwordTxt, pwd);
    page.click(loginBtn);
    return page.isVisible(logoutLink);
  }


  public String getLoginPageTitle() {
    String title = page.title();
    System.out.println("LoginPage title: " + title);
    return title;
  }

  public boolean isforgottenPasswordLinkDisplayed() {
    return page.isVisible(forgottenPasswordLink);
  }
}
