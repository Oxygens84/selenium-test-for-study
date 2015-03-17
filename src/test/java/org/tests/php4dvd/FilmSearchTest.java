package org.tests.php4dvd;

import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.tests.DriverFactory;
import org.tests.ProxyFactory;
import org.tests.common.ProxySteps;

import java.util.*;

public class FilmSearchTest {

    private WebDriver driver;
    private FilmList filmList;
    private LoginPageSteps loginSteps;
    private SearchPageSteps searchSteps;
    private ProxySteps proxySteps;
    private ProxyServer bmp;

    @Rule
    public TestName testName = new TestName();


    @Before
    public void initialize() throws Exception {

        bmp = ProxyFactory.initProxy();
        driver = DriverFactory.getDriverWithProxy(bmp);

        loginSteps = new LoginPageSteps(driver);
        searchSteps = new SearchPageSteps(driver);
        proxySteps = new ProxySteps(bmp);

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
//        proxySteps.validateHar();
//    }

    @Test
    public void testSearchRandomFilm() {
        String randomFilm = filmList.getRandomFilm();
        searchSteps.validateSearchResult(randomFilm, filmList.getFullList());
        proxySteps.validateHar();
    }

    @Test
    public void testSearchNonExistFilm() {
        String nonExistFilm = filmList.calculateNonExistFilm();
        searchSteps.validateNoSearchResult(nonExistFilm);
        proxySteps.validateHar();
    }

    @Test
    public void testPartialName() {
        List<String> listLongEnoughFilms = filmList.getLongEnoughFilms();
        Assert.assertFalse("No film with names long enough",listLongEnoughFilms.isEmpty());
        String randomFilm = filmList.getRandomFilm(listLongEnoughFilms);
        String randomPartialFilm = randomFilm.substring(1,randomFilm.length()-2);
        searchSteps.validateSearchResult(randomPartialFilm, filmList.getFullList());
        proxySteps.validateHar();
    }

    @Test
    public void testMostFrequentFilm() {
        String mostFrequentFilm = filmList.findMostFrequentFilm();
        searchSteps.validateSearchResult(mostFrequentFilm, filmList.getFullList());
        proxySteps.validateHar();
    }


    @After
    public void closeDriver() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (bmp != null) {
            bmp.stop();
        }
    }

}