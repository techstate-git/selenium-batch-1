package class4;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static utilities.WebUtils.clickElement;
import static utilities.WebUtils.driver;

public class AlertModals {

    @Test
    public void alert() throws InterruptedException {
        driver.get("https://demo.automationtesting.in/Alerts.html");
        clickElement(By.xpath("//div[@id='OKTab']/button"), Duration.ofSeconds(10));

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        Thread.sleep(2000);
        alert.accept();

        clickElement(By.xpath("//a[@href='#Textbox']"), Duration.ofSeconds(10));
        clickElement(By.xpath("//div[@id='Textbox']/button"), Duration.ofSeconds(10));
        System.out.println(alert.getText());
        alert.sendKeys("Beksultan");
        Thread.sleep(2000);
        alert.accept();
    }

    @Test
    public void modal() throws InterruptedException {
        driver.get("https://demo.automationtesting.in/Modals.html");
        WebElement element = driver.findElement(By.xpath("//a[@data-toggle='modal']"));
        element.click();

        String element2 = driver.findElement(By.xpath("//div[@id='myModal']//div[@class='modal-body']/p[1]")).getText();
        Thread.sleep(3000);
        System.out.println("Text" + element2);

        WebElement element1 = driver.findElement(By.xpath("//button[@type='button']"));
        Thread.sleep(3000);
        element1.click();
    }

}
