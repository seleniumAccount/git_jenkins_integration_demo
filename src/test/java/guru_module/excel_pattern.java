package guru_module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class excel_pattern {
        
	//global variables declaration
	
	public static HashMap<String,String> data;
	public static File src;
	public static FileInputStream fin;
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static int rows=0;
	public static int columns=0;
	public static String key;
	public static String value;
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		readData("./testdata.xlsx","Sheet1","Guru");
		
		String userName=data.get("userName");
		System.out.println(userName);
		String password=data.get("Password");
		System.out.println(password);
		System.setProperty("webdriver.chrome.driver","./drivers_folder/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
		
		
		WebElement username=driver.findElement(By.xpath("//input[@name='uid']"));
		username.sendKeys(userName);
		
		WebElement pasword=driver.findElement(By.xpath("//input[@name='password']"));
		pasword.sendKeys(password);
		
		WebElement btnLogin=driver.findElement(By.xpath("//input[@name='btnLogin']"));
		btnLogin.click();	
		
	}

                                          
	public static Map<String,String> readData(String path,String SheetName,String TestCaseName) throws IOException
	{
		data=new HashMap<String,String>();
		src=new File(path);
		try {
			fin=new FileInputStream(src);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelWBook=new XSSFWorkbook(fin);
		ExcelWSheet=ExcelWBook.getSheet(SheetName);
                //rows,columns getting code interview
		rows=ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
		columns=ExcelWSheet.getRow(0).getLastCellNum();
		
		for(int i=1; i<=rows;i++)
		{
			String Name=ExcelWSheet.getRow(i).getCell(0).getStringCellValue();
			if(Name.equals(TestCaseName))
			{
				for(int j=1;j<columns;j++)
				{
					key=ExcelWSheet.getRow(0).getCell(j).getStringCellValue();
					value=ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					data.put(key,value);
				}
			}
			
		}
		
		return data;
		
	}

}



