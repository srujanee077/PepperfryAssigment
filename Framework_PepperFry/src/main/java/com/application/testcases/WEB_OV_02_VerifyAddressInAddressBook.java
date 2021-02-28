package com.application.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.application.pages.LoginPage;
import com.application.pages.ProfileDropDown;
import com.application.utilities.BrowserFactory;

public class WEB_OV_02_VerifyAddressInAddressBook {
	private WebDriver driver;
	@Test
	public void verifyAddress() throws InterruptedException {
		driver =BrowserFactory.launchApplication(driver,"Chrome","https://www.pepperfry.com/");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.LogInToApp("", "");
		ProfileDropDown profileSelect = PageFactory.initElements(driver, ProfileDropDown.class);
		profileSelect.profileDropdownFlow("My Account","https://www.pepperfry.com/customer/dashboard");
	}

}
