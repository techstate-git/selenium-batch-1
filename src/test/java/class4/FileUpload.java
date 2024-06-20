package class4;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static utilities.WebUtils.driver;

public class FileUpload {
    @Test
    public void upload() {
        driver.get("https://demo.automationtesting.in/FileUpload.html");
        WebElement element = driver.findElement(By.xpath("//input[@id='input-4']"));

        element.sendKeys("/Users/beksultanismatov/IdeaProjects/Techstate/Batch/src/test/resources/upload-files/Upload.txt");
    }
}
