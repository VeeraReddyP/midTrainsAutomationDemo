package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

public class PaymentPage {

    private static final LoggerFactory logger = new LoggerFactory(PaymentPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;
    TestData testData = new TestData();

    public PaymentPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    By cardNumber = By.name("cardnumber");
    By expiryDate = By.xpath("//input[@placeholder='MM / YY']");
    By cvv = By.xpath("//input[@placeholder='123']");
    By payNowBtn = By.xpath("//a[@class='button-main-content']");
    By payingAmount = By.xpath("//span[@class='text-amount-amount']");
    By otpId = By.id("PaRes");
    By oKbtn = By.xpath("//button[@class='btn btn-sm btn-success']");
    By txnAmount = By.id("txn_amount");
    By thankYouMsg =By.xpath("//span[text()='Thank you for your purchase.']");
    By transactionSuccess = By.xpath("//div[@class='text-success text-bold']");
    By doneBtn = By.xpath("//a[@class='button-main-content']");
    By transactionFailed = By.xpath("//span[text()='Transaction failed']");
    By declinedMsg = By.xpath("//div[@class='text-failed']/span");

    public void enterValidPaymentDetails(){
        elementActions.waitForElemenWithFluent(cardNumber, testData.timeOut, testData.pollTime);
        elementActions.enterData(cardNumber,testData.creditCardNumber);
        elementActions.enterData(expiryDate,testData.creditCardExpiry);
        elementActions.enterData(cvv,testData.creditCardCvv);
    }

    public void enterInValidPaymentDetails(){
        elementActions.waitForElemenWithFluent(cardNumber, testData.timeOut, testData.pollTime);
        elementActions.enterData(cardNumber,testData.creditCardInvalidNumber);
        elementActions.enterData(expiryDate,testData.creditCardExpiry);
        elementActions.enterData(cvv,testData.creditCardCvv);
    }

    public void clickPayNowBtn(){
        elementActions.waitForElemenWithFluent(payNowBtn, testData.timeOut, testData.pollTime);
        elementActions.click(payNowBtn);
    }

    public String getPayingAmount() {
        elementActions.waitForElemenWithFluent(payingAmount, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(payingAmount);
    }

    public void enterOtp(){
        elementActions.waitForElemenToBeClickWithFluent(otpId, testData.timeOut, testData.pollTime);
        elementActions.enterData(otpId,testData.creditCardOtp);
    }

    public void submitPayment() {
        elementActions.waitForElemenToBeClickWithFluent(oKbtn, testData.timeOut, testData.pollTime);
        elementActions.click(oKbtn);
    }

    public String getTransactionAmount() {
        elementActions.switchToFrame(0);
        elementActions.waitForElemenWithFluent(txnAmount, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(txnAmount);
    }

    public String getSuccessMsg() {
        elementActions.switchToDefault();
        elementActions.switchToFrame("snap-midtrans");
        elementActions.waitForElemenWithFluent(transactionSuccess, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(transactionSuccess);
    }

    public void clickDoneBtn() {
        elementActions.waitForElemenWithFluent(doneBtn, testData.timeOut, testData.pollTime);
        elementActions.click(doneBtn);
    }

    public String getThankYouMsg() {
        elementActions.waitForElemenWithFluent(thankYouMsg, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(thankYouMsg);
    }

    public String getFailedMsg() {
        elementActions.switchToDefault();
        elementActions.switchToFrame("snap-midtrans");
        elementActions.waitForElemenWithFluent(transactionFailed, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(transactionFailed);
    }

    public String getDeclinedMsg() {
        elementActions.waitForElemenWithFluent(declinedMsg, testData.timeOut, testData.pollTime);
        return elementActions.getElementText(declinedMsg);
    }
}
