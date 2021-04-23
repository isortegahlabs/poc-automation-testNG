package me.isortegah.pocs;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.pom.pages.googleproject.GooglePage;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class GooglePageTest {

    private static final Logger logger = LogManager.getLogger(GooglePageTest.class);

    @BeforeClass
    @Parameters({"browser","platform"})
    public void setUpTest(String browser, String platform) {
        Config.getInstance().load();
        DriverFactory.getInstance().setDriver( browser , platform );
        logger.info("setupTest with platform " + platform + " and " + browser + " browser.");
    }

    @Test(priority = 1)
    public void stepOne() throws InterruptedException {
        GooglePage.setInstance();

        //GooglePage googlePage = new GooglePage();
        GooglePage.getInstance().goTo()
                .search("amazon méxico")
                .selectTopic("//div[@id='res']//a[@href='https://www.amazon.com.mx/']", "xpath");

        /*logger.trace("Hello there trace!");
        logger.debug("Hello there debug!");
        logger.info("Hello there info!");
        logger.warn("Hello there warn!");
        logger.error("Hello there error!");
        logger.fatal("Hello there fatal!");*/
    }

    @AfterClass
    public void testDown(){
        DriverFactory.getInstance().removeDriver();
    }
}
