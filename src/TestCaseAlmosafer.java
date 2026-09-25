import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

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
    Random rand=new Random();
	
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
		WebElement TheFooter=driver.findElement(By.tagName("footer"));
		Boolean ActualLogo =TheFooter.findElement(By.xpath("//img[@alt='qitaf']")).isDisplayed();
		Boolean ExpectedLogo=true;  
		
	    Assert.assertEquals(ActualLogo, ExpectedLogo);
	}
	@Test(priority=5)
	public void CheckDepatureDate()  {
		int Today =LocalDate.now().getDayOfMonth();
		int Tomorrow =LocalDate.now().plusDays(1).getDayOfMonth();
		int DayAfterTomorrow =LocalDate.now().plusDays(2).getDayOfMonth();
        
        //split قسم النص بعد كل space 0 1 2 3 
        String ActualDepatureDate=driver.findElement(By.id("testIdPickerPrefix__DatePicker__DepartureDate")).getAttribute("value").split(" ")[1];
        //int actualDepatureDate=Integer.parseInt(DepatureDate);
        String expectedDepatureDate=Integer.toString(Tomorrow);
        Assert.assertEquals(ActualDepatureDate, expectedDepatureDate);
	}
	@Test(priority=6)
	public void CheckReturnDate() {
		int DayAfterTomorrow =LocalDate.now().plusDays(2).getDayOfMonth();
        
        String ActualReturnDate=driver.findElement(By.id("testIdPickerPrefix__DatePicker__ArrivalDate")).getAttribute("value").split(" ")[1];
        String expectedReturnDate=Integer.toString(DayAfterTomorrow);
        Assert.assertEquals(ActualReturnDate, expectedReturnDate);
	}
	@Test(priority=7)
	public void RandomlyChangeThelanguage() throws InterruptedException {
		
		String ArabicWebSite="https://www.almosafer.com/ar";
		String EnglishWebSite="https://www.almosafer.com/en";
		String [] MyWebSite= {ArabicWebSite,EnglishWebSite};
		int RandomIndex =rand.nextInt(MyWebSite.length);
		driver.get(MyWebSite[RandomIndex]);
		
		Thread.sleep(2000);
		if(driver.getCurrentUrl().contains("ar")) {
			String ActualLanguage =	driver.findElement(By.tagName("html")).getAttribute("lang");
		    String ExpectedLanguage="ar";
		    Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		}else
		{
			String ActualLanguage =	driver.findElement(By.tagName("html")).getAttribute("lang");
		    String ExpectedLanguage="en";
		    Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		}
	}
	
	@AfterTest
    public void SetUp() {
		
	}
}
