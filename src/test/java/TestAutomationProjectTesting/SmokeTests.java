package TestAutomationProjectTesting;
import org.TestAutomationProject.DriverFactory;
import org.TestAutomationProject.Landingpage;
import org.TestAutomationProject.QRCodeDecoder;
import org.TestAutomationProject.myLoginpage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.zxing.*;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmokeTests {
    private static String Pyramids = "Pyramids of Giza";
    private static String Egypt = "Egypt";
    private static String WillShrek = "Will Shrek";
    private static WebDriver driver;
    private static myLoginpage loginPage;
    private static Landingpage home;

    @BeforeEach
    public void setUpClass() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(Landingpage.LoginURL);
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {}
        loginPage = new myLoginpage(driver);
        home = loginPage.loginAsValidUser("admin", "photoprism");
    }


    @Test
    public void SearchTitleTest() {assertTrue(home.SearchByTitle(WillShrek).getFirstImageTitle().contains(WillShrek));}

    @Test
    public void TestFilterByCountry(){
        assertEquals(Pyramids,home.OpenCountryDropDown(Egypt).getFirstImageTitle());
    }

    @Test
    public void TestGalleryEmpty() {
        assertFalse(home.isGalleryEmpty());
    }

    @Test
    public void TestReloadButton(){
        assertTrue(home.ClickReloadButton().IsUpdateToastVisible());
    }

    @Test
    public void TestQRFeature() throws Exception {
        WebElement qrImage = home.GenerateQR(WillShrek);
        assertTrue(qrImage.isDisplayed(), "QR Code was not displayed!");
        String decodedContent = QRCodeDecoder.decodeQRCode(qrImage);
        assertTrue(decodedContent.startsWith("https://i.imgur.com"), "QR Code content does not start with 'https://i.imgur.com'!");
        System.out.println("QR Code is displayed and contains the correct URL: " + decodedContent);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
