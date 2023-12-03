package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PromotionPage extends BaseTest {

    public PromotionPage fillModelCode(String text) {
        WebElement txtModelCode = driver.findElement(By.cssSelector("#product-hunt > div > div.g-row.g-row-next > div > div.ratecard-filter-wrapper > div.filter-col > div:nth-child(1) > div:nth-child(3) > div > div > div > input[type=text]"));
        txtModelCode.sendKeys(text + Keys.ENTER);
        return this;
    }

    public String getProductName() {
        WebElement txtProductName = driver.findElement(By.cssSelector("[class^='content-info-name g-text']"));
        return txtProductName.getText();
    }

    public String getTabDate() {
        WebElement tabDate = driver.findElement(By.cssSelector("[class='tab active 0'] span"));
        return tabDate.getText();
    }

    public String getBrandName() {
        WebElement txtBrandName = driver.findElement(By.cssSelector("[class^='content-info-brand']"));
        return txtBrandName.getText();
    }

    public String getStock() {
        WebElement txtStock = driver.findElement(By.cssSelector("[class='content g-d-flex g-d-align-center']"));
        return txtStock.getText();
    }

    public String firstAreaMaxPrice() {
        WebElement element = driver.findElement(By.cssSelector(".content.g-d-flex.g-d-align-center.g-d-justify-center.g-d-direction-column"));
        WebElement innerDiv = element.findElement(By.cssSelector("div"));
        return innerDiv.getText();
    }

    public String firstAreaCommission() {
        WebElement element = driver.findElement(By.cssSelector(".content.g-d-flex.g-d-align-center.g-d-justify-center.g-d-direction-column"));
        List<WebElement> items = element.findElements(By.cssSelector("[class*='body g-text']"));
        return items.get(0).getText();
    }

    public String secondAreaCommission() {
        WebElement element = driver.findElement(By.cssSelector(".content.g-d-flex.g-d-align-center.g-d-justify-center.g-d-direction-column"));
        List<WebElement> items = element.findElements(By.cssSelector("[class*='body g-text']"));
        return items.get(1).getText();
    }

    public String thirdAreaCommission() {
        WebElement element = driver.findElement(By.cssSelector(".content.g-d-flex.g-d-align-center.g-d-justify-center.g-d-direction-column"));
        List<WebElement> items = element.findElements(By.cssSelector("[class*='body g-text']"));
        return items.get(2).getText();
    }

    public String fourthAreaCommission() {
        WebElement element = driver.findElement(By.cssSelector(".content.g-d-flex.g-d-align-center.g-d-justify-center.g-d-direction-column"));
        List<WebElement> items = element.findElements(By.cssSelector("[class*='body g-text']"));
        return items.get(3).getText();
    }

    public String[] commissionAreas() {
        List<WebElement> items = driver.findElements(By.cssSelector(".content.g-d-flex.g-d-align-center.g-d-justify-center.g-d-direction-column [class*='body g-text']"));
        String[] list = new String[4];
        list[0] = items.get(0).getText();
        list[1] = items.get(1).getText();
        list[2] = items.get(2).getText();
        list[3] = items.get(3).getText();
        return list;
    }

    public String[] priceAreas() {
        List<WebElement> items = driver.findElements(By.cssSelector(".content.g-d-flex.g-d-align-center.g-d-justify-center.g-d-direction-column div"));
        String[] list = new String[6];
        list[0] = items.get(0).getText();
        list[1] = items.get(5).getText();
        list[2] = items.get(4).getText();
        list[3] = items.get(9).getText();
        list[4] = items.get(8).getText();
        list[5] = items.get(12).getText();
        return list;
    }
}
