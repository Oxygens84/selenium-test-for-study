package org.tests;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class DummyTest {

    private WebDriver driver;
    private RegisterPageSteps steps;
        
    @Rule
    public TestName testName = new TestName();
    
    @Before
    public void runDriver() throws IOException {
        driver = DriverFactory.getDriver(testName.getMethodName());
        steps = new RegisterPageSteps(driver);
    }
    
    //@Test
    public void testRegisterPage() {        
        steps.openRegisterPage();
        steps.ensureRegisterPage();        
    }
    
    @Test
    public void testInvalidEmail() {        
        steps.openRegisterPage();
        steps.ensureRegisterPage();
        steps.fillRegister("invalidEmail","validPassword","validPassword");
        steps.submitRegister();
        steps.ensureErrorMessage("Please enter a valid email address.");
    }
    
    //@Test
    public void testPasswordMismatch() {        
        steps.openRegisterPage();
        steps.ensureRegisterPage();
        steps.fillRegister("validEmail@example.com","validPassword","invalidPassword");
        steps.submitRegister();  
        steps.ensureErrorMessage("Password entries must match.");        
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
        
}