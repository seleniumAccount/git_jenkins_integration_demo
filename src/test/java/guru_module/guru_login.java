package guru_module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class guru_login {
	
	public Properties prop;
	public WebDriver driver;

	
	@Test
	public void propertiesReadingPattern() throws FileNotFoundException
	{
		
		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\Validations\\FlagMessages.properties");
		try {
			obj.load(objfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Drivers_Path = obj.getProperty("Driver_Folder_Path");
		String driver_Type=obj.getProperty("driver_Type");
		String App_Url=obj.getProperty("Application_Url");
		System.out.println(Drivers_Path);
		System.out.println(driver_Type);
		System.setProperty(driver_Type, Drivers_Path);
	    driver=new ChromeDriver();
		driver.get(App_Url);
		driver.manage().window().maximize();
		
		String userName=obj.getProperty("guru_username");
		System.out.println(userName);
		String password=obj.getProperty("guru_password");
		System.out.println(password);
		WebElement username=driver.findElement(By.xpath("//input[@name='uid']"));
		username.sendKeys(userName);
		
		WebElement pasword=driver.findElement(By.xpath("//input[@name='password']"));
		pasword.sendKeys(password);
		
		WebElement btnLogin=driver.findElement(By.xpath("//input[@name='btnLogin']"));
		btnLogin.click();	
		
		driver.close();

}
}
