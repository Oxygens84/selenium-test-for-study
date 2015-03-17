package org.tests.webmail;

import java.io.IOException;

import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.tests.DriverFactory;
import org.tests.ProxyFactory;
import org.tests.common.ProxySteps;
import org.tests.php4dvd.LoginPageSteps;
import org.tests.php4dvd.SearchPageSteps;

public class DummyTest {

    private WebDriver driver;
    private MainPageSteps steps;
    private ProxySteps proxySteps;
    private ProxyServer bmp;
        
    @Rule
    public TestName testName = new TestName();
    
    @Before
    public void runDriver() throws Exception {

        bmp = ProxyFactory.initProxy();
        driver = DriverFactory.getDriverWithProxy(bmp);
        //driver = DriverFactory.getDriver(testName.getMethodName());
        proxySteps = new ProxySteps(bmp);
        steps = new MainPageSteps(driver);
    }
    
    @Test
    public void testRegisterPage() {        
        steps.openMainPage();
        steps.checkMainPageTitle();
        steps.openInboxFolders();
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