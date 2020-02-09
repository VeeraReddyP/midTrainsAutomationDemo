package e2eTests;

import init.DriverUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.LoggerFactory;

public class BaseRun {

    private static final LoggerFactory logger = new LoggerFactory(BaseRun.class);
    DriverUtils driverUtils = new DriverUtils();
    @Parameters("browser")
    @BeforeClass
    public void launchBrowser(String browser) {
        driverUtils.initiateDriver(browser);
    }
   @AfterClass
   public void tearDown() {
      driverUtils.killDriver();
   }
}
