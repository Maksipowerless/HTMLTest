package org.spbstu.telematics.webtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.spbstu.telematics.TestSite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    SoftAssert softAssert;

    @BeforeSuite
    public void beforeSuite(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        TestSite.init(driver);
        softAssert = new SoftAssert();
    }

    @AfterSuite
    public void afterSuite(){
        driver.close();
    }
}
