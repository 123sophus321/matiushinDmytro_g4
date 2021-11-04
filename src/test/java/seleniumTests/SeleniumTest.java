package seleniumTests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    WebDriver driver;
    Logger logger;

    @Test
    public void test() {
        logger = Logger.getLogger(getClass());
        File chromeDriver = new File("./drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
        WebElement button = driver.findElement(By.xpath("//a[contains(text(),'Gmail')]"));
        String title = driver.getTitle();
        logger.info(title);
        Assert.assertTrue(button.getText().contains("Gmail"));
        driver.quit();
        logger.info("closed");
        
    }
}
