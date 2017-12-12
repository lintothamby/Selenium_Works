
/**
 * 
 */
import java.lang.String;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author lthamby
 *
 */
public class Jbegin {

	WebDriver driver;
	
	String fbUsername="test.ecommit2014@gmail.com";
	String fbPassword="welcome@12345";

	/**
	 * 
	 */
	@BeforeClass
	public void setup() {
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		// configureGenericCapabilities(capabilities);
		// TODO Auto-generated constructor stub
		ChromeOptions chromeOptions = new ChromeOptions();
		
		  chromeOptions.addArguments("--disable-notifications");
		  chromeOptions.addArguments("--start-maximized");
		 // System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\GeckoDriver\\chromedriver.exe"); /* gecko Driver Path */
		  System.setProperty("webdriver.chrome.driver","src\\chromedriver.exe");
		  chromeOptions.addArguments("test-type");
		  
		  capabilities.setBrowserName("chrome");
		  capabilities.setAcceptInsecureCerts(true);
		  capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		/*
		 * FirefoxProfile profile = new FirefoxProfile();
		 * profile.setPreference("app.update.auto", false);
		 * profile.setPreference("app.update.enabled", false); FirefoxOptions
		 * options = new FirefoxOptions();
		 * options.setCapability(FirefoxDriver.PROFILE, profile);
		 */

		driver = new ChromeDriver(chromeOptions);
		driver.get("http://www.facebook.com");
	}

	@Test
	//@Parameters({"userName", "password"})
	private void loginTest() throws Exception {
		driver.findElement(By.name("email")).sendKeys(fbUsername);
		driver.findElement(By.name("pass")).sendKeys(fbPassword);
		driver.findElement(By.id("loginbutton")).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		Thread.sleep(10000);
		//WebElement activeElement = driver.switchTo().activeElement(); 
		//String className =  activeElement.getAttribute("clearfix _ikh"); 
		driver.findElement(By.xpath("//textarea")).sendKeys("Hello World");
		//driver.findElement(By.xpath("xpathExpression")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Post']"))).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Hello World']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		Thread.sleep(1000);
		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}

}
