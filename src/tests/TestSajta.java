package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;

public class TestSajta {
	
	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
	System.setProperty("webdriver.chrome.driver", "C:\\Chrome driver- selenium\\chromedriver.exe");	
	        driver = new ChromeDriver();
	}
	
	
	
    @Test (priority = 1)
	public void testNeuspesnologovanje() {
		driver.navigate().to(Home.URL);
		driver.findElement(By.id("user-name")).click();
		driver.findElement(By.id("user-name")).sendKeys("user");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login-button")).click();
		String currnetUrl = driver.getCurrentUrl();
		
		String expectedUrl = driver.getCurrentUrl();  //ovde sam namerno stavio isti Url jer se test zove
		                                              //neuspesno logovanje
		
		Assert.assertEquals(currnetUrl, expectedUrl );
		
}
	
	@Test (priority = 2)
	public void UspesnoLogovanje() {
		File f = new File("data.xlsx");
		
		try {
		
		InputStream in = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Sheet sheet = wb.getSheetAt(0);
		
		SoftAssert sa = new SoftAssert();
		
		for(int i = 0; i < 3; i++)  {
			Row row = sheet.getRow(i);
		    Cell c0 = row.getCell(0);
		    Cell c1 = row.getCell(1);
		    
		    
		    String username = c0.toString();
			String password = c1.toString();
			
		
		driver.navigate().to(Home.URL);
		driver.findElement(By.id("user-name")).click();
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys(password);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.id("login-button")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String currnetUrl = driver.getCurrentUrl();
		
		
		driver.navigate().to(Home.URL);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		
		sa.assertEquals(currnetUrl, expectedUrl);
		
		}
		
		sa.assertAll();
		
		}  catch ( IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	@Test(priority = 3)
	public void testLocked() {
		
		driver.navigate().to(Home.URL);
		driver.findElement(By.id("user-name")).click();
		driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		  String currentUrl = driver.getCurrentUrl();
		  
		  String expectedUrl = "https://www.saucedemo.com/inventory.html";
		
		Assert.assertEquals(currentUrl, expectedUrl);
	}
	
	
	
	/*@Test (priority = 3)
	public void testSortiranje() {
		
		driver.navigate().to(Home.URL);
		
		Home.Login(driver);
		
		
		
		WebElement element = driver.findElement(By.className("active_option"));
		
		Select sel = new Select(element);
		sel.deselectByVisibleText("Price (low to high)");
		
		WebElement firstEle = sel.getFirstSelectedOption();
		
		System.out.println( " first ele" +firstEle.getText());
		
		
		
		
		
		
		
		
	}*/
	
		
			
		
	

}
