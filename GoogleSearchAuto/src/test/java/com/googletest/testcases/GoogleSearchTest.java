package com.googletest.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.googletest.utilities.SearchTextUtil;

public class GoogleSearchTest {

	public String baseUrl = "http://www.google.com";
	public WebDriver driver;

	@AfterMethod 
	public void terminateBrowser() {
		// System.out.println("Am closing the browser");
		driver.quit();
	}

	@BeforeMethod
	public void launchBrowser() {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}

	@Test(priority = 1) // To Launch the browser and verify Google is opening.
	public void verifyHomepageTitle() {
		String expectedTitle = "Google";
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 2) // Test case 1
	public void googleTestSearch() {

		System.out.println("Wait for the page to load");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement searchTxt = driver.findElement(By.xpath("//input[@name='q']"));
		searchTxt.sendKeys("test");
		WebElement submitBtn = driver.findElement(By.xpath("//input[@name='btnK']"));
		submitBtn.click();

		SearchTextUtil.getSearchResults("test", driver);
	}

	@Test(priority = 3) // Test case 2
	public void googleTestSearchSpecialCharacter() {

		System.out.println("Wait for the page to load");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement searchTxt = driver.findElement(By.xpath("//input[@name='q']"));
		searchTxt.sendKeys(":" + Keys.ENTER);

		SearchTextUtil.getSearchResults(":", driver);
	}

	@Test(priority = 4) // Test case 3
	public void googleRelativeTestSearch() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement searchTxt = driver.findElement(By.xpath("//input[@name='q']"));
		searchTxt.sendKeys("ggle" + Keys.ENTER);
		SearchTextUtil.getSearchResults("google", driver);
	}

}
