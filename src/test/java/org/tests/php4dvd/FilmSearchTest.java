package org.tests.php4dvd;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.tests.DriverFactory;

import java.io.IOException;
import java.util.*;

public class FilmSearchTest {

    private WebDriver driver;
    private FilmList filmList;
    private LoginPageSteps loginSteps;
    private SearchPageSteps searchSteps;
        
    @Rule
    public TestName testName = new TestName();
    
    @Before
    public void initialize() throws IOException {

        driver = DriverFactory.getDriver(testName.getMethodName());
        loginSteps = new LoginPageSteps(driver);
        searchSteps = new SearchPageSteps(driver);

        loginSteps.openLoginPage();
        loginSteps.validateLoginPage();
        loginSteps.login(System.getProperty("php4dvd.user"), System.getProperty("php4dvd.password"));

        searchSteps.validateFilmListPage();
        searchSteps.resetFilters();
        List<String> fullFilmList = searchSteps.collectResults();
        Assert.assertFalse("Film list is empty",fullFilmList.isEmpty());
        filmList = new FilmList(fullFilmList);
    }

//    @Test
//    public void testSearchFirstFilm() {
//        String firstFilm = fullFilmList.get(0);
//        searchSteps.validateSearchResult(firstFilm, fullFilmList);
//    }

    @Test
    public void testSearchRandomFilm() {
        String randomFilm = filmList.getRandomFilm();
        searchSteps.validateSearchResult(randomFilm, filmList.getFullList());
    }

    @Test
    public void testSearchNonExistFilm() {
        String nonExistFilm = filmList.calculateNonExistFilm();
        searchSteps.validateNoSearchResult(nonExistFilm);
    }

    @Test
    public void testPartialName() {
        List<String> listLongEnoughFilms = filmList.getLongEnoughFilms();
        Assert.assertFalse("No film with names long enough",listLongEnoughFilms.isEmpty());
        String randomFilm = filmList.getRandomFilm(listLongEnoughFilms);
        String randomPartialFilm = randomFilm.substring(1,randomFilm.length()-2);
        searchSteps.validateSearchResult(randomPartialFilm, filmList.getFullList());
    }

    @Test
    public void testMostFrequentFilm() {
        String mostFrequentFilm = filmList.findMostFrequentFilm();
        searchSteps.validateSearchResult(mostFrequentFilm, filmList.getFullList());
    }


    @After
    public void closeDriver() {
        driver.quit();
    }
        
}