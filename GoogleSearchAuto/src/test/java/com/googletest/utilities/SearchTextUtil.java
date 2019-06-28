package com.googletest.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class SearchTextUtil {

	/*
	 * This method is to search a particular string in google search page. Function
	 * name:getSearchResults(String<>) This function gets the list of all links in
	 * the page and search results clicks on the link and navigates to
	 * destination page and verifies the title of the page against search text.
	 * 
	 * 
	 */
	public static void getSearchResults(String searchText,WebDriver driver) {
		
		try {
			//System.out.println("Am in try block");
			boolean hasText = false;
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
			//System.out.println(links.size());
			for (int i = 1; i <= links.size(); i = i + 1) {
				hasText = links.get(i).getText().contains(searchText);
				if (links.get(i).getText().contains(searchText)) {
					//System.out.println("---------------" + hasText);
					links.get(i).click();
					Assert.assertEquals(hasText, driver.getTitle().contains(searchText));
					//System.out.println("Am out of search");
					}
			}
		} catch (Throwable e) {
			driver.navigate().refresh();
			System.out.println(e.getMessage());			
		}

	}
}
