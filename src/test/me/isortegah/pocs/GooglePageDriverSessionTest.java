 package me.isortegah.pocs;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.driverfactory.DriverSessionManager;
import me.isortegah.pocs.tools.pom.pages.googleproject.GooglePage;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

 public class GooglePageDriverSessionTest {

    private static final Logger logger = LogManager.getLogger(GooglePageDriverSessionTest.class);
    final ThreadLocal<RemoteWebDriver> wd = new ThreadLocal<>();
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeClass
    @Parameters({"browser","platform"})
    public void setUpTest(String browser, String platform) throws InterruptedException, MalformedURLException {
        Config.getInstance().load();
        if(browser.equals("FIREFOX"))
            Thread.sleep(8000);
        //DriverFactory.getInstance().setDriver( browser , platform );
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.toLowerCase());
        capabilities.setPlatform(Platform.LINUX);
        wd.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities));
        //wd.set(DriverFactory.getInstance().getDriver());
        //logger.info("** ORales--> " + DriverSessionManager.getInstance().getDriverSession(wd.get().getSessionId().toString()).getSessionId());
        logger.info("setUpTest sessionID: " + wd.get().getSessionId());
        logger.info("setupTest with platform " + platform + " and " + browser + " browser.");

    }

    @Test(priority = 1)
    public void stepOne() throws InterruptedException {
        logger.info("stepOne: " + wd.get().getSessionId());
        GooglePage googlePage = new GooglePage();
        googlePage.setDriver(wd.get()).goTo()
                .search("amazon méxico")
                .selectTopic("//div[@id='res']//a[@href='https://www.amazon.com.mx/']", "xpath");
    }

    @AfterClass
    public void testDown(){
        //DriverFactory.getInstance().removeDriver();
        wd.get().quit();
        wd.remove();
    }
}
