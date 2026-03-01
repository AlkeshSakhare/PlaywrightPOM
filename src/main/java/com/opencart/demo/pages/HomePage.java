package com.opencart.demo.pages;

import com.microsoft.playwright.Page;

public class HomePage {

  private Page page;
  private String searchbox = "[name='search']";
  private String searchbutton = "div#search button";
  private String searchResultHeader = "#content h1";
  private String myAccountLink = "//span[normalize-space()='My Account']";
  private String loginPageLink = "//a[normalize-space()='Login']";

  public HomePage(Page page) {
    this.page = page;
  }

  public String searchProduct(String productName) {
    page.fill(searchbox, productName);
    page.click(searchbutton);
    String searchResultHeaderTxt = page.textContent(searchResultHeader);
    System.out.println("searchResultHeaderTxt: " + searchResultHeaderTxt);
    return searchResultHeaderTxt;
  }

  public String getHomePageTitle() {
    String title = page.title();
    System.out.println("HomePage title: " + title);
    return title;
  }

  public LoginPage gotoLoginPage() {
    page.click(myAccountLink);
    page.click(loginPageLink);
    return new LoginPage(page);
  }
}
