package com.opencart.demo.testcases;

import com.microsoft.playwright.Page;
import com.opencart.demo.pages.HomePage;
import com.opencart.demo.pages.LoginPage;
import com.opencart.demo.playwrightfactory.PlaywrightFactory;
import java.util.Properties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  protected HomePage homePage;
  protected LoginPage loginPage;
  protected Properties properties;
  PlaywrightFactory pf;
  Page page;
  
  @BeforeMethod
  public void setUp() {
    System.out.println("Running from BaseTest: " + this.getClass().getName());
    pf = new PlaywrightFactory();
    properties = pf.initProp();
    page = pf.initPlaywright(properties);
  }

  @AfterMethod
  public void tearDown() {
    page.context().close();
    page.context().browser().close();
  }
}
