package com.opencart.demo.testcases;

import com.microsoft.playwright.Page;
import com.opencart.demo.pages.HomePage;
import com.opencart.demo.playwrightfactory.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest {

  PlaywrightFactory pf;
  Page page;
  HomePage homePage;

  @BeforeMethod
  public void setUp() {
    pf = new PlaywrightFactory();
    page = pf.initPlaywright("msedge");
    homePage = new HomePage(page);
  }

  @Test
  public void verifyPageTitle() {
    String expectedTitle = "Your Store";
    String actualTitle = homePage.pageTitle();
    Assert.assertEquals(actualTitle, expectedTitle);
  }

  @DataProvider(name = "productList")
  public Object[][] getProductList() {

    Object[][] productList = {
        {"macbook"},
        {"HP LP3065"},
        {"Canon EOS 5D"},
        {"MacBook Air"}, {"MacBook Pro"}};
    return productList;
  }

  @Test(dataProvider = "productList")
  public void verifySearchResult(String product) {
    String searchResult = homePage.searchProduct(product);
    Assert.assertEquals(searchResult, "Search - " + product);
  }

  @AfterMethod()
  public void tearDown() {
    page.context().browser().close();
  }
}
