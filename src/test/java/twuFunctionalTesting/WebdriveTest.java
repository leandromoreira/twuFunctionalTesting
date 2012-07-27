package twuFunctionalTesting;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebdriveTest {

	
	@Test
	public void shouldSeePlaceAnOrderInHome(){
		WebDriver browser = new HtmlUnitDriver();
		browser.get("http://localhost:8080/twuFunctionalTesting/");

		String sourcePage = browser.getPageSource();
		assertTrue(sourcePage.contains("Place an Order"));	
	}
	
}
