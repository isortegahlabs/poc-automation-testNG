package me.isortegah.pocs.tools.pom.pages.googleproject;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.pom.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(GooglePage.class);

    public GooglePage(WebDriver wd){
        super(wd);
    }

    public GooglePage goTo(){
        goToPage("https://google.com.mx");
        return this;
    }

    public GooglePage search(String searchTopic){
        WebElement element = locateElem.findElement(new By.ByName("q"));
        element.sendKeys(searchTopic);
        element.submit();
        return this;
    }

    public GooglePage selectTopic(String resultTopic , String typeLocator) throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = locateElem.findElement(typeLocator, resultTopic);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element);
        //element.click();
        return this;
    }

}
