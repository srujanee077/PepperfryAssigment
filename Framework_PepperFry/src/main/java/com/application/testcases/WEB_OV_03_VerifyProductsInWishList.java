package com.application.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.application.pages.CheckoutPage;
import com.application.pages.LoginPage;
import com.application.pages.SearchItem;
import com.application.utilities.BrowserFactory;

public class WEB_OV_03_VerifyProductsInWishList {
	private WebDriver driver;
	@Test
	public void verifyProductsInWishList() throws InterruptedException {
		driver =BrowserFactory.launchApplication(driver,"Chrome","https://www.pepperfry.com/");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.LogInToApp("srujanee077@gmail.com", "SRuj@nee077");
		SearchItem searchProduct = PageFactory.initElements(driver, SearchItem.class);
		//searchProduct.searchHoverAddWishList("Furniture");
		searchProduct.searchHoverAddWishList("3 Seater Sofas");

		
	}

}
