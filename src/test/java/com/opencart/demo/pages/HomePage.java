package com.opencart.demo.pages;

import com.microsoft.playwright.Page;

public class HomePage {

  Page page;
  private String searchbox = "[name='search']";
  private String searchbutton = "div#search button";
  private String searchResultHeader = "#content h1";

  public HomePage(Page page) {
    this.page = page;
  }

  public String searchProduct(String productName) {
    page.fill(searchbox, productName);
    page.click(searchbutton);
    String searchResulHeader = page.textContent(searchResultHeader);
    System.out.println("searchResulHeader: " + searchResulHeader);
    return searchResulHeader;
  }

  public String pageTitle() {
    String title = page.title();
    System.out.println("title: " + title);
    return title;
  }
}
