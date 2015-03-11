package org.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import org.openqa.selenium.By;

public class MainPageSteps {
    
    private final WebDriver driver;    
    private final MainPage page;

    public MainPageSteps(WebDriver driver) {
        this.driver = driver;
        this.page = PageFactory.initElements(driver, MainPage.class);
    }
    
    public void openMainPage() {
        driver.navigate().to(MainPage.URL);
        driver.manage().window().maximize();
    }

    public void checkMainPageTitle() {
        Assert.assertEquals("Telerik Web Mail Demo - Inbox", driver.getTitle());
    }

    public void openInboxFolders() {
        List<WebElement> inboxFolders = page.getInboxFolders();
        for (WebElement inboxFolder : inboxFolders) {
            System.out.println("folderName = " + inboxFolder.getText());
            inboxFolder.click();
            waitUntilListRefresh();
            //Assert.assertTrue(driver.findElement(By.linkText("From")).isEnabled());
        }
    }

    private void waitUntilListRefresh() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(page.getLoadIndicator()));
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.invisibilityOfElementLocated(page.getLoadIndicator()));
    }


}