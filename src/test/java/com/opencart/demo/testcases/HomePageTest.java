package com.opencart.demo.testcases;

import com.opencart.demo.constants.AppConstants;
import com.opencart.demo.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        homePage = new HomePage(page);
    }

    @Test
    public void verifyHomePageTitle() {
        String expectedTitle = AppConstants.HOME_PAGE_TITLE;
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @DataProvider(name = "productList")
    public Object[][] getProductList() {
        Object[][] productList = {{"macbook"}, {"MacBook Air"}, {"MacBook Pro"}};
        return productList;
    }

    @Test(dataProvider = "productList")
    public void verifySearchResult(String product) {
        String searchResult = homePage.searchProduct(product);
        Assert.assertEquals(searchResult, "Search - " + product);
    }

}
