package me.isortegah.pocs.tools.pom.pages.googleproject;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.pom.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GooglePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(GooglePage.class);
    private static ThreadLocal<GooglePage> instance = new ThreadLocal<>();

    private GooglePage(){
        super(DriverFactory.getInstance().getDriver());
    }

    public static GooglePage getInstance(){
        return instance.get();
    }

    public static void setInstance(){
        instance.set(new GooglePage());
    }

    public GooglePage goTo() throws InterruptedException {
        goToPage("https://google.com.mx");
        Thread.sleep(1000);
        return this;
    }

    public GooglePage search(String searchTopic) throws InterruptedException {
        WebElement element = locateElem.findElement(new By.ByName("q"));
        element.sendKeys(searchTopic);
        element.submit();
        Thread.sleep(2000);
        return this;
    }

    public GooglePage selectTopic(String resultTopic , String typeLocator) throws InterruptedException {
        WebElement element = locateElem.findElement(typeLocator, resultTopic);
        driver.executeScript("arguments[0].click();",element);
        return this;
    }

}
