import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCaseAlmosafer {
	WebDriver driver =new ChromeDriver();
	String MyWebSite="https://www.almosafer.com/en";

	
	@BeforeTest
	public void MySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(MyWebSite);
		
		WebElement ButtonForTheCurrency =driver.findElement(By.id(":r0:"));
		ButtonForTheCurrency.click();
		}
	
	
	@Test(priority=1)
	public void CheckTheEnglishLanguageIsDefault() {
	    String ActualLanguage =	driver.findElement(By.tagName("html")).getAttribute("lang");
	    String ExpectedLanguage="en";
	    
	    Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}
	@Test(priority=2)
	public void CheckTheDefaultCurrencyIsSAR() {
		String ActualDefaultCurrency =driver.findElement(By.xpath("//div[@data-testid='Header__CurrencySelector']")).getText();
		String ExpectedDefaultCurrency="SAR";   
		
	    Assert.assertEquals(ActualDefaultCurrency, ExpectedDefaultCurrency);
	}
	@Test(priority=3)
	public void CheckContactNumber() {
		String ActualNumber=driver.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.alm-desktop-1s3wk81")).getText();
		String ExpectedNumber="+966554400000";
		
	    Assert.assertEquals(ActualNumber, ExpectedNumber);
	}
	@Test(priority=4)
	public void CheckQitafLogo() {
		Boolean ActualLogo =driver.findElement(By.xpath("//img[@alt='qitaf']")).isDisplayed();
		Boolean ExpectedLogo=true;  
		
	    Assert.assertEquals(ActualLogo, ExpectedLogo);
	}
	@Test(priority=5)
	public void Z() {
		
	}
	
	@AfterTest
    public void SetUp() {
		
	}
}
