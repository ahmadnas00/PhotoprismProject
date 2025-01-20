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

    // Local
    // driver.get("http://localhost:2342/library/login");

    private static WebDriver driver;
    private static myLoginpage loginPage;
    private static Landingpage home;
    private String filePath = "C:\\Users\\an833\\Downloads\\random1.jpg";

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
    public void TestUploadadnApproveImage(){
        home.Addimage(filePath);
        home.GoToreview().approveimage().GoToLandingPage();
        assertTrue(home.SearchByTitle("Random").getFirstImageTitle().contains("Random"));
    }

    @Test
    public void TestGalleryEmpty() {
        assertFalse(home.isGalleryEmpty());
    }

    @Test
    public void TestReloadButton(){
        home.ClickReloadButton();
        assertTrue(home.IsUpdateToastVisible(), "The toast notification was not visible after reloading the page.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
