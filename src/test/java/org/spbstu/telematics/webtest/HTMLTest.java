package org.spbstu.telematics.webtest;

import org.spbstu.telematics.TestSite;
import org.spbstu.telematics.helper.ResourseLoader;
import org.testng.annotations.Test;
import java.util.Random;

public class HTMLTest extends BaseTest {

    @Test
    public void normalWorkTest() throws InterruptedException {
        TestSite.open();
        TestSite.loginPage.fillLoginPasswordForm(ResourseLoader.getUser("tester"));
        TestSite.loginPage.submitContactForm();
        for (int i = 0; i < 6; i++){
            TestSite.questionnairePage.clearForm();
            TestSite.questionnairePage.clearCheckButtons();
            TestSite.questionnairePage.clickChekButtons(new Random().nextInt(4));
            TestSite.questionnairePage.clickRadioButton(new Random().nextInt(4));
            ResourseLoader.generateRandomFieldData("test_1");
            TestSite.questionnairePage.fillForm(ResourseLoader.getFieldData("test_1"));
            TestSite.questionnairePage.submit();
            Thread.sleep(200);
            TestSite.questionnairePage.clickOK();
            softAssert.assertTrue(TestSite.questionnairePage.isExistAddedRow(ResourseLoader.getFieldData("test_1")));
        }
        softAssert.assertAll();
    }

    @Test
    public void invalidMailTest(){
        TestSite.open();
        TestSite.loginPage.fillLoginPasswordForm(ResourseLoader.getUser("user"));
        TestSite.loginPage.submitContactForm();
        softAssert.assertTrue(TestSite.loginPage.isShowInvalidEmailMessage());
        softAssert.assertAll();
    }

    @Test
    public void invalidMailPasswordTest(){
        TestSite.open();
        TestSite.loginPage.fillLoginPasswordForm(ResourseLoader.getUser("admin"));
        TestSite.loginPage.submitContactForm();
        softAssert.assertTrue(TestSite.loginPage.isShowInvalidEmailPasswordMessage());
        softAssert.assertAll();
    }
}
