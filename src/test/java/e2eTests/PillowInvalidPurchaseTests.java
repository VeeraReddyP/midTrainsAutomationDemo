package e2eTests;

import data.TestData;
import init.DriverUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PaymentPage;
import pages.ShoppingCartPage;
import pages.SummaryPage;
import utils.LoggerFactory;

public class PillowInvalidPurchaseTests extends BaseRun {
    private static final LoggerFactory logger = new LoggerFactory(PillowInvalidPurchaseTests.class);
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;
    SummaryPage summaryPage;
    PaymentPage paymentPage;
    TestData testData = new TestData();
    String actualProductCost;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(DriverUtils.getDriver());
        shoppingCartPage = new ShoppingCartPage(homePage.driver);
        summaryPage = new SummaryPage(homePage.driver);
        paymentPage = new PaymentPage(homePage.driver);
    }

    @Test()
    public void buyPillow_OnFailurePayment() {
        homePage.clickBuyNow();
        actualProductCost = homePage.getProductCost();
        Assert.assertEquals(homePage.getProductTitle(), testData.productTitle);
        shoppingCartPage.setCustomerData();
        shoppingCartPage.clickCheckOut();
        Assert.assertEquals(actualProductCost, summaryPage.getItemCost());
        Assert.assertEquals(summaryPage.getItemName(), testData.productTitle);
        summaryPage.clickShippingDetailsTab();
        Assert.assertEquals(shoppingCartPage.enteredCustomerData(),summaryPage.expectedCustomerData());
        summaryPage.clickContinueBtn();
        summaryPage.selectCreditCardPayment();
        paymentPage.enterInValidPaymentDetails();
        Assert.assertEquals(actualProductCost, paymentPage.getPayingAmount());
        paymentPage.clickPayNowBtn();
        Assert.assertEquals(testData.finalProductCost, paymentPage.getTransactionAmount());
        paymentPage.enterOtp();
        paymentPage.submitPayment();
        Assert.assertEquals(testData.actualFailureMsg, paymentPage.getFailedMsg());
        Assert.assertEquals(testData.actualDeclinedMsg, paymentPage.getDeclinedMsg());
        logger.info("========Test Done ========");
    }
}
