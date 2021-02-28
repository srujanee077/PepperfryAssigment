package com.application.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileDropDown {
	private WebDriver driver;
	private static Actions action;

	/*
	 * constructor to help intialize WebDriver
	 */
	public ProfileDropDown(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(xpath = "//div[contains(@class,'ma-address-tab')]/div/div[contains(@id,'editAddressBlock')]/label/a[contains(@class,'add-new-address') and text()='ADD NEW ADDRESS']")
	WebElement newAddress;

	public void profileDropdownFlow(String strText, String strURL) {
		profileDropdownSelect(strText, strURL, driver);
		MyAddressBookClick();
		addNewAddress();
	}

	public static void profileDropdownSelect(String strText, String strURL, WebDriver driver) {
		boolean staleElement = true;
		while (staleElement) {

			try {
				action = new Actions(driver);

				LoginPage.waitFn(driver);
				WebElement profile = driver
						.findElement(By.xpath("//a[@href='javascript:void(0)']/span[contains(text(),'Profile')]"));
				action.moveToElement(profile).click().build().perform();

				LoginPage.waitFn(driver);
				action.moveToElement(profile).moveToElement(driver.findElement(By.xpath(
						"//div[contains(@class,'header-nav-item profile')]/div/div/div/div[contains(@class,'profile-tooltip-list')]/a[contains(@href,'"
								+ strURL + "')and text()='" + strText + "']")))
						.click().build().perform();

				LoginPage.waitFn(driver);
				staleElement = false;

			} catch (StaleElementReferenceException e) {

				staleElement = true;

			}
		}
	}

	public void MyAddressBookClick() {
		driver.findElement(By.xpath("//body"));
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement addressNavClick = driver.findElement(By.xpath(
				"//div[contains(@class,'row') and @id ='myAccountNavigation']/div/ul[contains(@class,'row ma-nav')]/li[contains(@id,'address')]/a[@href='https://www.pepperfry.com/customer/address_book']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addressNavClick);
		// addressNavClick.click();
		new Actions(driver).moveToElement(addressNavClick).click().perform();

	}

	public void addNewAddress() {
		boolean staleElement = true;
		while (staleElement) {

			try {
				driver.findElement(By.xpath("//body"));
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
				new Actions(driver).moveToElement(newAddress).click().perform();
				staleElement = false;

			} catch (StaleElementReferenceException e) {

				staleElement = true;

			}
		}
	}

	public WebElement clickStaleElement(WebDriver driver, WebElement element, By locator) {
		try {
			// Check visibility. If reference is not stale, it will return the same
			// referenced. Otherwise it will go to catch.
			element.isDisplayed();
			element.click();
			return element;

			// Relocate element in catch and return
		} catch (StaleElementReferenceException e) {
			return driver.findElement(locator);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean retryingFindClick(String xpath) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(By.xpath(xpath)).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}
}
