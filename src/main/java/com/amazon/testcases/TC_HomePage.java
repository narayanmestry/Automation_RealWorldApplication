package com.amazon.testcases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.amazon.base.Base;
import com.amazon.page.HomePage;

public class TC_HomePage extends Base {

	public static String parentWindowId;
	public static String childWindowId;
	public static String childWindowId2;

	/**
	 * @Testcase_Description :able to Search Product
	 * 
	 * @throws IOException
	 */
//	@Test(priority = 1)
	public void tc_001_SearchProduct() throws IOException {
		HomePage homePage = new HomePage(driver);
		homePage.enterText("Lenovo");
		homePage.clickSearch();
	}

	/***************************************************************************************
	 * @Testcase_Description :able to select from dropdown and able search
	 * 
	 ****************************************************************************************/
//	@Test(priority = 2)
	public void tc_002_dropdown() {
		HomePage homePage = new HomePage(driver);
		homePage.selectDropdown(3);
		homePage.clickSearch();
	}

	
	/*****************************************************************************************
	 * @Testcase_Description : able to add one element to the cart
	 * @throws InterruptedException
	 ****************************************************************************************/
//	@Test(priority = 3)
	public void tc_003_addOnetoCart() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		tc_002_dropdown();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		// Select the item to add to card
		homePage.selectImage();

		// Used for handle childbrowser window
		// here child window get open so try to take id of child window and switch on it
		Set<String> listofwindows = driver.getWindowHandles();
		Iterator<String> iterator = listofwindows.iterator();
		// parent window id :
		parentWindowId = iterator.next();
		// child window id :
		childWindowId = iterator.next();
		driver.switchTo().window(childWindowId);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

		
		
		// scroll to add to cart Button
		homePage.downScroll();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		homePage.selectAddToCart();
	}


	
	/*******************************************************************************************
	 * @Testcase_Description : user should able to increase the Quantity of the
	 *                       Product
	 * @throws InterruptedException
	 ******************************************************************************************/
//	@Test(priority = 4)
	public void tc_004_increaseQuantityOfItem() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		tc_003_addOnetoCart();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		homePage.cartListButton();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// click to add Quantity
		homePage.select_Dropdowm_For_IncreaseQuntity(4);
	}

	/*******************************************************************************************
	 * @Testcase_Description : should able to increase the Quantity of the Product
	 *                       when user cart same product multiple time
	 * @throws InterruptedException
	 ******************************************************************************************/
//	@Test(priority = 5)
	public void tc_005_addSameItemMultipleTime() throws InterruptedException, IOException {
		HomePage homePage = new HomePage(driver);
		// here i have call tc_003_addOnetoCart() to add multiple item to card
		tc_003_addOnetoCart();
		driver.get("https://www.amazon.in/s?i=amazon-devices&k=&ref=nb_sb_noss&url=search-alias%3Damazon-devices");
		homePage.selectImage();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Used for handle childbrowser window
		// here child window get open so try to take id of child window and switch on it
		Set<String> listofwindows = driver.getWindowHandles();
		Iterator<String> iterator = listofwindows.iterator();
		// parent window id :
		parentWindowId = iterator.next();
		// child window id :
		childWindowId = iterator.next();
		childWindowId2 = iterator.next();

		driver.switchTo().window(childWindowId2);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// scroll to add to cart Button
		homePage.downScroll();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		homePage.selectAddToCart();
		// see the quantity increse or not by clicking in cart
		homePage.cartListButton();

	}

	/*******************************************************************************************
	 * @Testcase_Description : user should be  able to add multiple item to cart
	 * @throws IOException
	 * 
	 ******************************************************************************************/

	@Test
	public void tc_006_addMultipleItemToCart() throws IOException {
		HomePage homePage = new HomePage(driver);

		// Serch the Product
		tc_001_SearchProduct();
		homePage.selectItem1();
		

	}

}
