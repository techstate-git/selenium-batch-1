package class5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static utilities.WebUtils.*;

public class Iframes {
    @Test
    public void iframe() {
        driver.get("https://iframetester.com/?url=https://demo.automationtesting.in/FileDownload.html");
        driver.switchTo().frame("iframe-window");
        clickElement(By.xpath("//a[@href='https://github.com//sakinala/AutomationTesting/raw/master/samplefile.pdf']"), Duration.ofSeconds(10));
    }

    @Test
    public void nestedIframe() {
        driver.get("https://demo.automationtesting.in/Frames.html");
        clickElement(By.xpath("//a[@href='#Multiple']"), Duration.ofSeconds(10));
        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
        driver.switchTo().frame(iframe);
        WebElement nestedIframe = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
        driver.switchTo().frame(nestedIframe);
        sendKeysToElement(By.xpath("//input"), "Hello Techstate", Duration.ofSeconds(10));

        driver.switchTo().defaultContent();

        driver.switchTo().frame(iframe);
        System.out.println(
                waitForElement(By.xpath("//h5"), Duration.ofSeconds(10)).getText()
        );
    }
}











