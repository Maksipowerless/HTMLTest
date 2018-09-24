package org.spbstu.telematics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.spbstu.telematics.factoryPageObjects.QuestionnairePage;
import org.spbstu.telematics.factoryPageObjects.LoginPage;

public class TestSite
{
    public static LoginPage loginPage;
    public static QuestionnairePage questionnairePage;
    private static WebDriver driver;

    public static void init(WebDriver driver) {
        TestSite.loginPage = PageFactory.initElements(driver, LoginPage.class);
        TestSite.questionnairePage = PageFactory.initElements(driver, QuestionnairePage.class);
        TestSite.driver = driver;
    }

    public static void open() {
        driver.navigate().to("file:///home/maxfromperek/Downloads/java-qa-test.html");
    }
}
