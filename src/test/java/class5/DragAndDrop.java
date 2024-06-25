package class5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static utilities.WebUtils.driver;
import static utilities.WebUtils.waitForElement;

public class DragAndDrop {
    Actions actions = new Actions(driver);

    @Test
    public void dragNDrop() {
        driver.get("https://demo.automationtesting.in/Static.html");
        WebElement sourceElement = waitForElement(By.xpath("//img[@id='angular']"), Duration.ofSeconds(10));
        WebElement targetElement = waitForElement(By.xpath("//div[@id='droparea']"), Duration.ofSeconds(10));

        actions.dragAndDrop(sourceElement, targetElement).perform();
    }
}















