package homework;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static utilities.WebUtils.*;

public class HomeWork1 {
    @Test
    public void test1() {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        sendKeysToElement(By.xpath("//input[@name='username']"), "Admin", Duration.ofSeconds(10));
        sendKeysToElement(By.xpath("//input[@name='password']"), "admin123", Duration.ofSeconds(10));

        clickElement(By.xpath("//button[@type='submit']"), Duration.ofSeconds(10));

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory");

        clickElement(By.xpath("//label[.='Location']/parent::div/following-sibling::div/div/div[2]/div[5]"), Duration.ofSeconds(10));
    }
}
