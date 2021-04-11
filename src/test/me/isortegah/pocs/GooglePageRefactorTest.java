package me.isortegah.pocs;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.pom.pages.googleproject.GooglePage;
import me.isortegah.pocs.tools.pom.pages.googleproject.GooglePageRefactor;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class GooglePageRefactorTest {

    private static final Logger logger = LogManager.getLogger(GooglePageRefactorTest.class);
    public DesiredCapabilities capabilities = new DesiredCapabilities();
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

    @BeforeClass
    @Parameters({"browser","platform"})
    public void setUpTest(String browser, String platform) throws MalformedURLException {
        Config.getInstance().load();
        System.out.println(browser + " Browser Initiated");
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.toLowerCase());
        capabilities.setPlatform(Platform.LINUX);
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities));
        logger.info("setupTest with platform " + platform + " and " + browser + " browser. setUpTest sessionID: "+ getDriver().getSessionId());
    }

    public RemoteWebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    @Test(priority = 0)
    public void stepOne() throws InterruptedException {
        GooglePageRefactor googlePageRefactor = new GooglePageRefactor();
        googlePageRefactor.setDriver(getDriver())
                .goTo()
                .search("amazon méxico");
        googlePageRefactor.setDriver(getDriver())
                .selectTopic("//div[@id='res']//a[@href='https://www.amazon.com.mx/']", "xpath");
        /*logger.trace("Hello there trace!");
        logger.debug("Hello there debug!");
        logger.info("Hello there info!");
        logger.warn("Hello there warn!");
        logger.error("Hello there error!");
        logger.fatal("Hello there fatal!");*/
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass
    void terminate () {
        //Remove the ThreadLocalMap element
        logger.info("");
        driver.remove();
    }
}
