package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class WebUtils {
    public static WebDriver driver = new ChromeDriver();
    public static Duration timeDuration = Duration.ofSeconds(10);

    //Static method to wait for an element to be visible with a specified timeout
    public static WebElement waitForElement(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Static method to click an element using explicit wait
    public static void clickElement(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    //Static method to send keys to an element using explicit wait
    public static void sendKeysToElement(By locator, String text, Duration timeout) {
        WebElement element = waitForElement(locator, timeout);
        element.sendKeys(text);
    }

    //Static method to wait for a List of WebElements
    public static List<WebElement> waitForListElements(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    //Static method to take a screenshot of the web page
    public static void takeScreenshot() {
        //Took screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File destinationFile = new File("src/test/resources/screenshots/screenshot_" + timestamp + ".png");

        try {
            FileUtils.copyFile(screenshot, destinationFile);
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Static method to take a screenshot of the web page
    public static void takeScreenshotOfWebElement(By locator) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File destinationFile = new File("src/test/resources/screenshots/element_screenshot_" + timestamp + ".png");

        WebElement element = waitForElement(locator, Duration.ofSeconds(10));

        File screenshot = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, destinationFile);
            System.out.println("Screenshot of element saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}























