package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ProductsPage extends BaseTest {

    public ProductsPage fillModelCode(String text) {
        WebElement txtModelCode = driver.findElement(By.cssSelector("[placeholder='Barkod']"));
        txtModelCode.sendKeys(text + Keys.ENTER);
        return this;
    }

    public String getProductName() {
        WebElement txtProductName = driver.findElement(By.cssSelector("[class^='content-info-name g-text']"));
        return txtProductName.getText();
    }

    public String getTabDate() {
        String date = null;
        boolean status = driver.findElements(By.cssSelector("div[style*='visible'][class='tooltip-price-info'] [class='header-container'] p")).size()>0;
        if (status){
            WebElement elementToHover = driver.findElement(By.cssSelector("[class=\"quantity-tooltip-trigger\"]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(elementToHover).perform();
            WebElement tabDate = driver.findElement(By.cssSelector("div[style*='visible'][class='tooltip-price-info'] [class='header-container'] p"));
            date = tabDate.getText();
        }else {
            date = "-";
        }
        return date;
    }

    public String getBrandName() {
        WebElement txtBrandName = driver.findElement(By.cssSelector("[class^='content-info-brand']"));
        return txtBrandName.getText();
    }

    public String getStock() {
        WebElement txtStock = driver.findElement(By.cssSelector("[class='content g-d-flex g-d-align-center']"));
        return txtStock.getText();
    }

    public String[] getCommissionInfo() {
        String[] list = new String[4];
        boolean status = driver.findElements(By.cssSelector("[class=\"quantity-tooltip-trigger\"]")).size()>0;

        if (status){
            WebElement elementToHover = driver.findElement(By.cssSelector("[class=\"quantity-tooltip-trigger\"]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(elementToHover).perform();
            List<WebElement> items = driver.findElements(By.cssSelector("div[style*='visible'][class='tooltip-price-info'] [class='tooltip-container'] [class*='tooltip-value']"));
            list[0] = items.get(0).getText();
            list[1] = items.get(1).getText();
            list[2] = items.get(2).getText();
            list[3] = items.get(3).getText();
        }
        else {
            list[0] = driver.findElement(By.cssSelector("[class=\"commission\"]")).getText();
        }
        return list;
    }

    public String[] getPriceInfo() {
        String[] list = new String[6];
        boolean status = driver.findElements(By.cssSelector("[class=\"quantity-tooltip-trigger\"]")).size()>0;
        if (status){
            WebElement elementToHover = driver.findElement(By.cssSelector("[class=\"quantity-tooltip-trigger\"]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(elementToHover).perform();
            List<WebElement> items = driver.findElements(By.cssSelector("div[style*='visible'][class='tooltip-price-info'] [class='prices'] > span"));

            list[0] = splitNumeric(items.get(0).getText());
            list[1] = splitMultiNumeric(items.get(1).getText())[1];
            list[2] = splitMultiNumeric(items.get(1).getText())[0];
            list[3] = splitMultiNumeric(items.get(2).getText())[1];
            list[4] = splitMultiNumeric(items.get(2).getText())[0];
            list[5] = splitNumeric(items.get(3).getText());
        }
        return list;
    }
}
