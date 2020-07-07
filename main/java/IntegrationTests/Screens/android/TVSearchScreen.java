package IntegrationTests.screens.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import UITestFramework.GenericMethods;



/**
 * contains all the locators present on the login Screen
*/

public class TVSearchScreen extends GenericMethods {

	public TVSearchScreen(WebDriver driver) {
		super(driver);
		
	}
	

	// Locators on the TV Search Screen
	
	public By TVNameEditBox = By.xpath("");
	public By SearchButton = By.id("");
	public By titleBar = By.id("");
	
}