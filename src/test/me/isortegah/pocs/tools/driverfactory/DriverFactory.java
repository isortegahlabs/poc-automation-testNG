package me.isortegah.pocs.tools.driverfactory;

import me.isortegah.pocs.constants.BrowserType;
import me.isortegah.pocs.constants.PlatformType;
import me.isortegah.pocs.tools.driverfactory.drivers.ChromeDriverSetup;
import me.isortegah.pocs.tools.driverfactory.drivers.GeckoDriverSetup;
import me.isortegah.pocs.tools.driverfactory.drivers.RemoteWebDriverSetup;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private DriverFactory(){}
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static DriverFactory instance = new DriverFactory();
    private Map<String, String> config;
    private PlatformType platform;

    public static DriverFactory getInstance(){
        return instance;
    }

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>(){

      @Override
      protected RemoteWebDriver initialValue() {
          return null;
      }

    };

    public RemoteWebDriver getDriver(){
        return driver.get();
    }

    public void setDriver(String browser){
        setDriver(BrowserType.valueOf(browser), PlatformType.LINUX);
    }

    public void setDriver(String browser , String platform){
        setDriver(BrowserType.valueOf(browser), PlatformType.valueOf(platform));
    }

    public void setDriver(BrowserType browser , PlatformType platformType ){
        platform = platformType;
        String driverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/";

        config = Config.getInstance().getParamsConfig();

        switch (browser.toString()) {
            case "CHROME":
                setDriverChrome( driverPath );
                break;
            case "FIREFOX":
                setDriverFirefox( driverPath );
                break;
            case "SAFARI":
                setDriverSafari( driverPath );
                break;
            case "IE":
                setDriverIE( driverPath );
                break;
            case "EDGE":
                setDriverEdge( driverPath );
                break;
            case "HTMLUNIT":
                setDriverHtmlUnit( driverPath );
                break;
            default:
                
                break;
        }

        int i = 10;

        for (int j = 1; j<= i; i++){
            try {
                driver.get().manage().window().maximize();
                break;
            }catch (WebDriverException we){
                driver.set(new ChromeDriver());
                driver.get().manage().window().maximize();
            }
            if (i == j){
                Assert.fail("Failed to maximize window " + j + " times");
            }
        }
        driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    private void setDriverHtmlUnit(String driverPath) {
    }

    private void setDriverEdge(String driverPath) {
    }

    private void setDriverIE(String driverPath) {
    }

    private void setDriverSafari(String driverPath) {
        if ( Boolean.parseBoolean(config.get("remoteWebDriver")) )
            driver.set(
                    RemoteWebDriverSetup.getInstance()
                            .remoteSetup(BrowserType.SAFARI, platform) );
        //else
          //  driver.set(ChromeDriverSetup.getInstance().localSetup( fileLocation ));
    }

    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }

    private void setDriverChrome(String driverPath){
        String fileLocation = driverPath + "chromedriver";
        fileLocation += (!isOsBaseUnix())?".exe":"";
        if ( Boolean.parseBoolean(config.get("remoteWebDriver")) ){
            RemoteWebDriver rwd = RemoteWebDriverSetup.getInstance()
                    .remoteSetup(BrowserType.CHROME, platform);
            logger.info("SessionID chrome " + rwd.getSessionId());
            driver.set(rwd);
        } else
            driver.set(ChromeDriverSetup.getInstance().localSetup( fileLocation ));
    }

    private void setDriverFirefox(String driverPath) {
        String fileLocation = driverPath + "geckodriver";
        fileLocation += (!isOsBaseUnix())?".exe":"";
        if ( Boolean.parseBoolean(config.get("remoteWebDriver")) ){
            RemoteWebDriver rwd = RemoteWebDriverSetup.getInstance()
                    .remoteSetup(BrowserType.FIREFOX, platform);
            logger.info("SessionID firefox " + rwd.getSessionId());
            driver.set( rwd );
        } else
            driver.set(GeckoDriverSetup.getInstance().localSetup( fileLocation ));
    }

    private Boolean isOsBaseUnix(){
        return System.getProperty("os.name").toLowerCase().contains("windows")?false:true;
    }

}
