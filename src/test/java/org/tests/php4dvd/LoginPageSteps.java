package org.tests.php4dvd;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPageSteps {

    private final WebDriver driver;
    private final LoginPage page;

    public LoginPageSteps(WebDriver driver) {
        this.driver = driver;
        this.page = new LoginPage();
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(ajaxElementLocatorFactory, this.page);
    }

    public void openLoginPage() {
        driver.navigate().to(LoginPage.URL);
    }

    public void checkPageTitle(String expectedTitle) {
        Assert.assertEquals(expectedTitle, driver.getTitle().trim());
    }

    public void login(String userName, String password) {
        page.getUserName().sendKeys(userName);
        page.getPassword().sendKeys(password);
        page.getSubmit().click();
    }

    public void validateLoginPage(){
        checkPageTitle(LoginPage.PAGE_TITLE);
        Assert.assertTrue(page.getUserName().isDisplayed());
        Assert.assertTrue(page.getPassword().isDisplayed());
        Assert.assertTrue(page.getSubmit().isDisplayed());
    }

}
