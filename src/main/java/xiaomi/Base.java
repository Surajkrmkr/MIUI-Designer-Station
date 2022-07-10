package xiaomi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Base {


	 public static WebDriver driver;
	 public static WebDriverWait wait;
	 
	   @Test
	   @Parameters("browser")
	   public void setUp(String br)
	   {   	   
		   if(br.matches("firefox"))
		   {
			   System.setProperty("webdriver.gecko.driver","D:\\eclipse-workspace\\Pratesting\\geckodriver.exe");
			   driver=new FirefoxDriver();		   
		   }
		   if(br.matches("chrome"))
		   {
			   System.setProperty("webdriver.chrome.driver","D:\\Download(HDD)\\_Download\\geckodriver-v0.30.0-win64\\chromedriver.exe");
			   ChromeOptions options = new ChromeOptions().setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			   driver = new ChromeDriver(options);
		   }
		   driver.manage().window().maximize();
	   }
}
