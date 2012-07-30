package twuFunctionalTesting;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;


public class WebdriveTest {


	@Test
	public void shouldPlaceAnOrder(){
		WebDriver browser = new FirefoxDriver();
		browser.get("http://localhost:8080/twuFunctionalTesting/");
		browser.findElement(By.partialLinkText("Place an Order")).click();

		WebElement name = browser.findElement(By.id("name_field"));
		name.sendKeys("Batman");

		WebElement email = browser.findElement(By.id("email_field"));
		email.sendKeys("batman@gotham.com");

		WebElement combo = browser.findElement(By.id("items"));
		browser.findElement(By.id("submitButton")).click();
		
		String sourcePage = browser.getPageSource();
		browser.quit();
		assertTrue(sourcePage.contains("Order Saved!"));
	}

}
