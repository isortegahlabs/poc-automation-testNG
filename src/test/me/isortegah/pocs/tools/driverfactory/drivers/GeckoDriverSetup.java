package me.isortegah.pocs.tools.driverfactory.drivers;

import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

public class GeckoDriverSetup {
    private static final Logger logger = LogManager.getLogger(GeckoDriverSetup.class);
    private static GeckoDriverSetup geckoDriverSetup;

    private GeckoDriverSetup(){}

    public static GeckoDriverSetup getInstance(){
        if( geckoDriverSetup == null ){
            geckoDriverSetup = new GeckoDriverSetup();
        } else {
            logger.error("No se puede crear el objeto GeckoDriverSetup.");
        }

        return geckoDriverSetup;
    }

    public FirefoxDriver localSetup(String fileLocation){
        Map<String, String> params = Config.getInstance().getParamsConfig();
        System.setProperty("webdriver.gecko.driver", fileLocation);
        FirefoxOptions options = new FirefoxOptions();
        if( params.get("headless").equals("true") )
            options.setHeadless(true);
        return new FirefoxDriver(options);
    }
}
