package IntegrationTests.screens.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import UITestFramework.GenericMethods;



/**
 * contains all the locators present on the login Screen
*/

public class AndroidLoginScreen extends GenericMethods {

	public AndroidLoginScreen(WebDriver driver) {
		super(driver);
		
	}
	

	// Locators on the login screen
	
	public By userName = By.xpath("");
	public By password = By.xpath("");
	public By signInButton = By.xpath("");
	public By startedButton = By.id("");


	public By searchIcon = By.id("");
	public By titleBar = By.id("");
	
}