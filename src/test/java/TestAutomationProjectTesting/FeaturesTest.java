package TestAutomationProjectTesting;
import org.TestAutomationProject.Landingpage;
import org.TestAutomationProject.Review;
import org.TestAutomationProject.myLoginpage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeaturesTest {


    public static final String baseURL = "https://1761-83-229-24-163.ngrok-free.app/library/browse";
    // driver.get("http://localhost:2342/library/login");

    private static WebDriver driver;
    private static myLoginpage loginPage;
    private static Landingpage home;
    private static String myTitle = "Will Shrek";
    private String filePath = "C:\\Users\\an833\\Downloads\\random1.jpg";

    @BeforeEach
    public void setUpClass() throws MalformedURLException {
        driver = Review.DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        loginPage = new myLoginpage(driver);
        home = loginPage.loginAsValidUser("admin", "insecure");
    }

    @Test
    @Order(1)
    public void TestUploadadnApproveImage(){
        home.Addimage(filePath);
        home.GoToreview().approveimage().GoToLandingPage();
        assertTrue(home.SearchByTitle("Random").getFirstImageTitle().contains("Random"));
    }


    @Test
    @Order(3)
    public void TestGalleryEmpty() {
        assertFalse(home.isGalleryEmpty());
    }

    @Test
    @Order(4)
    public void TestReloadButton(){
        home.ClickReloadButton();
        assertTrue(home.IsUpdateToastVisible(), "The toast notification was not visible after reloading the page.");
    }

    @Test
    @Order(5)
    public void SearchTitleTest() {
        home.SearchByTitle(myTitle);
        assertTrue(home.getFirstImageTitle().contains(myTitle), "The image title doesn't contain the search term");
    }

    @Test
    @Order(6)
    public void MarkFavoriteandUnFavorite() {
        home.SearchByTitle(myTitle).Favoritefirstimage();
        assertTrue(home.GoToFavorites().SearchByTitle(myTitle).getFirstImageTitle().contains(myTitle), "The image title doesn't contain the search term");
        home.SearchByTitle(myTitle).UnFavoritefirstimage();
        assertFalse(home.GoToFavorites().SearchByTitle(myTitle).isTitleInFavorites(myTitle),"The image is in the Favorites section");
    }

    @Test
    @Order(7)
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
