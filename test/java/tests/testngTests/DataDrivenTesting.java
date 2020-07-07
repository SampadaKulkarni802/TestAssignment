import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

import test.testngTests.LoginTest;
import IntegrationTests.screens.android.TVSearchScreen;

public class DataDrivenTesting extends CreateSession
{

XSSFWorkbook wb;
static XSSFSheet sheet;
FileInputStream fis;

@BeforeTest
public void getExcel() throws IOException
{
File src = new File("F:\\java\\src\\main\\java\\TestData\\TVdata.xlsx");
fis = new FileInputStream(src);
wb = new XSSFWorkbook(fis);
sheet = wb.getSheetAt(0);
}

@Test
public void TVData() throws MalformedURLException, InterruptedException
{
     LoginTest login = new LoginTest();
     login.LoginVerification();

			
	for(int i =0 ; i<5; i++)
	{
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	WebElement searchTV = driver.findElement(TVNameEditBox).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	String data = sheet.getRow(i).getCell(0).getStringCellValue();
	searchTV.sendKeys(data);
	
	driver.pressKeyCode(AndroidKeyCode.ENTER);
	Thread.sleep(3000);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	driver.findElement(SearchButton).click();
	Thread.sleep(10000);

	driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	
	}
}
}