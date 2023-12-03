package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends BaseLibrary {

    public static String URL = "https://partner.trendyol.com/dashboard";

    @BeforeTest
    public static void openBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null) browser = "Chrome";

        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--remote-allow-origins=*");
            co.addArguments("start-maximized");
            co.addArguments("disable-infobars");
            co.addArguments("--disable-extensions");
            co.addArguments("--incognito");
            //co.addArguments("--headless=new");
            driver = new ChromeDriver(co);
        }
        else if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browser.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if (browser.equals("Safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
