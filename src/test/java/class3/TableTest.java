package class3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static utilities.WebUtils.clickElement;
import static utilities.WebUtils.driver;

public class TableTest {
    @Test
    public void tableTest() {
        driver.navigate().to("https://seleniumpractise.blogspot.com/2021/08/webtable-in-html.html");
        driver.manage().window().maximize();

        clickElement(By.xpath("//table[@id='customers']//tr[2]/td[1]/input"), Duration.ofSeconds(10));
    }
}
