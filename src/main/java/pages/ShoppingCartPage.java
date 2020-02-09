package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

import java.util.HashMap;

public class ShoppingCartPage {

    private static final LoggerFactory logger = new LoggerFactory(ShoppingCartPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;
    TestData testData = new TestData();

    public ShoppingCartPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    By custName = By.xpath("//td[text()='Name']/following-sibling::td/input");
    By custEmail = By.xpath("//td[text()='Email']/following-sibling::td/input");
    By custPhone = By.xpath("//td[text()='Phone no']/following-sibling::td/input");
    By custCity = By.xpath("//td[text()='City']/following-sibling::td/input");
    By custAddress = By.xpath("//td[text()='Address']/following-sibling::td/textarea");
    By custPostal = By.xpath("//td[text()='Postal Code']/following-sibling::td/input");
    By checkoutBtn = By.xpath("//div[@class='cart-checkout']");


    public void setCustName(){
        elementActions.waitForElemenWithFluent(custName, testData.timeOut, testData.pollTime);
        elementActions.enterData(custName,testData.customerName);
    }

    public void setCustEmail(){
        elementActions.waitForElemenWithFluent(custEmail, testData.timeOut, testData.pollTime);
        elementActions.enterData(custEmail,testData.customerEmail);
    }

    public void setCustPhone(){
        elementActions.waitForElemenWithFluent(custPhone, testData.timeOut, testData.pollTime);
        elementActions.enterData(custPhone,testData.customerPhone);
    }

    public void setCustCity(){
        elementActions.waitForElemenWithFluent(custCity, testData.timeOut, testData.pollTime);
        elementActions.enterData(custCity,testData.customerCity);
    }

    public void setCustAddress(){
        elementActions.waitForElemenWithFluent(custAddress, testData.timeOut, testData.pollTime);
        elementActions.enterData(custAddress,testData.customerAddress);
    }

    public void setCustPostal(){
        elementActions.waitForElemenWithFluent(custPostal, testData.timeOut, testData.pollTime);
        elementActions.enterData(custPostal,testData.customerPortal);
    }

    public void clickCheckOut() {
        elementActions.waitForElemenWithFluent(checkoutBtn, testData.timeOut, testData.pollTime);
        elementActions.click(checkoutBtn);
    }

    public void setCustomerData(){
        setCustName();
        setCustEmail();
        setCustPhone();
        setCustCity();
        setCustAddress();
        setCustPostal();
    }

    public HashMap<String,String> enteredCustomerData(){
        HashMap<String,String> customerData = new HashMap<>();
        customerData.put("name",testData.customerName);
        customerData.put("email",testData.customerEmail);
        customerData.put("phoneNum",testData.customerPhone);
        customerData.put("Address",testData.customerAddress+" "+testData.customerCity+" "+testData.customerPortal);
        return  customerData;
    }

}
