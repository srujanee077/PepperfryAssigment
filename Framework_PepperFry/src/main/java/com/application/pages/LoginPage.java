package com.application.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;

	/*
	 * constructor to help intialize WebDriver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(xpath = "*//a/span[text()='Profile']")
	WebElement loginIcon;

	@FindBy(xpath = "//div[contains(@class,'reg-modal-right-bottom-btn-wrap')]/a[text()='Existing User? Log In']")
	WebElement existingUser;

	@FindBy(xpath = "//div[contains(@class,'popup-box gb-modal')]/a[@class='popup-close']")
	WebElement popUpCloseBtn;

	@FindBy(xpath = "//div[contains(@class,'home-rgstr-mobile')]/input[contains(@class,'log-mobile-email-inpt')]")
	WebElement emailID;

	@FindBy(xpath = "//div[contains(@class,'popup-body clearfix')]/div[2]/div/div/div/form/div[3]/div[contains(@class,'home-pwd-inp-wrap home-pwd-row')]/input[contains(@class,'animate-input home-ip-field') and @name='password']")
	WebElement passowrd;

	@FindBy(xpath = "//div[contains(@class,'popup-body clearfix')]/div[2]/div/div/div/form/div[5]/div/input[@name='usernameLogin']")
	WebElement loginBtn;

	public void LogInToApp(String userName, String pass) throws InterruptedException {

		Thread.sleep(10000);

		Actions action = new Actions(driver);
		Action senEsc = action.sendKeys(Keys.ESCAPE).build();
		senEsc.perform();

		// loginIcon.click();

		action = new Actions(driver);

		waitFn(driver);
		WebElement profile = driver
				.findElement(By.xpath("//a[@href='javascript:void(0)']/span[contains(text(),'Profile')]"));
		action.moveToElement(profile).click().build().perform();

		waitFn(driver);
		action.moveToElement(profile)
				.moveToElement(driver.findElement(
						By.xpath("//div[contains(@class,'header-profile-tooltip')]/div/a[@id='registerPopupLink']")))
				.click().build().perform();

		waitFn(driver);

		WebElement existUser = driver.findElement(By.xpath(
				"//div[contains(@class,'reg-modal-right-bottom-btn-wrap')]/a[contains(text(),'Existing User? Log In')]"));
		existUser.click();

		driver.getWindowHandle();

		waitFn(driver);
		WebElement user = driver.findElement(By.xpath(
				"//div[contains(@class,'popup-body clearfix')]/div[2]/div/div/div/form/div[2]/div[contains(@class,'home-rgstr-mobile')]/input[contains(@class,'animate-input log-mobile-email-inpt') and @name ='user[new]']"));
		user.sendKeys(userName);

		waitFn(driver);
		WebElement userPass = driver.findElement(By.xpath(
				"//div[contains(@class,'popup-body clearfix')]/div[2]/div/div/div/form/div[3]/div[contains(@class,'home-pwd-inp-wrap home-pwd-row')]/input[contains(@class,'animate-input home-ip-field') and @name='password']"));

		userPass.sendKeys(pass);
		WebElement loginButton = driver.findElement(By.xpath(
				"//div[contains(@class,'popup-body clearfix')]/div[2]/div/div/div/form/div[5]/div/input[@name='usernameLogin']"));
		loginButton.click();

	}

	public static void waitFn(WebDriver driver) {
		@SuppressWarnings("deprecation")
		Wait wait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	}

	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

}
