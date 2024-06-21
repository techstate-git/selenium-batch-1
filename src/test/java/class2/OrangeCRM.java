package class2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.WebUtils.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class OrangeCRM {

    @BeforeAll // BeforeAll hook runs once before all test cases
    public static void LoginTest() throws InterruptedException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(3000);
        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("admin123");

        WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
        login.click();
    }

    @Test
    @Order(1)
    public void systemUsersSearchFunctionality() throws InterruptedException {
        String usernameForSearch = "Admin";
        Thread.sleep(3000);
        WebElement adminButton = driver.findElement(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']"));
        adminButton.click();

        Thread.sleep(2000);
        WebElement username = driver.findElement(By.xpath("//label[.='Username']/parent::div/following-sibling::div/input"));
        username.sendKeys(usernameForSearch);

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();


    }

    @Test
    @Order(2)
    public void addNewUserFunctionalityTesting() throws InterruptedException {

        // New user information
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("employeeName", "John");
        userInfo.put("userName", "joker12");
        userInfo.put("password", "Joker@123");

        // click Admin tab
        Thread.sleep(4000);
        WebElement adminButton = driver.findElement(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']"));
        adminButton.click();

        // click Add new user button
        Thread.sleep(2000);
        WebElement addNewUserBtn = driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/button"));
        addNewUserBtn.click();

        // click User Role dropdown
        Thread.sleep(2000);
        WebElement userRoleDropDown = driver
                .findElement(By.xpath("//label[contains(text(), 'User Role')]/parent::node()/following-sibling::div/div/div/div"));
        userRoleDropDown.click();

        // select Admin for User Role dropdown
        WebElement adminOptionUserRole = driver.findElement(By.xpath("//div[@role='listbox']/div[2]"));
        adminOptionUserRole.click();

        // click Status dropdown
        WebElement statusDropDown = driver
                .findElement(By.xpath("//label[contains(text(), 'Status')]/parent::node()/following-sibling::div"));
        statusDropDown.click();

        // store the dropdown options for Status selection
//        Thread.sleep(500);
//        WebElement statusDropDownOptionsParent = driver
//                .findElement(By.xpath("//label[contains(text(), 'Status')]/parent::node()/following-sibling::div/div/div[2]"));

        try {
            // get list of child elements ['Select', 'Enabled', 'Disabled']
            Thread.sleep(2000);
            List<WebElement> statusDropDownOptionsList = driver
                    .findElements(By.xpath("//label[contains(text(), 'Status')]/parent::node()/following-sibling::div/div/div[2]/div"));

            for (WebElement option : statusDropDownOptionsList) {
                Thread.sleep(500);
                String optionText = option.getText();

                if (optionText.equalsIgnoreCase("Enabled")) {
                    option.click();
                }
            }
        } catch (StaleElementReferenceException staleElementReferenceException) {
            staleElementReferenceException.getMessage();
        }

        // get the locator for Employee Name input field
        WebElement employeeNameInputField = driver.findElement(By.xpath("//input[@placeholder=\"Type for hints...\"]"));
        employeeNameInputField.sendKeys(userInfo.get("employeeName"));
        Thread.sleep(2000);

        WebElement listedEmployeeName = employeeNameInputField.findElement(By.xpath("parent::node()/following-sibling::div"));
        System.out.println("Found employee name is: " + listedEmployeeName.getText().toUpperCase());
        listedEmployeeName.click();

        // Information to put into Username for a new user
        By userNameInputField = By.xpath("//label[contains(text(), 'Username')]/parent::node()/following-sibling::div/input");
        Duration timeDuration = Duration.ofSeconds(10);
        sendKeysToElement(userNameInputField, userInfo.get("userName"), timeDuration);// Information to put into Username for a new user

        // Enter password for the new user using list of webelements
        ArrayList<WebElement> passwordList = (ArrayList<WebElement>) driver.findElements(By.xpath("//input[@type='password']"));
        passwordList.get(0).sendKeys(userInfo.get("password"));
        passwordList.get(1).sendKeys(userInfo.get("password"));

        // click Save to create the new user
        By saveBtn = By.xpath("//button[@type='submit']");
        clickElement(saveBtn, Duration.ofSeconds(1));

        // Assert that user was successfully added/created
        String expectedMsg = "Success";
        WebElement toastSuccess = waitForElement(By.xpath("//p[contains(@class, 'oxd-text--toast-title')]"), Duration.ofSeconds(10));
        String actualMsg = toastSuccess.getText();
        assertEquals(expectedMsg, actualMsg, "Expected and Actual toast message did not match.");
    }

    @AfterAll
    static void closeAndQuitTheBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }
}
