package window_handles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.Set;

import static utilities.WebUtils.*;

public class MultipleWindows {

    @Test
    public void verifyAlertMessageInDifferentTab() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("Extensions/AdBlock.crx"));

        driver.navigate().to("https://www.hyrtutorials.com/p/window-handles-practice.html");

        // getWindowHandle() method returns a unique ID for the current page as a String
        String mainPage = driver.getWindowHandle();

        // opening the new tab
        clickElement(By.id("newTabBtn"), timeDuration);

        // getWindowHandles() returns list of all open windows/tabs as a unique Set of Strings
        Set<String> pages = driver.getWindowHandles();
        String secondPage = null;

        for (String page : pages) {
            if (!page.equals(mainPage)) {
                secondPage = page;
                driver.switchTo().window(page);
            }
        }

        // clicking on the Alert btn
        clickElement(By.id("alertBox"), timeDuration);
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();

        String actualAlertMsg = alert.getText();
        System.out.println("Alllert messsaggggeee : " + actualAlertMsg);
        String expectedAlertMsg = "I am an alert box!";

        alert.accept();
        Assertions.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert message does not match expected");

        Thread.sleep(2000);
        driver.quit();
    }
}
