package org.tests.php4dvd;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    public static final String URL = "http://barancev.w.pw/php4dvd/";

    public static final String PAGE_TITLE = " My movie collection - php4dvd v2.0 ";

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "submit")
    private WebElement submit;

    public WebElement getUserName() {
        return userName;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getSubmit() {
        return submit;
    }
}
