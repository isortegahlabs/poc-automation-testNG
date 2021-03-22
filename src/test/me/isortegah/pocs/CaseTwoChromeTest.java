package me.isortegah.pocs;

import me.isortegah.pocs.constants.BrowserType;
import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class CaseTwoChromeTest {
    WebDriver driver;
    WebDriverWait waitElement;
    private static final Logger logger = LogManager.getLogger(CaseTwoChromeTest.class);

    @BeforeClass
    public void setUpTest() {
        Config.getInstance().load();
        DriverFactory.getInstance().setDriver(BrowserType.CHROME);
        driver = DriverFactory.getInstance().getDriver();
        driver.get("https://google.com");
        waitElement = new WebDriverWait(driver, 15);
    }

    @Test(priority = 0)
    public void stepOne(){
        logger.trace("Hello there trace!");
        logger.debug("Hello there debug!");
        logger.info("Hello there info!");
        logger.warn("Hello there warn!");
        logger.error("Hello there error!");
        logger.fatal("Hello there fatal!");
    }

    @AfterClass
    public void testDown(){
        DriverFactory.getInstance().removeDriver();
    }
}
