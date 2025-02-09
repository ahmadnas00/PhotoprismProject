package TestAutomationProjectTesting;
import org.TestAutomationProject.DriverFactory;
import org.TestAutomationProject.Landingpage;
import org.TestAutomationProject.Review;
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

public class FilterAndEditTest {
    private static WebDriver driver;
    private static myLoginpage loginPage;
    private static Landingpage home;
    private static String myTitle = "Will Shrek";
    private static String Pyramids = "Pyramids of Giza";
    private static String ShoppingCart = "Shoppingcart";
    private String filePath = "C:\\Users\\an833\\Downloads\\random1.jpg";

    @BeforeEach
    public void setUpClass() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        // Local
        // driver.get("http://localhost:2342/library/login");

        // WorkFlow
        driver.get("https://70cd-212-199-36-114.ngrok-free.app/library/login");
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {System.out.println("Ngrok warning page was not loaded");}
        loginPage = new myLoginpage(driver);
        home = loginPage.loginAsValidUser("admin", "insecure");
    }

    @Test
    @Order(1)
    public void TestSendToArchive(){
        home.SearchByTitle(ShoppingCart).HoverFirstImage().select().OpenOptions().SendToArchive();
        assertEquals(ShoppingCart,home.GoToArchive().SearchByTitle(ShoppingCart).getFirstImageTitle());
    }

    @Test
    @Order(2)
    public void TestToggleView() {
        assertTrue(home.ClickToggleView1());
        assertTrue(home.ClickToggleView2());
        assertTrue(home.ClickToggleView3());
    }


    @Test
    @Order(4)
    public void TestFilterByYear(){assertEquals(Pyramids,home.OpenYearDropDown("2017").getFirstImageTitle());}

    @Test
    @Order(5)
    public void TestFilterByCamera(){assertEquals(ShoppingCart,home.OpenCameraDropDown("Canon EOS R5").getFirstImageTitle());
    }

    @Test
    @Order(6)
    public void TestFilterByMonth(){assertEquals(ShoppingCart,home.OpenMonthDropDown("February").getFirstImageTitle());}

    @Test
    @Order(7)
    public void TestFilterByColor(){
        assertEquals("El-classico Win",home.OpenColorDropDown("Purple").getFirstImageTitle());
    }

    @Test
    @Order(8)
    public void TestFilterByCategory(){
        assertEquals("Beautiful colorful bird",home.OpenCategoryDropDown("Animal").getFirstImageTitle());
    }

    @Test
    @Order(9)
    public void TestFilterByUploadTime(){
        assertEquals(myTitle,home.OpenTimeDropDown("Newest First").getFirstImageTitle());
    }

    @Test
    @Order(10)
    public void TestImageSelect(){home.SearchByTitle(ShoppingCart).HoverFirstImage().select();}

    @Test
    @Order(11)
    public void TestImageUnselect(){
        home.SearchByTitle(ShoppingCart).HoverFirstImage().select().OpenOptions().ClearPick();
    }

    @Test
    @Order(12)
    public void TestImageShareCancel(){
        home.SearchByTitle(ShoppingCart).HoverFirstImage().select();
        home.OpenOptions().share().sharecancel();
    }

    @Test
    @Order(13)
    public void TestUploadadnApproveImage(){
        home.Addimage(filePath);
        home.GoToreview().approveimage().GoToLandingPage();
        assertTrue(home.SearchByTitle("Random").getFirstImageTitle().contains("Random"));
    }

    @Test
    @Order(14)
    public void MarkFavoriteandUnFavorite() {
        home.SearchByTitle(myTitle).Favoritefirstimage();
        assertTrue(home.GoToFavorites().SearchByTitle(myTitle).getFirstImageTitle().contains(myTitle), "The image title doesn't contain the search term");
        home.SearchByTitle(myTitle).UnFavoritefirstimage();
        assertFalse(home.GoToFavorites().SearchByTitle(myTitle).isTitleInFavorites(myTitle),"The image is in the Favorites section");
    }

    @Test
    @Order(15)
    public void HeartIconChanging() {
        home.SearchByTitle(myTitle);
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
