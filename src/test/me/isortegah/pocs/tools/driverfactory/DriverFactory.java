package me.isortegah.pocs.tools.driverfactory;

import me.isortegah.pocs.constants.BrowserType;
import me.isortegah.pocs.tools.driverfactory.drivers.ChromeDriverSetup;
import me.isortegah.pocs.tools.driverfactory.drivers.GeckoDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private DriverFactory(){}

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(){

      @Override
      protected WebDriver initialValue() {
          return null;
      }

    };

    public WebDriver getDriver(){
        return driver.get();
    }

    public void setDriver(BrowserType browser){
        String driverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/";

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
    }

    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }

    private void setDriverChrome(String driverPath){
        String fileLocation = driverPath + "chromedriver";
        fileLocation += (!isOsBaseUnix())?".exe":"";
        driver.set(ChromeDriverSetup.getInstance().localSetup( fileLocation ));
    }

    private void setDriverFirefox(String driverPath) {
        String fileLocation = driverPath + "geckodriver";
        fileLocation += (!isOsBaseUnix())?".exe":"";
        driver.set(GeckoDriverSetup.getInstance().localSetup( fileLocation ));
    }

    private Boolean isOsBaseUnix(){
        return System.getProperty("os.name").toLowerCase().contains("windows")?false:true;
    }

}
