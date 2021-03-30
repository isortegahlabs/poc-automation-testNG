package me.isortegah.pocs;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.pom.pages.googleproject.GooglePage;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class GooglePageTest {

    private static final Logger logger = LogManager.getLogger(GooglePageTest.class);
    private static GooglePage googleP;

    @BeforeClass
    @Parameters("browser")
    public void setUpTest(String browser) {
        Config.getInstance().load();
        DriverFactory.getInstance().setDriver(browser);
        logger.info("setup test with " + browser + " browser.");
    }

    @Test(priority = 0)
    public void stepOne() throws InterruptedException {
        GooglePage.getInstance().goTo()
                .search("amazon méxico")
                .selectTopic("//a[@href='https://www.amazon.com.mx/']", "xpath");
        Thread.sleep(5000);
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
