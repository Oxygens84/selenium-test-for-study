package org.tests.php4dvd;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class SearchPageSteps {

    private final WebDriver driver;
    private final SearchPage page;

    public SearchPageSteps(WebDriver driver) {
        this.driver = driver;
        this.page = new SearchPage();
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(ajaxElementLocatorFactory, this.page);
    }

    public void validateFilmListPage(){
        webDriverWaitUntil(ExpectedConditions.visibilityOf(page.getSearchField()));
        webDriverWaitUntil(ExpectedConditions.visibilityOf(page.getCategoryButton()));
        webDriverWaitUntil(ExpectedConditions.visibilityOf(page.getSortButton()));
        webDriverWaitUntil(ExpectedConditions.visibilityOf(page.getResultButton()));
    }

    public List<String> collectResults() {
        List<String> titles = new ArrayList<>();
        List<WebElement> titleElements = driver.findElements(page.getResultFilmNames());
        for (WebElement titleFilm : titleElements) {
            titles.add(titleFilm.getText());
        }
        System.out.println(titles);
        return titles;
    }

    private void waitForResultList() {
        webDriverWaitUntil(ExpectedConditions.invisibilityOfElementLocated(page.getLoadingIndicator()));
        webDriverWaitUntil(ExpectedConditions.visibilityOf(page.getResultDiv()));
        waitForCalmingDown();
    }

    private void waitForCalmingDown() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForEmptyResult() {
        webDriverWaitUntil(ExpectedConditions.invisibilityOfElementLocated(page.getLoadingIndicator()));
        webDriverWaitUntil(ExpectedConditions.visibilityOf(page.getResultContainer()));
    }

    public void resetFilters() {
        page.getSearchField().clear();
        page.getCategoryButton().click();
        page.getAllCategoriesItem().click();
        page.getResultButton().click();
        page.getAllResultsItem().click();
        waitForResultList();
    }

    public void validateSearchResult(String testingFilmName, List<String> fullFilmList) {
        List<String> expectedFilmList = new ArrayList<>();
        for (String film : fullFilmList) {
            if (film.toUpperCase().contains(testingFilmName.toUpperCase())) {
                expectedFilmList.add(film);
            }
        }
        inputSearchString(testingFilmName);
        waitForResultList();
        List<String> foundFilmList = collectResults();
        Assert.assertEquals(expectedFilmList,foundFilmList);
    }

    public void validateNoSearchResult(String nonExistFilm) {
        inputSearchString(nonExistFilm);
        waitForEmptyResult();
        String result = page.getResultContainer().getText();
        //Assert.assertEquals("No movies were found.",result);
        Assert.assertEquals("No movies where found.",result);  // grammar error
    }

    private void inputSearchString(String searchString) {
        page.getSearchField().clear();
        page.getSearchField().sendKeys(searchString);
        page.getSearchField().sendKeys(Keys.ENTER);
    }

    private void webDriverWaitUntil(ExpectedCondition condition) {
        (new WebDriverWait(driver, 10, 100)).until(condition);
    }
}
