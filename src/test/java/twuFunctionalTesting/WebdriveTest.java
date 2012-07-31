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

//change the name of test to something MORE meaningful 
public class PlaceOrderTest {
	//since we will always use a driver(browser) then I will create a attribute to hold it in order to avoid object creation and let the code clear
	private WebDriver browser;
	//this is the root url (shared between tests) to our application
	private String placeOrderUrl = "http://localhost:8080/twuFunctionalTesting/";
	
	//all the work we need to do before each @Test execute themself, things like open file, connection...
	@Before
	public void setup(){
		browser = new FirefoxDriver();
	}
	
	//all the work we need to do after each @Test... things like close file, connection...
	@After
	public void down(){
		browser.quit();
	}

	//the list of benefits that we can have dealing with code with no duplication is long
	// * reuse (you can create another tests with different values only using the existing methods)
	// * extend (since you have a single point doing something it is easy to extend [try to imagine extending something like clickAtSubmmitButton if the button changed, if you didn't have a single point you should correct the entire code])
	@Test
	public void shouldPlaceAnOrder(){
		access(placeOrderUrl);
		clickAtLinkWithTextLike("Place an Order");
		
		fillFieldNameWith("Batman");
		fillFieldEmailWith("batman@gotham.com");
		
		clickAtSubmitButton();
		
		String orderMsg = browser.findElement(By.className("header")).getText();
		assertEquals("Order Saved!", orderMsg);
	}
	

	
	@Test
	public void shouldCalculateTax(){
		int FOURTH_ITEM = 3;
		access(placeOrderUrl);
		clickAtLinkWithTextLike("Place an Order");
		
		fillFieldNameWith("Batman");
		fillFieldEmailWith("batman@gotham.com");
		
		//hee the thing is legibility to code
		select(FOURTH_ITEM);
		
		clickAtSubmitButton();
		
		String total = browser.findElement(By.name("total")).getAttribute("value");
		assertEquals("60.5", total);
	}

	//always in order TO AVOID CODE DUPLICATION we create method (or function ... ) that will be used in shared places
	private access(String url){
		browser.get(url);
	}
	
	//still on things to AVOID CODE DUPLICATION and now you can see the name of method more easy to read and understand
	public void clickAtLinkWithTextLike(String text){
		browser.findElement(By.partialLinkText(text)).click();
	}
	
	//still avoiding code duplication
	private void fillFieldNameWith(String value){
		WebElement name = browser.findElement(By.id("name_field"));
		name.sendKeys(value);		
	}

	private void fillFieldEmailWith(String value){
		WebElement email = browser.findElement(By.id("email_field"));
		email.sendKeys(value);
	}
	
	private void clickAtSubmitButton(){
		browser.findElement(By.id("submitButton")).click();
	}
	
	public void select(int order){
		browser.findElements(By.name("item")).get(order).click();
	}
}
