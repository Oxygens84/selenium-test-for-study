package org.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
    
    public static final String URL = "https://myaccount.nytimes.com/register";
    
    @FindBy(id = "submit")
    private WebElement submitButton;
    
    @FindBy(id = "email_address")
    private WebElement emailField;
    
    @FindBy(id = "password1")
    private WebElement password1Field;
    
    @FindBy(id = "password2")
    private WebElement password2Field;
    
    @FindBy(className = "error")
    private WebElement errorMessage;

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPassword1Field() {
        return password1Field;
    }

    public WebElement getPassword2Field() {
        return password2Field;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }   
    
}