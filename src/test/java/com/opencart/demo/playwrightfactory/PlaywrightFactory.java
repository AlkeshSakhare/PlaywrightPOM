package com.opencart.demo.playwrightfactory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {


  public Page initPlaywright(String browserName) {
    Playwright playwright = Playwright.create();
    Browser browser = null;
    switch (browserName.toLowerCase().trim()) {
      case "chromium":
      case "chrome":
      case "msedge":
        browser = playwright.chromium()
            .launch(new LaunchOptions().setHeadless(false).setChannel(browserName));
        break;
      case "firefox":
        browser = playwright.firefox()
            .launch(new LaunchOptions().setHeadless(false).setChannel(browserName));
        break;
      case "safari":
        browser = playwright.webkit()
            .launch(new LaunchOptions().setHeadless(false).setChannel(browserName));
        break;
      default:
        System.out.println("Entered browser " + browserName + " does not exist");
    }
    BrowserContext browserContext = browser.newContext();
    Page page = browserContext.newPage();
    // page.navigate("https://demo.opencart.com/");
    page.navigate("https://naveenautomationlabs.com/opencart/");
    return page;
  }

}
