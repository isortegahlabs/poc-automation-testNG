package me.isortegah.pocs.tools.utils.selenium;

import me.isortegah.pocs.constants.LocatorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocateElements {
    private static final Logger logger = LogManager.getLogger(LocateElements.class);
    private static RemoteWebDriver driver;

    public LocateElements(RemoteWebDriver wd){
        driver = wd;
        logger.info("LocateElments SessionID: " + wd.getSessionId());
    }

    public WebElement findElement(By locator) throws InterruptedException {
        logger.info("findElement Sessionid: " + driver.getSessionId() + " locator--> " + locator.toString());
        return driver.findElement(locator);
    }

    public WebElement findElement( String locatorType , String value) throws InterruptedException {
        WebElement element = null;
        switch (LocatorType.valueOf(locatorType.toUpperCase()).toString()){
            case "XPATH":
                element = findElement(By.xpath(value));
                break;
        }
        return element;
    }

}
