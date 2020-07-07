package UITestFramework;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import logger.Log;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


/**
 * contains all the methods to create a new session and destroy the 
 * session after the test(s) execution is over. Each test extends
 *  this class.
 */
public class CreateSession  {

	public WebDriver driver = null;
	Properties configFile ;
	protected static Properties lobConfigProp = new Properties();
	protected static Properties localeConfigProp = new Properties();
	protected FileInputStream configFis, lobConfigFis, localeConfigFis;	
	public Properties testDataFile;
	private final String CONFIG_FILE_PATH="F:\\java\\src\\main\\java\\config\\config.properties";
	protected File file = new File("");
	Properties configProp = new Properties();
	String OS;



	/** 
	 * this method creates the driver depending upon the passed parameter (android or iOS)
	 *  and loads the config properties file.
	 * @param os android or iOS
	 * @param methodName - name of the method under execution  
	 * @throws Exception issue while loading properties files or creation of driver.
	 */
	@Parameters({"os"})
	@BeforeMethod
	public  void createDriver(String os, Method methodName) throws Exception{

	propertiesFileLoad(os);

	File propertiesFile = new File(file.getAbsoluteFile() + "F:\\java\\src\\main\\java\\log4j.properties");
		PropertyConfigurator.configure(propertiesFile.toString());
		Log.info("--------------------------------------");



		if (os.equalsIgnoreCase("android")){
			String buildPath = choosebuild(os);
			androidDriver(buildPath, methodName);
			Log.info("Android driver created");

		}																		         
		else if (os.equalsIgnoreCase("iOS")){
			String buildPath = choosebuild(os);
			iOSDriver(buildPath, methodName);
			Log.info("iOS driver created");
		}
	}

	/** 
	 * this method quit the driver after the execution of test(s) 
	 */
	@AfterMethod
	public void teardown(){
		Log.info("Shutting down driver");
		driver.quit();
	}



	/** 
	 *  this method creates the android driver
	 *  @param buildPath - path to pick the location of the app
	 *  @param methodName - name of the method under execution 
	 * @throws MalformedURLException Thrown to indicate that a malformed URL has occurred.
	 */
	public void androidDriver(String buildPath, Method methodName) throws MalformedURLException{
		File app = new File(buildPath);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("appPackage", "");
		capabilities.setCapability("appActivity", "");
		capabilities.setCapability("name", methodName.getName());
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("automationName", "UiAutomator2");
		driver = new AndroidDriver( new URL("http://localhost:4723/wd/hub"), capabilities);

	}

	/** 
	 *  this method creates the iOS driver
	 *  @param buildPath- path to pick the location of the app
	 *  @param methodName- name of the method under execution
	 * @throws MalformedURLException Thrown to indicate that a malformed URL has occurred.
	 */
	public void iOSDriver(String buildPath, Method methodName) throws MalformedURLException {
		File app = new File(buildPath);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","iOS");
		capabilities.setCapability("platformVersion", "");
		capabilities.setCapability("appiumVersion", "");
		capabilities.setCapability("name", methodName.getName());
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,""); 
		capabilities.setCapability("app", app.getAbsolutePath());
		driver  = new IOSDriver( new URL("http://localhost:4723/wd/hub"), capabilities);

	}



	/** 
	 *  this method loads properties files config and file having test data.
	 * @param platform android or ios, to load specific test data file.
	 * @throws Exception property files are not loaded successfully
	 */
	public void propertiesFileLoad(String platform) throws Exception{
		configFis = new FileInputStream(file.getAbsoluteFile()
				+ CONFIG_FILE_PATH);
		configProp .load(configFis);
	}


	public String choosebuild(String invokeDriver){
		String appPath = null;
		if(invokeDriver.equals("android")){
			appPath = configProp.getProperty("AndroidAppPath");
			return appPath;
		}
		else if(invokeDriver.equals("iOS")){
			appPath = configProp.getProperty("iOSAppPath");
			return appPath;
		}

		return appPath;
	}



}