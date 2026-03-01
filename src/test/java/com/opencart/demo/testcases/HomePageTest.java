package com.opencart.demo.testcases;

import com.opencart.demo.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

  @Test
  public void verifyHomePageTitle() {
    String expectedTitle = AppConstants.HOME_PAGE_TITLE;
    String actualTitle = homePage.getHomePageTitle();
    Assert.assertEquals(actualTitle, expectedTitle);
  }

  @DataProvider(name = "productList")
  public Object[][] getProductList() {
    Object[][] productList = {{"macbook"}, {"HP LP3065"}, {"Canon EOS 5D"}, {"MacBook Air"},
        {"MacBook Pro"}};
    return productList;
  }

  @Test(dataProvider = "productList")
  public void verifySearchResult(String product) {
    String searchResult = homePage.searchProduct(product);
    Assert.assertEquals(searchResult, "Search - " + product);
  }

}
