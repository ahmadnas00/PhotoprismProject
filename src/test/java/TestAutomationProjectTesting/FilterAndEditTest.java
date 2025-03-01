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

import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FilterAndEditTest {
    private static WebDriver driver;
    private static Library mylibrary;
    private static myLoginpage loginPage;
    private static Landingpage home;
    private String filePath = "C:\\Users\\an833\\Downloads\\random1.jpg";
    private static String ImgName = "Zebra";

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
    public void setUpClass() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(Landingpage.LoginURL);
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {System.out.println("Ngrok warning page was not loaded");}
        loginPage = new myLoginpage(driver);
        home = loginPage.loginAsValidUser("admin", "photoprism");
    }

    @Test
    @Order(1)
    public void TestSendToArchive(){
        home.SearchByTitle("Unknown").HoverFirstImage().select().OpenOptions().SendToArchive();
        assertEquals("Unknown",home.GoToArchive().SearchByTitle("Unknown").getFirstImageTitle());
    }

    @Test
    @Order(2)
    public void TestToggleView() {
        assertTrue(home.ClickToggleView1());
        assertTrue(home.ClickToggleView2());
        assertTrue(home.ClickToggleView3());
    }

    @Test
    @Order(3)
    public void SearchTitleTest() {assertTrue(home.SearchByTitle(ImgName).getFirstImageTitle().contains(ImgName));}


    @Test
    @Order(4)
    public void TestFilterByYear(){assertEquals("Unknown",home.OpenYearDropDown("2017").getFirstImageTitle());}

    @Test
    @Order(5)
    public void TestFilterByCamera(){assertEquals("Unknown",home.OpenCameraDropDown("Apple iphone SE").getFirstImageTitle());
    }

    @Test
    @Order(6)
    public void TestFilterByMonth(){assertEquals("Botanical Garden / Berlin / 2013",home.OpenMonthDropDown("June").getFirstImageTitle());}

    @Test
    @Order(7)
    public void TestFilterByColor(){
        assertEquals("Lawnwood Snake Sanctuary / George / 2013",home.OpenColorDropDown("Gold").getFirstImageTitle());
    }

    @Test
    @Order(8)
    public void TestFilterByUploadTime(){
        assertEquals("Unknown",home.OpenTimeDropDown("Newest First").getFirstImageTitle());
    }

    @Test
    @Order(9)
    public void TestImageSelect(){home.SearchByTitle("Unknown").HoverFirstImage().select();}

    @Test
    @Order(10)
    public void TestImageUnselect(){
        home.SearchByTitle("Unknown").HoverFirstImage().select().OpenOptions().ClearPick();
    }

    @Test
    @Order(11)
    public void TestImageShareCancel(){
        home.SearchByTitle("Unknown").HoverFirstImage().select();
        home.OpenOptions().share().sharecancel();
    }

    @Test
    @Order(12)
    public void TestUploadadnApproveImage(){
        home.Addimage(filePath);
        home.GoToreview().approveimage().GoToLandingPage();
        assertTrue(home.SearchByTitle("Random").getFirstImageTitle().contains("Random"));
    }

    @Test
    @Order(13)
    public void MarkFavoriteandUnFavorite() {
        home.SearchByTitle("Unknown").Favoritefirstimage();
        assertTrue(home.GoToFavorites().SearchByTitle("Unknown").getFirstImageTitle().contains("Unknown"), "The image title doesn't contain the search term");
        home.SearchByTitle("Unknown").UnFavoritefirstimage();
        assertFalse(home.GoToFavorites().SearchByTitle("Unknown").isTitleInFavorites("Unknown"),"The image is in the Favorites section");
    }

    @Test
    @Order(14)
    public void HeartIconChanging() {
        home.SearchByTitle("Unknown");
        String initialIconClass = home.GetinitialIcon();
        home.Favoritefirstimage();
        String updatedIconClass = home.GetfinalIcon();
        home.UnFavoritefirstimage();
        assertNotEquals(initialIconClass,updatedIconClass);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
