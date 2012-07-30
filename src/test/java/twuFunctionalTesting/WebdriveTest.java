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
		
		String orderMsg = browser.findElement(By.className("header")).getText();
		assertEquals("Order Saved!", orderMsg);
		
		browser.quit();
	}
	
	@Test
	public void shouldCalculateTax(){
		WebDriver browser = new FirefoxDriver();
		browser.get("http://localhost:8080/twuFunctionalTesting/");
		
		browser.findElement(By.partialLinkText("Place an Order")).click();
		
		browser.findElement(By.id("name_field")).sendKeys("Batman");

		browser.findElement(By.id("email_field")).sendKeys("batman@gotham.com");
		
		browser.findElements(By.name("item")).get(3).click();
		
		//Would use code bellow to dynamically calculate total price:
		
		//WebElement price = browser.findElement(By.id("current_price"));
		//WebElement tax = browser.findElement(By.id("current_tax"));
		//WebElement total = browser.findElement(By.id("current_total"));
		
		browser.findElement(By.id("submitButton")).click();
		
		String total = browser.findElement(By.name("total")).getAttribute("value");

		assertEquals("60.5", total);
		
		browser.quit();
		
	}

}
