package me.isortegah.pocs.tools.driverfactory.drivers;

import me.isortegah.pocs.constants.BrowserType;
import me.isortegah.pocs.constants.PlatformType;
import me.isortegah.pocs.tools.utils.settings.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverSetup {

    private static final Logger logger = LogManager.getLogger(RemoteWebDriverSetup.class);
    private static RemoteWebDriverSetup remoteWebDriverSetup;
    private PlatformType platform;

    private RemoteWebDriverSetup() {}

    public static RemoteWebDriverSetup getInstance() {
        logger.info("getIntance");
        //if ( remoteWebDriverSetup == null ) {
            return new RemoteWebDriverSetup();
        //}

        //return remoteWebDriverSetup;
    }

    public RemoteWebDriver remoteSetup(BrowserType browser , PlatformType platformType){
        logger.info("remoteSetup " + browser + " browser and " + platformType + " platform");
        platform = platformType;
        try {
            DesiredCapabilities caps = null;
            switch (browser.toString()) {
                case "CHROME":
                    caps = getCapsChrome();
                    break;
                case "FIREFOX":
                    caps = getCapsFirefox();
                    break;
                case "SAFARI":
                    caps = getCapsSafari();
                    break;
                case "IE":
                    getCapsIE();
                    break;
                case "EDGE":
                    getCapsEdge();
                    break;
                case "HTMLUNIT":
                    getCapsHtmlUnit();
                    break;
                default:

                    break;
            }
            String urlHub = Config.getInstance().getParamsConfig().get("urlHub");
            return new RemoteWebDriver( new URL(urlHub +"/wd/hub"),caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private DesiredCapabilities getCapsChrome() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName" , "chrome");
        caps.setCapability("platform" , platform);
        return caps;
    }

    private DesiredCapabilities getCapsFirefox() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName" , "firefox");
        caps.setCapability("platform" , platform);
        return caps;
    }

    private DesiredCapabilities getCapsSafari() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName" , "safari");
        caps.setCapability("platform" , platform);
        return caps;
    }

    private void getCapsIE() {
    }

    private void getCapsEdge() {
    }

    private void getCapsHtmlUnit() {
    }
}
