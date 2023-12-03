package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {

    public LoginPage userName(String userName) {
        WebElement userNameField = driver.findElement(By.cssSelector("input[type='text']"));
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage password(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage login() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
        return this;
    }
}
