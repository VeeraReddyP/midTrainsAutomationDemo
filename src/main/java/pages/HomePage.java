package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

public class HomePage {

    private static final LoggerFactory logger = new LoggerFactory(HomePage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;
    TestData testData = new TestData();

    By buyNowBtn = By.xpath("//a[@class='btn buy']");
    By title = By.xpath("//div[@class='title']");
    By cost = By.xpath("//div[@class='price']/span[2]");

    public HomePage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public void clickBuyNow() {
        elementActions.waitForElemenToBeClickWithFluent(buyNowBtn, testData.timeOut, testData.pollTime);
        elementActions.click(buyNowBtn);
    }

    public String getProductTitle() {
        elementActions.waitForElemenWithFluent(title, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(title);
    }

    public String getProductCost() {
        elementActions.waitForElemenWithFluent(cost, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(cost);
    }
}
