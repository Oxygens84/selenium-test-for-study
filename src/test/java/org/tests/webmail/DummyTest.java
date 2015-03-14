package org.tests.webmail;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.tests.DriverFactory;

public class DummyTest {

    private WebDriver driver;
    private MainPageSteps steps;
        
    @Rule
    public TestName testName = new TestName();
    
    @Before
    public void runDriver() throws IOException {
        driver = DriverFactory.getDriver(testName.getMethodName());
        steps = new MainPageSteps(driver);
    }
    
    @Test
    public void testRegisterPage() {        
        steps.openMainPage();
        steps.checkMainPageTitle();
        steps.openInboxFolders();
    }
    
    @After
    public void closeDriver() {
        driver.quit();
    }
        
}