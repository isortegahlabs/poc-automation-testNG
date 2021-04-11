package me.isortegah.pocs.tools.pom.pages.googleproject;

import me.isortegah.pocs.tools.pom.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GooglePageRefactor extends BasePage {

    private static final Logger logger = LogManager.getLogger(GooglePageRefactor.class);

    public GooglePageRefactor(){
        super();
    }

    public GooglePageRefactor setDriver(RemoteWebDriver rwd){
        init(rwd);
        return this;
    }

    public GooglePageRefactor goTo(){
        logger.info("goTo--> Thread: " + Thread.currentThread().getId() + " sessionID: " + driver.getSessionId());
        goToPage("https://google.com.mx");
        return this;
    }

    public void search(String searchTopic) throws InterruptedException {

        WebElement element = locateElem.findElement(new By.ByName("q"));
        element.sendKeys(searchTopic);
        element.submit();
        logger.info("Search --> Thread: " + Thread.currentThread().getId() + " sessionID: " + driver.getSessionId());
        Thread.sleep(2000);
    }

    public void selectTopic(String resultTopic , String typeLocator) throws InterruptedException {
        logger.info("SelectTopic --> Thread: " + Thread.currentThread().getId() + " SessionId selectTopic: " + driver.getSessionId());
        driver.findElement(By.xpath("//div[@id='res']//a[@href='https://www.amazon.com.mx/']")).click();
        //WebElement element = locateElem.findElement(typeLocator, resultTopic);
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("arguments[0].click();",element);
        //element.click();
    }

}
