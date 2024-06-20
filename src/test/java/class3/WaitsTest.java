package class3;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static utilities.WebUtils.*;

public class WaitsTest {
    @Test
    public void userNavigation() {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        sendKeysToElement(By.xpath("//input[@name='username']"), "Admin", Duration.ofSeconds(10));
        sendKeysToElement(By.xpath("//input[@name='password']"), "admin123", Duration.ofSeconds(10));

        clickElement(By.xpath("//button[@type='submit']"), Duration.ofSeconds(10));

        clickElement(By.xpath("//a[@href='/web/index.php/pim/viewMyDetails']"), Duration.ofSeconds(10));
        List<WebElement> userNavigation = waitForListElements(By.xpath("//div[@role='tablist']//a"), Duration.ofSeconds(10));

        List<String> expectedTexts = Arrays.asList(
                "Personal Details",
                "Contact Details",
                "Emergency Contacts",
                "Dependents",
                "Immigration",
                "Job",
                "Salary",
                "Report-to",
                "Qualifications",
                "Memberships"
        );

        int index = 0;
        for (WebElement element : userNavigation) {
            String actualText = element.getText(); //Personal Details
            String expectedText = expectedTexts.get(index); //Personal Details

            System.out.println("Actual: " + actualText + " | Expected: " + expectedText);
            Assert.assertEquals(expectedText, actualText); // Personal Details = Personal Details

            index++; //1
        }
    }
}













