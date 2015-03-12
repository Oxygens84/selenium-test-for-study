package org.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class MainPage {
    
    public static final String URL = "http://demos.telerik.com/aspnet-ajax/webmail/default.aspx";

    public static final String PAGE_TITLE = "Telerik Web Mail Demo - Inbox";

    @FindBy(xpath = "//ul[@class='rtUL']//span[@class='rtIn']")
    private List<WebElement> inboxFolders;

    @FindBy(css = "#ctl00_ContentPlaceHolder2_RadGrid1_ctl00")
    private WebElement mailListGrid;

    private By loadIndicator = By.cssSelector(".RadAjax");


    public List<WebElement> getInboxFolders() {
        return inboxFolders;
    }

    public WebElement getMailListGrid() {
        return mailListGrid;
    }

    public By getLoadIndicator() {
        return loadIndicator;
    }
}