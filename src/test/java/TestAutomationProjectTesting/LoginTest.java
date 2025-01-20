package TestAutomationProjectTesting;

import org.TestAutomationProject.DriverFactory;
import org.TestAutomationProject.Landingpage;
import org.TestAutomationProject.Review;
import org.TestAutomationProject.myLoginpage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;


public class LoginTest {
    WebDriver driver;
    private myLoginpage loginPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        driver = DriverFactory.getDriver();
       // driver.get("http://localhost:2342/library/login");
        driver.get("https://1761-83-229-24-163.ngrok-free.app/library/login");
        loginPage = new myLoginpage(driver);
    }

    @Test
    public void TestValidLogin() {
        Landingpage home = loginPage.loginAsValidUser("admin", "insecure");
        assertTrue(home.isLoggedInSuccessfully());
    }

    @Test
    public void TestInvalidLogin(){
        myLoginpage page1 = loginPage.loginInValidUser("ahmad", "password123");
        assertTrue(page1.isNotLoggedIn());
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}

