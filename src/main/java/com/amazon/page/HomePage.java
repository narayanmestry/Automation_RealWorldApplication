package com.amazon.page;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

	static WebDriver driver = null;

	@FindBy(id = "twotabsearchtextbox")
	WebElement textbox;

	@FindBy(xpath = "//input[@value = 'Go']")
	WebElement searchBox;

	@FindBy(id = "searchDropdownBox")
	WebElement dropdownlist;

	@FindBy(xpath = "//img[@alt = 'Echo Dot (3rd Gen) – New and improved smart speaker with Alexa (Black)']")
	WebElement img;

	@FindBy(xpath = "//a[@id = 'mbc-buybutton-addtocart-1-announce']")
	WebElement addtocart;

	@FindBy(id = "nav-cart")
	WebElement cartListButton;

	@FindBy(id = "a-autoid-0")
	WebElement quntityDropdown;

	@FindBy(id = "dropdown1_4")
	WebElement quntity;

	@FindBy(xpath = "//span[text()='Lenovo K9 (Black, 32 GB) (3 GB RAM)']")
	WebElement lenevoMobile1;

	@FindBy(xpath = "Lenovo A6 Note (Blue, 32 GB) (3 GB RAM)")
	WebElement lenevoMobile2;

	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ActionMethod

	/***********************************************************************************
	 * @param text
	 * @throws IOException
	 * @Description : Enter the Text 
	 ************************************************************************************/
	public void enterText(String text) throws IOException {
		textbox.sendKeys(text);
	}


	/***********************************************************************************
	 * @Description :click on Search box
	 ************************************************************************************/
	public void clickSearch() {
		searchBox.click();
	}

	/***********************************************************************************
	 * @param index
	 * @Description :select the item from Dropdown
	 ************************************************************************************/
	public void selectDropdown(int index) {
		WebElement list = dropdownlist;
		Select s = new Select(list);
		dropdownlist.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		s.selectByIndex(index);
	}

	/***********************************************************************************
	 * @Description : Select Image
	 ************************************************************************************/
	public void selectImage() {
		img.click();
	}

	/***********************************************************************************
	 * @Description : Scroll down
	 ************************************************************************************/
	public void downScroll() throws InterruptedException {
		int x, y;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		x = addtocart.getLocation().getX();
		y = addtocart.getLocation().getY();
		js.executeScript("window.scrollBy(" + x + "," + (y - 100) + ")");
		Thread.sleep(1000);
	}


	/***********************************************************************************
	 * @Description : Select item to cart
	 ************************************************************************************/
	public void selectAddToCart() {
		addtocart.click();
	}


	/***********************************************************************************
	 * @Description : Select cartlist
	 ************************************************************************************/

	public void cartListButton() {
		cartListButton.click();
	}

 
	/***********************************************************************************
	 * @Description : increse the Quantity of product by selecting perticular number of quantity in
	 * 				  list
	 ************************************************************************************/

	public void select_Dropdowm_For_IncreaseQuntity(int index) {
		quntityDropdown.click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		quntity.click();
	}

	
	public void selectItem1() {
		lenevoMobile1.click();
	}
	
	public void selectItem2() {
		lenevoMobile2.click();
	}
	
}
