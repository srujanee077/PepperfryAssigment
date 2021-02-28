package com.application.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.application.pages.CheckoutPage;
import com.application.pages.LoginPage;
import com.application.pages.SearchItem;
import com.application.utilities.BrowserFactory;

public class WEB_OV_01_VerifyPay_Address {
	private WebDriver driver;
	@Test
	public void loginApp() throws InterruptedException {
		driver =BrowserFactory.launchApplication(driver,"Chrome","https://www.pepperfry.com/");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.LogInToApp("", "");
		SearchItem searchProduct = PageFactory.initElements(driver, SearchItem.class);
		searchProduct.searchPageFlowFistItemSelect("sofa","2");
		CheckoutPage checkoutProd = PageFactory.initElements(driver, CheckoutPage.class);
		checkoutProd.verifyPayCheckoutPageFlow("Republic");
		
	}

}
