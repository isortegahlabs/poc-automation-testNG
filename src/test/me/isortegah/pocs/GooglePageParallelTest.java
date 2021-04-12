package me.isortegah.pocs;

import me.isortegah.pocs.tools.pom.pages.googleproject.GooglePageRefactor;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class GooglePageParallelTest {

    private static final Logger logger = LogManager.getLogger(GooglePageParallelTest.class);
    public DesiredCapabilities capabilities = new DesiredCapabilities();
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

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

    @Test(priority = 1)
    public void stepOne() throws InterruptedException {
        GooglePageRefactor googlePageRefactor = new GooglePageRefactor();
        googlePageRefactor.setDriver(getDriver())
                .goTo()
                .search("amazon méxico");
        googlePageRefactor.setDriver(getDriver())
                .selectTopic("//div[@id='res']//a[@href='https://www.amazon.com.mx/']", "xpath");
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

    public RemoteWebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }
}
