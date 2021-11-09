package financeIUA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class ConverterPage {

    private static final By buyButton = By.xpath("//li[@id='buy']");
    private static final By sellButton = By.xpath("//li[@id='sell']");
    private static final By fckngAD = By.xpath("//div[@class='achernar__closeControl']");
    private static final By currencyAmount = By.xpath("//input[@id='currency_amount']");
    private static WebDriver driver;

    private static ArrayList<String> currencies = new ArrayList(Arrays.asList("USD", "UAH", "EUR", "PLN", "RUB"));
    private static final String currencyRate = "//p[@id='xxx']//input[@id='currency_rate']";
    //do you know the better way to use some dynamic xpath if all of them are almost the same (just different currency id)?
    private static final String result = "//p[@id='xxx']//input[@id='currency_exchange']";
    private static final By UAHResult = By.xpath("//p[@id='UAH']//input[@id='currency_exchange']");
    private static final By EURResult = By.xpath("//p[@id='EUR']//input[@id='currency_exchange']");
    private static final By RUBResult = By.xpath("//p[@id='RUB']//input[@id='currency_exchange']");
    private static final By PLNResult = By.xpath("//p[@id='PLN']//input[@id='currency_exchange']");
    private static final By currencyDropdown = By.xpath("//select[@id='converter_currency']");


    private static final String url = "https://finance.i.ua/converter/";


    public ConverterPage(WebDriver driver) {
        ConverterPage.driver = driver;
    }

    public static void go() {
        driver.get(url);
    }

    public static void closeAD() {
        driver.findElement(fckngAD).click();
    }

    public static void sell() {
        driver.findElement(sellButton).click();
    }


    public static void choseCurrency(String currencyName) {
        new Select(driver.findElement(currencyDropdown)).selectByVisibleText(currencyName);
    }

    public static void fillCurrencyAmount(double amount) {
        WebElement amountField = driver.findElement(currencyAmount);
        amountField.clear();
        amountField.sendKeys(Double.toString(amount));
    }

    public static double getExchangeRate(String currencyName) {
        String rateForCurreny  = currencyRate.replace("xxx", currencyName);
        return Double.parseDouble(driver.findElement(By.xpath(rateForCurreny)).getAttribute("value").replace(" ", ""));
    }

    public static double getResult(String currencyName) {
        String resultForCurreny = result.replace("xxx", currencyName);
        return Double.parseDouble(driver.findElement(By.xpath(resultForCurreny)).getAttribute("value").replace(" ", ""));

    }

    public static Map<String, Double> getResultsForEachCurrency(String currencyName) {
        if (currencies.contains(currencyName) ){currencies.remove(currencies.indexOf(currencyName));}
        Map <String, Double> resultsForCurrency= new HashMap<>();
        for (String currency : currencies) {
            resultsForCurrency.put(currency, getResult(currency));
        }
        return resultsForCurrency;
    }

    public static Map<String, Double> getExchangeRateForEachCurrency(String currencyName) {
        if (currencies.contains(currencyName)){currencies.remove(currencies.indexOf(currencyName));}
        Map <String, Double> rateForCurrencies= new HashMap<>();
        for (String currency : currencies) {
            rateForCurrencies.put(currency, getExchangeRate(currency));
        }
        return rateForCurrencies;
    }
}
