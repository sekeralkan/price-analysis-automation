package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BaseTest {

    public MainPage menuSelect(String menu) {
        WebElement element = driver.findElement(By.xpath("//*[contains(@class,'menu-item')]/div/div[@class='item-text' and contains(text(),'"+menu+"')]"));
        jsClick(element);
        return this;
    }

    public MainPage downMenuSelect(String menu) {
        WebElement element = driver.findElement(By.xpath("//div[@class='item-text' and contains(text(), '"+menu+"')]"));
        jsClick(element);
        return this;
    }
}
