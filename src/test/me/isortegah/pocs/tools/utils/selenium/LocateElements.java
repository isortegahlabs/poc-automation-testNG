package me.isortegah.pocs.tools.utils.selenium;

import me.isortegah.pocs.constants.LocatorType;
import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocateElements {
    private static final Logger logger = LogManager.getLogger(LocateElements.class);
    private static LocateElements locateElements;
    private static WebDriver driver;

    public LocateElements(WebDriver wd){
        driver = wd;
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public WebElement findElement( String locatorType , String value) {
        WebElement element = null;
        switch (LocatorType.valueOf(locatorType.toUpperCase()).toString()){
            case "XPATH":
                element =  findElement(By.xpath(value));
                break;
        }
        return element;
    }

}
