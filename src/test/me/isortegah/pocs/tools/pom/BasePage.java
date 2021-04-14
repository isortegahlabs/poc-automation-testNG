package me.isortegah.pocs.tools.pom;

import me.isortegah.pocs.tools.utils.selenium.LocateElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    public RemoteWebDriver driver;
    public LocateElements locateElem;

    public BasePage(){}

    public void init(RemoteWebDriver rwd){
        driver = rwd;
        locateElem = new LocateElements(rwd);
    }

    public void goToPage(String url){
        driver.get(url);
    }
}
