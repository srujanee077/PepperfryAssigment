package com.application.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SearchItem {
	private WebDriver driver;
	private Actions action;

	/*
	 * constructor to help intialize WebDriver
	 */
	public SearchItem(WebDriver driver) {
		this.driver=driver;

	}
	@FindBy(name="//input[@name='q' and contains(@class,'ui-autocomplete-input')]")
	WebElement searchBar;

	public void searchPageFlowFistItemSelect(String strProductToSearch,String strQuantity) {
		searchInApp(strProductToSearch);
		firstProductClick();
		moveToActiveTab(driver);
		selectQuantity(strQuantity);
		buyNowClick();
	}

	/**
	 * Search Section
	 */
	public void searchInApp(String strProductToSearch) {
		try {
			LoginPage.waitFn(driver);
			action = new Actions(driver);
			WebElement searchProduct = driver.findElement(By.xpath("//div[contains(@class,'search_bar')]/div/form/input[contains(@name,'q') and @class='ui-autocomplete-input']"));	

			searchProduct.sendKeys(strProductToSearch);//"sofa"
			searchProduct.sendKeys(Keys.ENTER);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Inside catch");
		}
	}
	public void firstProductClick() {
		/**
		 * First elem click
		 */
		try {
			WebElement firstElem = driver.findElement(By.xpath("//div[contains(@class,'pf-col srchrslt-crd-2x1 srch-rslt-cards pf-margin-bottom20 clipprods')][1]/div/div[1]"));
			LoginPage.waitFn(driver);
			firstElem.click();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Inside catch");
		}
	}

	/**
	 * Tab Movement
	 */
	public static void moveToActiveTab(WebDriver driver) {
		try {
			System.out.println(driver.getTitle());
			Set <String> st= driver.getWindowHandles();
			Iterator<String> it = st.iterator();
			String parent =  it.next();
			String child = it.next();
			// switch to child
			driver.switchTo().window(child);
			Thread.sleep(2000);
			//swtich to parent
			//driver.switchTo().window(parent);
			System.out.println("Returned to parent");
			System.out.println(driver.getTitle());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Inside catch");
		}
	}

	/**
	 * Select --Quantity
	 */
	public void selectQuantity(String quant) {
		try {
			driver.findElement(By.xpath("//body")).click();
			action.sendKeys(Keys.PAGE_DOWN);
			LoginPage.waitFn(driver);

			WebElement quantity = driver.findElement(By.xpath("//div[contains(@class,'v-qty-opt-value-wrap')]/div[1]/select"));

			Select objSelect =new Select(quantity);
			objSelect.selectByValue("2");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Inside catch");
		}
	}
	/***
	 * Buy Now
	 */
	public void buyNowClick() {
		try {
			LoginPage.waitFn(driver);
			WebElement buyNow = driver.findElement(By.xpath("//div[contains(@class,'v-atc-bn-btn-section container-fluid ')]/div/div[2]/a[contains(text(),'Buy Now')]"));
			buyNow.click();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Inside catch");
		}
	}

public void searchHover(String product) {
	LoginPage.waitFn(driver);
	List <WebElement> menuNavList = driver.findElements(By.xpath("//div[contains(@class,'menu_wrapper transition pf-navbar lazy-load')]/ul/li/a"));
	int i=1;
//	for(WebElement menu:menuNavList) {
//		System.out.println("Elemts--"+menu.getText());
//		if(menu.getText().equals(product)) {
//			System.out.println("Furniture Produt found>>>");
//			 WebElement toClickElem = driver.findElement(By.xpath("//div[contains(@class,'menu_wrapper transition pf-navbar lazy-load')]/ul/li/a["+i+"]"));
//			 toClickElem.click();
//			action.moveToElement(toClickElem).click().build().perform();
//			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//			action.moveToElement(toClickElem).moveToElement( driver.findElement(By.xpath("//div[contains(@class,'lazy-load') and @id='megamenu']/div/div/div/div/div/a[contains(text(),'3 Seater Sofas')][1]"))).click().build().perform();
//			
//		}
//		++i;
	//}
	boolean staleElement = true; 
	while(staleElement){

		  try{
	WebElement menuNav = driver.findElement(By.xpath("//div[contains(@class,'menu_wrapper transition pf-navbar lazy-load')]/ul/li/a[@href='https://www.pepperfry.com/furniture.html?type=hover-furniture' and contains(text(),'Furniture')]"));
	menuNav.click();
	LoginPage.waitFn(driver);
	action.moveToElement(menuNav).perform();
	//action.moveToElement( driver.findElement(By.xpath("//a[contains(@class,'hvr-col-listitem-link pf-text-grey') and text()='3 Seater Sofas']"))).click().build().perform();
	
//	driver.findElement(By.xpath("//div[contains(@class,'megamenu_panel')]/div/div/div/div/a[@href='https://www.pepperfry.com/furniture-sofas-sofas-couches-three-seater.html?type=hover-furniture' and contains(text(),'3 Seater Sofas')]")).click();
//	driver.findElement(By.linkText("3 Seater Sofas"));
	staleElement = false;


	  } catch(StaleElementReferenceException e){

	    staleElement = true;

	  }

	}
	
	
	
}
public void productAddToWishList() {

	LoginPage.waitFn(driver);
	WebElement addToWishList= driver.findElement(By.xpath("//div[contains(@class,'row vipProduct__social')]/div[3]/div"));
	//"//div[contains(@id,'productView')]/div/div/div[1]/div/div[contains(@class,'row clip-dtl-brand')]/div/a[contains(@class,'clip-heart-icn pf-right')]"));
	addToWishList.click();
	action.moveToElement(addToWishList).perform();
}

public String storeProductName() {
	WebElement productName = driver.findElement(By.xpath("//h1[contains(@class,'v-pro-ttl pf-medium-bold-text')]"));
	System.out.println("Product Name-----"+productName.getText());
	return productName.getText();
}

public void validateProductName(String toCheck) {
	List<WebElement>listProduct= driver.findElements(By.xpath("//div[contains(@class,'tab-container')]/div/div/div[contains(@class,'xs-6 pf-col ma-wish-sku-name-col')]"));
	for(WebElement elem:listProduct) {
		if(elem.getText().contains(toCheck)) {
			System.out.println("wishlist Product--"+elem.getText());
			System.out.println("Matching Product found");
		}
	}
}
public void searchHoverAddWishList(String product) {
	LoginPage.waitFn(driver);
	//searchHover(product);
	searchInApp(product);
	firstProductClick();
	moveToActiveTab(driver);
	productAddToWishList();
	String strProduct=storeProductName();
	ProfileDropDown.profileDropdownSelect("My Wishlist","https://www.pepperfry.com/customer/wishlist",driver);
	validateProductName(strProduct);
}

}
