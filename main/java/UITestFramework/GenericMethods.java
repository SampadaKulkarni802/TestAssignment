package UITestFramework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GenericMethods {

    WebDriver driver = null;

    // common timeout for all tests can be set here
    public final int timeOut = 40;

    public GenericMethods(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * method verify whether element is present on screen
     *
     * @param targetElement element to be present
     * @return true if element is present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     *                              or otherwise occupied, and the thread is interrupted, either before
     *                              or during the activity.
     */
    public Boolean isElementPresent(By targetElement) throws InterruptedException {
        Boolean isPresent = driver.findElements(targetElement).size() > 0;
        return isPresent;
    }

    /**
     * method to hide keyboard
     */
    public void hideKeyboard() {
        ((AppiumDriver) driver).hideKeyboard();
    }

    
    /**
     * method to wait for an element to be visible
     *
     * @param targetElement element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForVisibility(By targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + targetElement);
            throw e;

        }
    }

    
    /**
     * method to tap on the screen on provided coordinates
     *
     * @param xPosition x coordinate to be tapped
     * @param yPosition y coordinate to be tapped
     */
    public void tap(double xPosition, double yPosition) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("startX", xPosition);
        tapObject.put("startY", yPosition);
        js.executeScript("mobile: tap", tapObject);
    }


    /**
     * method to find an element
     *
     * @param locator element to be found
     * @return WebElement if found else throws NoSuchElementException
     */
    public WebElement findElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element;
        } catch (NoSuchElementException e) {
            Log.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
            throw e;
        }
    }

    /**
     * method to find all the elements of specific locator
     *
     * @param locator element to be found
     * @return return the list of elements if found else throws NoSuchElementException
     */
    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> element = driver.findElements(locator);
            return element;
        } catch (NoSuchElementException e) {
            Log.logError(this.getClass().getName(), "findElements", "element not found" + locator);
            throw e;
        }
    }

    /**
     * method to get message test of alert
     *
     * @return message text which is displayed
     */
    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            return alertText;
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }

    /**
     * method to verify if alert is present
     *
     * @return returns true if alert is present else false
     */
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }

    /**
     * method to Accept Alert if alert is present
     */

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /**
     * method to Dismiss Alert if alert is present
     */

    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    
    /**
     * method to set the context to required view.
     *
     * @param context view to be set
     */
    public void setContext(String context) {

        Set<String> contextNames = ((AppiumDriver) driver).getContextHandles();

        if (contextNames.contains(context)) {
            ((AppiumDriver) driver).context(context);
            Log.info("Context changed successfully");
        } else {
            Log.info(context + "not found on this page");
        }

        Log.info("Current context" + ((AppiumDriver) driver).getContext());
    }

    /**
     * method to long press on specific element by passing locator
     *
     * @param locator element to be long pressed
     */
    public void longPress(By locator) {
        try {
            WebElement element = driver.findElement(locator);

            TouchAction touch = new TouchAction((MobileDriver) driver);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withElement(ElementOption.element(element));
            touch.longPress(longPressOptions).release().perform();
            Log.info("Long press successful on element: " + element);
        } catch (NoSuchElementException e) {
            Log.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
            throw e;
        }

    }

  

    /**
     * method to swipe on the screen on provided coordinates
     *
     * @param startX   - start X coordinate to be tapped
     * @param endX     - end X coordinate to be tapped
     * @param startY   - start y coordinate to be tapped
     * @param endY     - end Y coordinate to be tapped
     * @param duration duration to be tapped
     */

    public void swipe(double startX, double startY, double endX, double endY, double duration) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        // swipeObject.put("touchCount", 1.0);
        swipeObject.put("startX", startX);
        swipeObject.put("startY", startY);
        swipeObject.put("endX", endX);
        swipeObject.put("endY", endY);
        swipeObject.put("duration", duration);
        js.executeScript("mobile: swipe", swipeObject);
    }


    static String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + 	".instance(0));";
    }

}   
     
    

            