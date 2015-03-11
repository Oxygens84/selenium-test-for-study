package org.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class MainPage {
    
    public static final String URL = "http://demos.telerik.com/aspnet-ajax/webmail/default.aspx";

//    @FindBys({
//            @FindBy(xpath = "//ul[@class='rtUL']"),
//            @FindBy(xpath = "span[@class='rtIn']")
//    })
//    List<WebElement> inboxFolders;

    @FindBy(xpath = "//ul[@class='rtUL']//span[@class='rtIn']")
    List<WebElement> inboxFolders;

    private By loadIndicator = By.cssSelector(".RadAjax");

    public List<WebElement> getInboxFolders() {
        return inboxFolders;
    }

    public By getLoadIndicator() {
        return loadIndicator;
    }
}