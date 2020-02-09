package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

import java.util.HashMap;

public class SummaryPage {

    private static final LoggerFactory logger = new LoggerFactory(SummaryPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;
    TestData testData = new TestData();

    public SummaryPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    By itemName = By.xpath("//span[@class='item-name']");
    By itemCost = By.xpath("//td[@class='table-amount text-body']");
    By shippingDetailsTab = By.xpath("//span[text()='shipping details']");
    By custName = By.xpath("//div[text()='Name']/following-sibling::div");
    By custNumber = By.xpath("//div[text()='Phone number']/following-sibling::div");
    By custEmail = By.xpath("//div[text()='Email']/following-sibling::div");
    By custAddress = By.xpath("//div[text()='Address']/following-sibling::div");
    By continueBtn = By.xpath("//a[@class='button-main-content']");
    By creditCardOption = By.xpath("//div[@id='payment-list']//div[text()='Credit Card']");
    String frameId ="snap-midtrans";

    public void clickContinueBtn(){
        elementActions.waitForElemenToBeClickWithFluent(continueBtn, testData.timeOut, testData.pollTime);
        elementActions.click(continueBtn);
    }

    public void clickShippingDetailsTab(){
        elementActions.waitForElemenToBeClickWithFluent(shippingDetailsTab, testData.timeOut, testData.pollTime);
        elementActions.click(shippingDetailsTab);
    }

    public String getItemName() {
        elementActions.waitForElemenWithFluent(itemName, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(itemName);
    }

    public String getCustName() {
        elementActions.waitForElemenWithFluent(custName, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(custName);
    }

    public String getCustNumber() {
        elementActions.waitForElemenWithFluent(custNumber, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(custNumber);
    }

    public String getCustEmail() {
        elementActions.waitForElemenWithFluent(custEmail, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(custEmail);
    }

    public String getCustAddress() {
        elementActions.waitForElemenWithFluent(custAddress, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(custAddress);
    }


    public String getItemCost() {
        elementActions.switchToFrame(frameId);
        elementActions.waitForElemenWithFluent(itemCost, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(itemCost);
    }

    public void selectCreditCardPayment(){
        elementActions.waitForElemenWithFluent(creditCardOption, testData.timeOut, testData.pollTime);
        elementActions.click(creditCardOption);
    }

    public HashMap<String,String> expectedCustomerData(){
        HashMap<String,String> customerData = new HashMap<>();
        customerData.put("name",getCustName());
        customerData.put("email",getCustEmail());
        customerData.put("phoneNum",getCustNumber());
        customerData.put("Address",getCustAddress());
        return  customerData;
    }
}
