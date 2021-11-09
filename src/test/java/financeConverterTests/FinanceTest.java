package financeConverterTests;

import financeIUA.ConverterPage;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;


public class FinanceTest  {
    private static Logger logger;
    private static WebDriver driver;

    @BeforeAll
    public static void setupDriver(){
        File chromeDriver = new File("./drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @BeforeEach
    public void goToExchangePage(){
        new ConverterPage(driver);
        ConverterPage.go();
    }

    @Test
    public void checkUAHBuy(){
        double amount= 100;
        String currency = "UAH";
        ConverterPage.choseCurrency(currency);
        ConverterPage.fillCurrencyAmount(amount);
        final Map<String, Double> resultsForEachCurrency = ConverterPage.getResultsForEachCurrency(currency);
        final Map<String, Double> exchangeRateForEachCurrency = ConverterPage.getExchangeRateForEachCurrency(currency);
        for (var result : resultsForEachCurrency.entrySet()) {
            DecimalFormat df = new DecimalFormat("####0.00");
            Assertions.assertEquals(result.getValue().toString(), df.format(amount / exchangeRateForEachCurrency.get(result.getKey())));
        }
    }

    @Test
    public void checkUAHSell(){
        double amount= 99;
        String currency = "UAH";
        ConverterPage.choseCurrency(currency);
        ConverterPage.sell();
        ConverterPage.fillCurrencyAmount(amount);
        final Map<String, Double> resultsForEachCurrency = ConverterPage.getResultsForEachCurrency(currency);
        final Map<String, Double> exchangeRateForEachCurrency = ConverterPage.getExchangeRateForEachCurrency(currency);
        for (var result : resultsForEachCurrency.entrySet()) {
            DecimalFormat df = new DecimalFormat("####0.00");
            Assertions.assertEquals(result.getValue().toString(), df.format(amount / exchangeRateForEachCurrency.get(result.getKey())));
        }
    }

    @Test
    public void stupidTest(){
        Assertions.assertEquals("Конвертер валют онлайн. Конвертер валют Украины онлайн по курсу НБУ",driver.getTitle() );
    }

    @AfterAll
    public static void quit(){driver.quit();}


}
