package com.amazon.testcases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.amazon.base.Base;
import com.amazon.page.CartList;
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
		homePage.enterText("Sport");
		homePage.clickSearch();
	}

	/***************************************************************************************
	 * @Testcase_Description :able to select from dropdown and able search
	 * 
	 ****************************************************************************************/
//	@Test(priority = 2)
	public void tc_002_dropdown() {
		HomePage homePage = new HomePage(driver);
		homePage.selectDropdownByIndex(3);
//		homePage.selectDropdownByName("Computers & Accessories");
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
		homePage.select_Dropdowm_For_IncreaseQuntity();
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	 * @Testcase_Description : User should able to add multiple item in the cart
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 ******************************************************************************************/

//	@Test(priority = 6)
	public void tc_006_addMultipleItemToCart() throws IOException, InterruptedException {
		HomePage homePage = new HomePage(driver);

		// Serch the Product
		tc_001_SearchProduct();
		Thread.sleep(3000);
		
		
		// 1st Item Select 
		homePage.selectItem1();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// child browser window will open so handle it first
		Set<String> listofwindows = driver.getWindowHandles();
		System.out.println(listofwindows.size());
		Iterator<String> iterator = listofwindows.iterator();
		// parent window id :
		parentWindowId = iterator.next();		
		System.out.println(parentWindowId);

		// child window id :
		childWindowId = iterator.next();	
		System.out.println(childWindowId);
		
		driver.switchTo().window(childWindowId);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		homePage.downScroll_2();
		homePage.selectAddToCart2();
		
		
		
		// 	Now 2nd Item Going to be select 
		driver.get("https://www.amazon.in/s?k=Sport&ref=nb_sb_noss_2");
		Thread.sleep(2000);
		homePage.selectItem2();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// child browser window will open so handle it first
		listofwindows = driver.getWindowHandles();
		System.out.println(listofwindows.size());
		iterator = listofwindows.iterator();
		// parent window id :
		parentWindowId = iterator.next();		
		System.out.println(parentWindowId);

		// child1 window id :
		childWindowId = iterator.next();	
		System.out.println(childWindowId);

		// child2 window id :
		childWindowId2 = iterator.next();	
		System.out.println(childWindowId2);

		driver.switchTo().window(childWindowId2);
		homePage.downScroll_2();
		homePage.selectAddToCart2();

		// to see how many item in the cart list
		homePage.cartListButton();
	}

	
	
	/*******************************************************************************************
	 * @throws InterruptedException 
	 * @Testcase_Description : user should be able to remove item to cart
	 * @throws IOException
	 * 
	 ******************************************************************************************/
//	@Test(priority = 7)
	public void tc_007_removeFromCart() throws IOException, InterruptedException
	{
		HomePage homePage = new HomePage(driver);
		tc_006_addMultipleItemToCart();
		Thread.sleep(4000);
		homePage.deleteItem();
		Thread.sleep(1000);
		homePage.deleteItem();

		
	}
	
	/***********************************************************************************
	 * @throws InterruptedException 
	 * @Description : Select item to cart
	 ************************************************************************************/
	
	@Test
	public void clickToItemDescription() throws InterruptedException
	{
		HomePage homePage = new HomePage(driver);
		CartList cart = new CartList(driver);
		tc_003_addOnetoCart();
		Thread.sleep(3000);
		cart.itemDescription();
		
	}
}
