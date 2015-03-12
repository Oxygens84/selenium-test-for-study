package org.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageSteps {
    
    private final WebDriver driver;    
    private final MainPage page;

    public MainPageSteps(WebDriver driver) {
        this.driver = driver;
        this.page = new MainPage();
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(ajaxElementLocatorFactory, this.page);
    }
    
    public void openMainPage() {
        driver.manage().window().maximize();
        driver.navigate().to(MainPage.URL);
    }

    public void checkMainPageTitle() {
        Assert.assertEquals(page.PAGE_TITLE, driver.getTitle());
    }

    public void openInboxFolders() {
        List<WebElement> inboxFolders = page.getInboxFolders();
        for (WebElement inboxFolder : inboxFolders) {
            clickFolder(inboxFolder);
            webDriverWaitUntil(elementToBeClickable(page.getMailListGrid()));
        }
    }

//    public void openInboxFolders() {
//        List<WebElement> inboxFolders = page.getInboxFolders();
//        for (WebElement inboxFolder : inboxFolders) {
//            System.out.println("folderName = " + inboxFolder.getText());
//            inboxFolder.click();
//            waitUntilListRefresh();
//        }
//    }

    private void clickFolder(final WebElement inboxFolder) {
        System.out.println("Processing folder = " + inboxFolder.getText());
        Utils.tryAttempts(3, new Runnable() {
            @Override
            public void run() {
                inboxFolder.click();
                waitUntilListRefresh();
            }
        });
    }

    private void waitUntilListRefresh() {
        webDriverWaitUntil(visibilityOfElementLocated(page.getLoadIndicator()));
        webDriverWaitUntil(invisibilityOfElementLocated(page.getLoadIndicator()));
    }

    private void webDriverWaitUntil(ExpectedCondition condition) {
        (new WebDriverWait(driver, 10, 100)).until(condition);
    }

}