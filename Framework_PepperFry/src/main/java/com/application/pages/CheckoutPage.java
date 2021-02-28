package com.application.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CheckoutPage {
	private WebDriver driver;

	/*
	 * constructor to help intialize WebDriver
	 */
	public CheckoutPage(WebDriver driver) {
		this.driver=driver;

	}
	public void verifyPayCheckoutPageFlow(String coupon) {
		applyCoupoun(coupon);
		String storeYourPay=fetchPayCheckout();
		placeOrderClick();
		fetchPayInPayments(storeYourPay);
	}
	/**
	 * Apply coupon
	 */
	public void applyCoupoun(String coupon) {
		SearchItem.moveToActiveTab(driver);

		LoginPage.waitFn(driver);
		WebElement applyCouponField = driver.findElement(By.xpath("//div[contains(@class,'ck-cpn-wrap')]/div[contains(@class,'ck-cpn-input-wrap')]/input[contains(@class,'inputcoupon')]"));
		applyCouponField.sendKeys(coupon);

		LoginPage.waitFn(driver);
		WebElement applyCouponBtn = driver.findElement(By.xpath("//div[contains(@class,'ck-cpn-wrap')]/div[contains(@class,'ck-cpn-input-wrap')]/input[contains(@name,'cpn_check_btn')]"));
		applyCouponBtn.click();
		Reporter.log("Apply button clicked");
	}
	/**
	 * Fetch your Pay from Checkout screen
	 * @return 
	 */
	public String fetchPayCheckout() {
		WebElement yourPay = driver.findElement(By.xpath("//div[contains(@class,'ck-final-price-col')][2]/span[contains(@class,'ck-final-price-txt') and @id ='total_pay_coupon']"));
		String storeYourPay=yourPay.getText();
		System.out.println("Your Pay---"+storeYourPay);
		return storeYourPay;
	}


	/**
	 * Click on Place order
	 */
	public void placeOrderClick() {

		LoginPage.waitFn(driver);
		WebElement placeOrderBtn = driver.findElement(By.xpath("//span[contains(@class,'ck-proceed-btn-wrap')]/a[contains(text(),'PLACE ORDER')][1]"));
		placeOrderBtn.click();
	}
	/**
	 * Fetch You Pay in Payments section
	 */
	public void fetchPayInPayments(String storeYourPay) {
		WebElement yourPay = driver.findElement(By.xpath("//div[contains(@class,'ck-final-price-col')][2]/span[contains(@class,'ck-final-price-txt') and @id ='total_pay_coupon']"));

		String yourPayInPayment = yourPay.getText();
		if(yourPayInPayment.equals(storeYourPay)) {
			System.out.println("Successfully found Payment section in CheckOut"+storeYourPay+"and Payment in pge same "+yourPayInPayment);
		}

	}
}
