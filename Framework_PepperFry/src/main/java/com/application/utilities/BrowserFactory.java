package com.application.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	public static WebDriver launchApplication(WebDriver driver, String browserName, String appURL) {
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Not supporting browser used");
		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// implicit wait
		return driver;

	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}
