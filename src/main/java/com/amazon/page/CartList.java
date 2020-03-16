package com.amazon.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartList {
	
	static WebDriver driver = null;

	@FindBy(xpath = "//div[@id='sc-item-Cac758f97-f699-45e3-8fa0-1c996fedaa43']//img[@class='sc-product-image']") 
	WebElement iteminCart;
	
	
	public CartList(WebDriver driver) {
		 this.driver = driver;
	}


	public void itemDescription()
	{
		iteminCart.click();
	}
	
}
