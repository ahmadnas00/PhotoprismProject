package TestAutomationProjectTesting;
import org.TestAutomationProject.*;
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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmokeTests {
    private static String ImgName = "Zebra";
    private static String ImgName2 = "Chameleon / Saint-Paul / 2015";
    private static WebDriver driver;
    private static myLoginpage loginPage;
    private static Library mylibrary;

    private static Landingpage home;

    @BeforeAll
    public static void setUpData()throws MalformedURLException {
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

        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception
        }
        driver.get(Landingpage.LibraryURL);
        mylibrary = new Library(driver);
        mylibrary.StartIndexing();
        driver.quit();
    }

    @BeforeEach
    public void setLogin() throws MalformedURLException {
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
    public void TestFilterByCountry(){
        assertEquals(ImgName2,home.OpenCountryDropDown("France").getFirstImageTitle());
    }

    @Test
    public void TestFilterByCategory(){
        assertEquals(ImgName2,home.OpenCategoryDropDown("Reptile").getFirstImageTitle());
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
        WebElement qrImage = home.GenerateQR(ImgName);
        assertTrue(qrImage.isDisplayed(), "QR Code was not displayed!");

        String decodedContent = QRCodeDecoder.decodeQRCode(qrImage);
        assertTrue(decodedContent.startsWith("https://i.ibb.co"), "QR Code content does not start with 'https://i.ibb.co'!");
        System.out.println("QR Code is displayed and contains the correct URL: " + decodedContent);


        testInternetAccess(decodedContent);
        BufferedImage generatedQR = fetchImageFromURL(decodedContent);
        BufferedImage originalQR = ImageIO.read(new File("src/test/resources/Original_img.jpg"));
        assertTrue(compareImages(generatedQR, originalQR), "Generated QR Code does not match the original image!");
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }



    public boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }
        for (int y = 0; y < imgA.getHeight(); y++) {
            for (int x = 0; x < imgA.getWidth(); x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private BufferedImage fetchImageFromURL(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0"); // Prevents blocking
        InputStream inputStream = connection.getInputStream();
        return ImageIO.read(inputStream); // Load the image into memory
    }

    private void testInternetAccess(String imageUrl) throws Exception {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD"); // Just checks if the URL is reachable
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.connect();
            System.out.println("Successfully connected to " + imageUrl);
        } catch (Exception e) {
            throw new Exception("Unable to reach image URL: " + imageUrl, e);
        }
    }
}
