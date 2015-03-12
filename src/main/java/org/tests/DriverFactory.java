package org.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    public static WebDriver getDriver(String testName) throws IOException {
        mixInPropertyFile();
        String environment = System.getProperty("environment");
        switch(environment) {
            case "local":
                return localDriver();
            case "saucelab":
                return saucelabDriver(testName);
            default:
                throw new RuntimeException("Invalid environment: " + environment);
        }
    }

    private static void mixInPropertyFile() throws IOException {
        PropertyReader reader = new PropertyReader();
        Properties props = reader.readPropertyFile();
        for (Object key: props.keySet()) {
            System.setProperty(key.toString(),props.getProperty(key.toString()));
        }
    }

    private static WebDriver localDriver() {
        String driver = System.getProperty("driver");
        switch(driver) {
            case "FireFox":
                return new FirefoxDriver();
            case "IE":
                return new InternetExplorerDriver();
            case "Chrome":
                return new ChromeDriver();
            //case "PhantomJS":
            //    return new PhantomJSDriver(new DesiredCapabilities());
            case "remote":
                return new RemoteWebDriver(null);
            default:
                throw new RuntimeException("Invalid driver: " + driver);
        }
    }

    private static WebDriver saucelabDriver(String testName) throws MalformedURLException {
        String driver = System.getProperty("driver");
        DesiredCapabilities capabilities;
        switch(driver) {
            case "FireFox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "IE":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case "Chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            //case "PhantomJS":
            //    capabilities = DesiredCapabilities.phantomjs();
            //    break;
            default:
                throw new RuntimeException("Invalid driver: " + driver);
        }
        capabilities.setCapability("name", testName);
        String userName = System.getProperty("saucelab.user");
        String userPassword = System.getProperty("saucelab.password");
        String url = System.getProperty("saucelab.url");
        url = url.replace("{{saucelab.user}}", userName);
        url = url.replace("{{saucelab.password}}", userPassword);
        return new RemoteWebDriver(
                new URL(url),
                capabilities);
    }

}
