package org.tests.php4dvd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {

    private final By loadingIndicator = By.id("loading");

    private final By resultFilmNames = By.xpath("//div[@id='results']//div[@class='title']");

    @FindBy(id = "q")
    private WebElement searchField;

    @FindBy(id = "category-button")
    private WebElement categoryButton;

    @FindBy(id = "sort-button")
    private WebElement sortButton;

    @FindBy(id = "n-button")
    private WebElement resultButton;

    @FindBy(xpath = "//ul[@id='category-menu']/li/a[text()='All categories']")
    private WebElement allCategoriesItem;

    @FindBy(xpath = "//ul[@id='n-menu']/li/a[text()='All results per page']")
    private WebElement allResultsItem;

    @FindBy(xpath = "//div[@id='results']/div[@class='content']")
    private WebElement resultContainer;

    @FindBy(id = "results")
    private WebElement resultDiv;


    public WebElement getResultDiv() {
        return resultDiv;
    }

    public By getLoadingIndicator() {
        return loadingIndicator;
    }

    public By getResultFilmNames() {
        return resultFilmNames;
    }

    public WebElement getAllCategoriesItem() {
        return allCategoriesItem;
    }

    public WebElement getAllResultsItem() {
        return allResultsItem;
    }

    public WebElement getResultContainer() {
        return resultContainer;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getCategoryButton() {
        return categoryButton;
    }

    public WebElement getSortButton() {
        return sortButton;
    }

    public WebElement getResultButton() {
        return resultButton;
    }
}
