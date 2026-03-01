package com.opencart.demo.playwrightfactory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

  static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
  static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
  static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
  static ThreadLocal<Page> tlPage = new ThreadLocal<>();
  Properties properties;

  public Playwright getPlaywright() {
    return tlPlaywright.get();
  }

  public Browser getBrowser() {
    return tlBrowser.get();
  }

  public BrowserContext getBrowserContext() {
    return tlBrowserContext.get();
  }

  public Page getPage() {
    return tlPage.get();
  }

  public Properties initProp() {
    try {
      properties = new Properties();
      properties.load(new FileReader("./src/main/resources/config.properties"));
      return properties;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Page initPlaywright(Properties properties) {

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) dimension.getWidth();
    int height = (int) dimension.getHeight();
    LaunchOptions launchOptions = new LaunchOptions();
    launchOptions.setHeadless(false);
    //Playwright playwright = Playwright.create();
    tlPlaywright.set(Playwright.create());
    // Browser browser = null;
    String browserName = properties.getProperty("browser").trim();
    switch (browserName.toLowerCase().trim()) {
      case "chromium":
      case "chrome":
      case "msedge":
        //browser = playwright.chromium().launch(launchOptions.setChannel(browserName));
        tlBrowser.set(getPlaywright().chromium()
            .launch(launchOptions.setChannel(browserName)));
        break;
      case "firefox":
        // browser = playwright.firefox().launch(launchOptions.setChannel(browserName));
        tlBrowser.set(getPlaywright().firefox()
            .launch(launchOptions.setChannel(browserName)));
        break;
      case "safari":
      case "webkit":
        /// browser = playwright.webkit().launch(launchOptions.setChannel(browserName));
        tlBrowser.set(getPlaywright().webkit()
            .launch(launchOptions.setChannel(browserName)));
        break;
      default:
        System.out.println("Entered browser " + browserName + " does not exist");
    }
    // BrowserContext browserContext = browser.newContext();
    tlBrowserContext.set(
        getBrowser().newContext(new NewContextOptions().setViewportSize(width, height)));
    //Page page = browserContext.newPage();
    tlPage.set(getBrowserContext().newPage());
    // page.navigate("https://demo.opencart.com/");
    tlPage.get().navigate(properties.getProperty("url").trim());
    return getPage();
  }

}
