package me.isortegah.pocs.tools.pom;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.utils.selenium.LocateElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    private static WebDriver driver;
    public static LocateElements locateElem = LocateElements.getInstance();

    public BasePage(){
        driver = DriverFactory.getInstance().getDriver();
    }

    public void goToPage(String url){
        driver.get(url);
    }


}
