package me.isortegah.pocs;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GoogleParallelTestThreadLocal {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(String browser) throws MalformedURLException
    {
        System.out.println(browser + " Browser Initiated");
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.toLowerCase());
        capabilities.setPlatform(Platform.LINUX);
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities));
    }

    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    @Test
    public void appleSite() throws InterruptedException
    {
        try
        {
            getDriver().navigate().to("http://google.com.mx");
            getDriver().findElement(new By.ByName("q")).sendKeys("amazon méxico");
            getDriver().findElement(new By.ByName("q")).submit();
            Thread.sleep(2000);

            getDriver().findElement(By.xpath("//div[@id='res']//a[@href='https://www.amazon.com.mx/']")).click();
            Thread.sleep(2000);

        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass
    void terminate () {
        //Remove the ThreadLocalMap element
        driver.remove();
    }
}
