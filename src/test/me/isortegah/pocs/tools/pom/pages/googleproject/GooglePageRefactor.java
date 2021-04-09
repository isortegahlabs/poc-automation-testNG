package me.isortegah.pocs.tools.pom.pages.googleproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GooglePageRefactor {

    private static final Logger logger = LogManager.getLogger(GooglePageRefactor.class);
    protected static RemoteWebDriver driver = null;

    public GooglePageRefactor(){
    }

    public GooglePageRefactor setDriver(RemoteWebDriver driver){
        this.driver = driver;
        return this;
    }

    public GooglePageRefactor goTo(){
        driver.get("https://google.com.mx");
        return this;
    }

    public GooglePageRefactor search(String searchTopic) throws InterruptedException {
        logger.info("Thread: " + Thread.currentThread().getId());
        driver.findElement(new By.ByName("q")).sendKeys(searchTopic);
        driver.findElement(new By.ByName("q")).submit();
        Thread.sleep(2000);
        //WebElement element = locateElem.findElement(new By.ByName("q"));
        //element.sendKeys(searchTopic);
        //element.submit();
        return this;
    }

    public GooglePageRefactor selectTopic(String resultTopic , String typeLocator) throws InterruptedException {
        logger.info("Thread: " + Thread.currentThread().getId() + " SessionId selectTopic: " + driver.getSessionId());
        //driver.findElement(By.xpath("//div[@id='res']//a[@href='https://www.amazon.com.mx/']")).click();
        //WebElement element = locateElem.findElement(typeLocator, resultTopic);
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("arguments[0].click();",element);
        //element.click();
        return this;
    }

}
