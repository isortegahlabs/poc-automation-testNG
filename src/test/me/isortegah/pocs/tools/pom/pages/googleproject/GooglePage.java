package me.isortegah.pocs.tools.pom.pages.googleproject;

import me.isortegah.pocs.tools.driverfactory.DriverFactory;
import me.isortegah.pocs.tools.pom.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage extends BasePage {

    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(GooglePage.class);
    private static GooglePage googlePage;

    private GooglePage(){
        driver = DriverFactory.getInstance().getDriver();
    }

    public static GooglePage getInstance(){
        if ( googlePage == null )
            googlePage = new GooglePage();
        return googlePage;
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

    public GooglePage selectTopic(String resultTopic , String typeLocator){
        WebElement element = locateElem.findElement(typeLocator, resultTopic);
        element.click();
        return this;
    }

}
