package me.isortegah.pocs.tools.driverfactory.drivers;

import me.isortegah.pocs.constants.WebdriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverSetup {
    private static final Logger logger = LogManager.getLogger(ChromeDriverSetup.class);
    private static ChromeDriverSetup chromeDriverSetup;

    private ChromeDriverSetup(){}

    public static ChromeDriverSetup getInstance(){
        if( chromeDriverSetup == null){
            chromeDriverSetup = new ChromeDriverSetup();
        } else {
            logger.error("No se puede crear el objeto.");
        }

        return chromeDriverSetup;
    }

    public ChromeDriver localSetup(String fileLocation){
        System.setProperty(WebdriverType.CHROME, fileLocation);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disabled-web-security");
        options.addArguments("--no-proxy-server");

        Map<String,Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service",false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications",2);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }
}