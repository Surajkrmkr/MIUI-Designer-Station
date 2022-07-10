package xiaomi;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Xiaomi extends Base {
	final String homeUrl ="https://in.zhuti.designer.intl.xiaomi.com/home?userId=1686236300&productState=ALL&productType=THEME&perPage=30&currentPage=1";
	final String weekNo = "86";
	By signIn=By.xpath("//*[@id=\"app\"]/div/div[2]/div/button[1]");
	By emailId=By.xpath("//input[@name='account']");
	By pwd=By.name("password");
	By submit=By.xpath("//button[@type='submit']");
	By submitBtn=By.xpath("//*[@id=\"app\"]/section/div[2]/div[1]/div[1]/div/div[2]/button");
	By file=By.xpath("//*[@class=\"upload-area\"][3]//input");
	By description=By.xpath("//textarea[contains(@placeholder,'Describe this theme')]");
	By tags=By.xpath("//input[contains(@placeholder,'tags')]");
	By tagError=By.xpath("//div[contains(text(),'no such')]");
	By copyright=By.xpath("//input[contains(@accept,'.zip')]");
	By finalBtn=By.xpath("//span[contains(@slot,'footer')]/button");
	By cookieBtn=By.xpath("//*[@id=\"__cookie_tip\"]/div");
	final String[] keywords = {"Gradient","colorful","abstract","Cool","Dynamic lockscreen","date"}; //
	//"Curved edge display","clock","week","date","Dynamic lockscreen","month","Cool"
	
	@Test(dependsOnMethods="setUp" )
	public void searchData() throws Exception {
		File folder = new File("E:\\Xiaomi Contract\\THEMES\\Week"+weekNo);
		File[] listOfFiles = folder.listFiles();
				
		driver.get(homeUrl);
		Thread.sleep(5000);
		wait = new WebDriverWait(driver,120);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(signIn));
		Thread.sleep(3000);
		driver.findElement(signIn).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(emailId));
		driver.findElement(emailId).sendKeys("8895611584");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pwd));
		driver.findElement(pwd).sendKeys("suraj123");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(submit));
		driver.findElement(submit).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cookieBtn));
		driver.findElement(cookieBtn).click();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"app\"]/section/div[1]/div/ul/li[2]"))).perform();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[3]/ul/li[1]")));
		driver.findElement(By.xpath("/html/body/div[3]/ul/li[1]")).click();
		int count = 1;
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	System.out.println(file.getName());
				uploadingSingleFile(file.getName());
				System.out.println(count+ " : "+ file.getName()+" uploaded Successfully");
				count ++;
		    }
		}
		driver.close();


	}
	
		public void uploadingSingleFile(String path) throws Exception {
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		Thread.sleep(10000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(submitBtn));
		driver.findElement(submitBtn).click();
		
		  WebElement uploadElement= driver.findElement(file);

	        uploadElement.sendKeys("E:\\Xiaomi Contract\\THEMES\\Week"+weekNo+"\\"+path);
	       
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(description));
			driver.findElement(description).sendKeys("Features:\r\n"
					+ "- Digital Clock\r\n"
					+ "- Icons\r\n"
					+ "- Music\r\n"
					+ "- WeekDay");  
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tags));
			
			do {
				driver.findElement(tags).clear();
				for (String key : keywords) {
					driver.findElement(tags).sendKeys(key);
					Thread.sleep(2000);
					driver.findElement(tags).sendKeys(Keys.ENTER);
			}
			}while(driver.findElements(tagError).size()>0);

			 WebElement element= driver.findElement(copyright);
			 element.sendKeys("E:\\Xiaomi Contract\\THEMES\\copyright.zip");

			 JavascriptExecutor jse = (JavascriptExecutor)driver;
			 jse.executeScript("scroll(350, 0)");
			 Thread.sleep(5000);
//			 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(finalBtn));
			driver.findElement(finalBtn).click();
	
	}
}
