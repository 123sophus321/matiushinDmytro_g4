package financeIUA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private static By converterButton= By.xpath("//li[contains(@class,'')]//a[contains(text(),'Конвертер валют')]");
    private static By fckngAD= By.xpath("//div[@class='achernar__closeControl']");
    private static String url = "https://finance.i.ua/";
    private static WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void go(){
        driver.get(url);
    }

    public static void closeAD(){
        driver.findElement(fckngAD).click();
    }

    public static ConverterPage goToConverterPage(){
        driver.findElement(converterButton).click();
        return new ConverterPage(driver);
    }
}