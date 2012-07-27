package twuFunctionalTesting;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriveTest {

	
	@Test
	public void shouldOpenAppPageWithSelenium(){
		WebDriver firefox = new FirefoxDriver();
		firefox.get("http://localhost:8080/twuFunctionalTesting/");
	
		
	}
	
}