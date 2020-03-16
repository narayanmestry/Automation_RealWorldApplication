package com.amazon.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.amazon.utility.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver = null;

	/**
	 * @throws IOException : used for initiate the browser
	 */
	@BeforeMethod
	public void startBrowser() throws IOException {
		if (Utility.fetchPropertyValue("browserName").toString().equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (Utility.fetchPropertyValue("browserName").toString().equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();		
		}
		driver.get("https://www.amazon.in/");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage(). timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	
	/**
	 * used to close the Browser
	 * @throws InterruptedException 
	 */
//	@AfterMethod
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.close();
		driver.quit();
	}
}
