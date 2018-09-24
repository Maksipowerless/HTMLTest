package org.spbstu.telematics.factoryPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.spbstu.telematics.factoryPageObjects.entities.User;

public class LoginPage {

    @FindBy(id = "loginEmail")
    WebElement email;

    @FindBy(id = "loginPassword")
    WebElement password;

    @FindBy(id = "authButton")
    WebElement buttonSubmit;

    @FindBy(id = "emailFormatError")
    WebElement invalidEmail;

    @FindBy(id = "invalidEmailPassword")
    WebElement invalidEmailPassword;

    public void fillLoginPasswordForm(User user){
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
    }

    public void submitContactForm(){
        this.buttonSubmit.click();
    }

    public boolean isShowInvalidEmailMessage(){
        return invalidEmail.isDisplayed();
    }

    public boolean isShowInvalidEmailPasswordMessage(){
        return invalidEmailPassword.isDisplayed();
    }
}

