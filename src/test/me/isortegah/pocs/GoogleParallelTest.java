package me.isortegah.pocs;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GoogleParallelTest {

    WebDriver driver;
    String nodeURL;

    @Parameters({"browser"})
    @BeforeMethod()
    public void setUp(String browser) throws MalformedURLException
    {
        if(browser.equalsIgnoreCase("CHROME"))
        {
            nodeURL = "http://localhost:4444/wd/hub";
            System.out.println("Chrome Browser Initiated");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.LINUX);

            driver = new RemoteWebDriver(new URL(nodeURL),capabilities);

            driver.get("https://google.com.mx");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        else
        if(browser.equalsIgnoreCase("FIREFOX"))
        {
            nodeURL = "http://localhost:4444/wd/hub";
            System.out.println("Firefox Browser Initiated");
            DesiredCapabilities capabilities1 = DesiredCapabilities.firefox();
            capabilities1.setBrowserName("firefox");
            capabilities1.setPlatform(Platform.LINUX);

            driver = new RemoteWebDriver(new URL(nodeURL),capabilities1);

            driver.get("https://google.com.mx");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @Test
    public void appleSite() throws InterruptedException
    {
        try
        {

            driver.findElement(new By.ByName("q")).sendKeys("amazon méxico");
            driver.findElement(new By.ByName("q")).submit();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[@id='res']//a[@href='https://www.amazon.com.mx/']")).click();
            Thread.sleep(2000);

        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @AfterMethod()
    public void tearDown()
    {
        driver.quit();
        System.out.println("Browser Closed");
    }
}
