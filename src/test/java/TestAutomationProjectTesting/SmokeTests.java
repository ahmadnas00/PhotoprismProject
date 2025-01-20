package TestAutomationProjectTesting;
import org.TestAutomationProject.DriverFactory;
import org.TestAutomationProject.Landingpage;
import org.TestAutomationProject.myLoginpage;
import org.junit.jupiter.api.*;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmokeTests {

    // Workflow
    public static final String baseURL = "https://1761-83-229-24-163.ngrok-free.app/library/login";
    private static String Pyramids = "Pyramids of Giza";
    private static String Egypt = "Egypt";
    // Local
    // driver.get("http://localhost:2342/library/login");
    private static String WillShrek = "Will Shrek";
    private static WebDriver driver;
    private static myLoginpage loginPage;
    private static Landingpage home;

    @BeforeEach
    public void setUpClass() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {System.out.println("Ngrok warning page was not loaded");}
        loginPage = new myLoginpage(driver);
        home = loginPage.loginAsValidUser("admin", "insecure");
    }


    @Test
    public void SearchTitleTest() {
        home.SearchByTitle(WillShrek);
        assertTrue(home.getFirstImageTitle().contains(WillShrek));
    }

    @Test
    public void TestFilterByCity(){
        assertEquals(Pyramids,home.OpenCountryDropDown(Egypt).getFirstImageTitle());
    }

    @Test
    public void TestGalleryEmpty() {
        assertFalse(home.isGalleryEmpty());
    }

    @Test
    public void TestReloadButton(){
        home.ClickReloadButton();
        assertTrue(home.IsUpdateToastVisible());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
