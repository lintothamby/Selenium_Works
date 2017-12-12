import static org.testng.Assert.assertTrue;

import java.lang.String;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Linto Thamby
 *  
 * WalletHub-Automation Script
 * Facebook Post Message
 * TestNG Framework
 * Browser- Google Chrome
 */


public class WalletHub {

	WebDriver driver;
	String reviewData = "asdsdsddsdsdsdsddsdssdsd sasadsdsd dasdsssas ssasasas asasas  sasasas sasasasas asasasasas asasasa asasasasa asasasasa asasasa asasasa asasasa asasasa asasasa asasa sasasasas asasasa assasas asasasas";
    String useremail = "testabc007@gmail.com"; 
    String username = "testabc007";
    String password = "W123@hub"; 
	@BeforeClass
	public void setup() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// configureGenericCapabilities(capabilities);
		// TODO Auto-generated constructor stub
		ChromeOptions chromeOptions = new ChromeOptions();

		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("--start-maximized");
		
		System.setProperty("webdriver.chrome.driver","src\\chromedriver.exe");
		chromeOptions.addArguments("test-type");

		capabilities.setBrowserName("chrome");
		capabilities.setAcceptInsecureCerts(true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	 @Test(priority = 1)
	public void register(){
		driver.get("https://wallethub.com/join/light");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Email Address')]")).sendKeys(useremail);
		driver.findElement(By.name("pw1")).sendKeys(password);
		driver.findElement(By.name("pw2")).sendKeys(password);
		driver.findElement(By.cssSelector(".check>span")).click();
		driver.findElement(By.cssSelector(".btn.blue.touch-element-cl")).click();
		waitForVisibiltyOfElement(By.xpath("//h2[contains(.,'Thank you for registering with WalletHub!')]"));
	}

	@Test(priority = 2)
	public void rating() {
		driver.get("http://wallethub.com/profile/test_insurance_company/");
		waitForVisibiltyOfElement(By.xpath("//span[contains(.,'Write a Review')]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:window.scrollBy(0,350)");

		try {
			waitForVisibiltyOfElement(By.xpath("//span[contains(@class,'cta_arrow down')]"));
			driver.findElement(By.xpath("//span[contains(@class,'cta_arrow down')]")).click();
		} catch (Exception e) {
			// do nothing
		}

		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span[contains(@class,'wh-rating')]"));
		builder.moveToElement(element).build().perform();

		waitForVisibiltyOfElement(By.xpath("//div[contains(@class,'wh-rating-choices-holder')]"));


		for (int stars = 1; stars <= 4; stars++) {
			Actions builder2 = new Actions(driver);
			WebElement element2 = driver
					.findElement(By.xpath("//div[contains(@class,'wh-rating-choices-holder')]/a[" + stars + "]"));
			builder2.moveToElement(element2).build().perform();
			if (stars == 4) {
				element2.click();
			}
		}

		waitForVisibiltyOfElement(By.cssSelector(".dropdown-title"));

		WebElement dropdown = driver.findElement(By.cssSelector(".dropdown-title"));

		dropdown.click();
		waitForVisibiltyOfElement(By.xpath("//a[contains(@data-target,'Health')]"));
		driver.findElement(By.xpath("//a[contains(@data-target,'Health')]")).click();

		driver.findElement(By.name("review")).sendKeys(reviewData);
		driver.findElement(By.xpath("//input[contains(@type,'submit')]"));
		
		waitForVisibiltyOfElement(By.xpath("//h1[contains(.,'Awesome!')]"));
		
		
	}
	
	@Test(priority = 3)
	public void review() {
	
		driver.get("https://wallethub.com/profile/"+username+"/reviews/");		
		//driver.get("https://wallethub.com/profile/test_ecommit2014/reviews/");
		waitForVisibiltyOfElement(By.className("name"));
	 Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'"+reviewData+"')]")).isDisplayed());
	 
	}
	

	private void waitForVisibiltyOfElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}

}