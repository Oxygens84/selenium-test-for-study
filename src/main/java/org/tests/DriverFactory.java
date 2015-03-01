package org.tests;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
    
    public static WebDriver getDriver() throws IOException {
        PropertyReader reader = new PropertyReader();
        Properties props = reader.readPropertyFile();
        for (Object key: props.keySet()) {
            System.setProperty(key.toString(),props.getProperty(key.toString()));
        }        
        String driver = System.getProperty("driver");
        switch(driver) {
            case "FireFox": 
                return new FirefoxDriver();
            case "IE":                 
                return new InternetExplorerDriver();         
            case "Chrome":                 
                return new ChromeDriver();
            case "PhantomJS": 
                return new PhantomJSDriver(new DesiredCapabilities());
            case "remote": 
                return new RemoteWebDriver(null);  
            default:
                return new FirefoxDriver();
        }        
    }   
    
}
