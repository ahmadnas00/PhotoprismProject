package TestAutomationProjectTesting;

import org.TestAutomationProject.DriverFactory;
import org.TestAutomationProject.Landingpage;
import org.TestAutomationProject.Review;
import org.TestAutomationProject.myLoginpage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class LoginTest {
    WebDriver driver;
    private myLoginpage loginPage;

//    @BeforeEach
//    public void setUp() throws MalformedURLException {
//        driver = DriverFactory.getDriver();
//        driver.get(Landingpage.LoginURL);
//        try {
//            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
//            visitSiteButton.click();
//        } catch (TimeoutException err) {System.out.println("Ngrok warning page was not loaded");}
//        loginPage = new myLoginpage(driver);
//    }
//
//    @Test
//    public void TestValidLogin() {
//        Landingpage home = loginPage.loginAsValidUser("admin", "photoprism");
//        assertTrue(home.isLoggedInSuccessfully());
//    }
//
//    @Test
//    public void TestInvalidLogin(){
//        myLoginpage page1 = loginPage.loginInValidUser("ahmad", "password123");
//        assertTrue(page1.isNotLoggedIn());
//    }
//
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
//

}

