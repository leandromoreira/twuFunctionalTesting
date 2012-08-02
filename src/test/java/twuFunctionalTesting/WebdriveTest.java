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

//the next big change I would do would be the PageObject pattern :P
//altouhg I don't like the work it´s a good ideia check it out http://code.google.com/p/selenium/wiki/PageObjects
public class PlaceOrderTest {
	
	//for sure the class itself should be place in a separated file
	public static class PlaceOrderPage{
		private final WebDriver browser;
		public PlaceOrderPage(final WebDriver browser){
			this.browser =  browser;
		}
		
		public void goToHome(){
			browser.get("http://localhost:8080/twuFunctionalTesting/");
		}
		
		public void exit(){
			browser.quit();
		}
		
		public void clickAtPlaceAnOrder(){
			browser.findElement(By.partialLinkText("Place an Order")).click();	
		}
		
		public void fillNameWith(final String valeu){
			browser.findElement(By.id("name_field")).sendKeys(value);
		}

		public void fillEmailWith(final String valeu){
			browser.findElement(By.id("email_field")).sendKeys(value);
		}
		
		public void submit(){
			browser.findElement(By.id("submitButton")).click();
		}
		
		public String getTextByClass(final String className){
			page.getValueByClass()browser.findElement(By.className("header")).getText();
		}
		
		public String getValueByName(final String className){
			page.getValueByClass()browser.findElement(By.className("header")).getText();
		}
		
		public void select(int order){
			browser.findElements(By.name("item")).get(order).click();
		}
	}
	
	//one of the great benefits is that we can eliminate selenium or any other web automate framework
	//so if you want to change its internal you can do that easily 
	//btw when you do this pro, you usually create a super Page object full of common "page" methods
	//and you can create a "default" driver in order to avoid the Browser dependency as well
	@Before
	public void setup(){
		page = new PlaceOrderPage(new FirefoxDriver());
	}
	
	@After
	public void down(){
		page.exit();
	}
	
	@Test
	public void shouldPlaceAnOrder(){
		page.goToHome();
		page.clickAtPlaceAnOrder();
		
		page.fillNameWith("Batman");
		page.fillEmailWith("batman@gotham.com");
		
		page.submit();
		
		String orderMsg = page.getTextByClass("header");
		assertEquals("Order Saved!", orderMsg);
	}
	
	@Test
	public void shouldCalculateTax(){
		int FOURTH_ITEM = 3;
		page.goToHome();
		page.clickAtPlaceAnOrder();
		
		page.fillNameWith("Batman");
		page.fillEmailWith("batman@gotham.com");
		
		page.select(FOURTH_ITEM);
		
		page.submit();
		
		String total =  page.getValueByName("total");
		assertEquals("60.5", total);
	}
}
