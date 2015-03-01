package org.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageSteps {
    
    private final WebDriver driver;    
    private final RegisterPage page;

    public RegisterPageSteps(WebDriver driver) {
        this.driver = driver;
        this.page = PageFactory.initElements(driver, RegisterPage.class);
    }
    
    public void openRegisterPage() {
        driver.navigate().to(RegisterPage.URL);
    }

    public void ensureRegisterPage() {
        Assert.assertEquals("Free Registration - The New York Times", driver.getTitle());    
    }   

    public void fillRegister(String email, String password1, String password2) {
        page.getEmailField().sendKeys(email);
        page.getPassword1Field().sendKeys(password1);
        page.getPassword2Field().sendKeys(password2);
    }
    
    public void submitRegister() {
        page.getSubmitButton().click();
    }

    public void ensureErrorMessage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, page.getErrorMessage().getText());
    }
    
}