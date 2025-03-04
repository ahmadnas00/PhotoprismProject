package TestAutomationProjectTesting;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import org.TestAutomationProject.DriverFactory;
import org.TestAutomationProject.Landingpage;
import org.TestAutomationProject.Library;
import org.TestAutomationProject.myLoginpage;
import org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v129.network.model.Request;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ApiTest {
    private static final String BaseURL1 = Landingpage.URL + "api/v1/photos";
    private static final String URLPreview = Landingpage.URL + "api/v1/photos/view?count=600&offset=0&merged=true&country=&camera=0&lens=0&label=&latlng=&year=0&month=0&color=&order=newest&q=&public=true&quality=3";
    private static final String URLArchive = Landingpage.URL + "api/v1/batch/photos/archive";
    private static final String URLPrivate = Landingpage.URL + "api/v1/batch/photos/private";
    private static final String URLRestore = Landingpage.URL + "api/v1/batch/photos/restore";

    private static WebDriver driver;
    private static myLoginpage loginPage;
    private static Library mylibrary;
    private static Landingpage home;

    private String Token = getAuthToken();
    private static String ImageID1 = "";


    public static String getAuthToken() {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body("{\"username\": \"admin\", \"password\": \"photoprism\"}")
                .post(Landingpage.URL + "api/v1/session");
        String token = response.jsonPath().getString("access_token");
        return token;
    }

//    @BeforeAll
//    public static void setUpData()throws MalformedURLException {
//        driver = DriverFactory.getDriver();
//        driver.manage().window().maximize();
//        driver.get(Landingpage.LoginURL);
//        try {
//            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
//            visitSiteButton.click();
//        } catch (TimeoutException err) {}
//        loginPage = new myLoginpage(driver);
//        home = loginPage.loginAsValidUser("admin", "photoprism");
//        try {
//            Thread.sleep(2000); // Wait for 2 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace(); // Handle the exception
//        }
//        driver.get(Landingpage.LibraryURL);
//        mylibrary = new Library(driver);
//        mylibrary.StartIndexing();
//        driver.get(Landingpage.URL);
//        ImageID1 = home.SearchByTitle("Unknown").getUID();
//        driver.quit();
//    }


    @BeforeEach
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    @Order(1)
    public void TestPreview() {
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + Token)
                .when()
                .get(URLPreview);

        assertEquals(200, response.statusCode(), "Status code should be 200");
        JsonPath jsonResponse = response.jsonPath();
        List<Map<String, Object>> photos = jsonResponse.getList("$");
        assertEquals(9, photos.size(), "The size of the photos list should be 9");
    }

//    @Test
//    @Order(2)
//    public void TestFavoriteImage() {
//        Response response = RestAssured
//                .given()
//                .baseUri(BaseURL1)
//                .header("Authorization", "Bearer " + Token)  // Authorization header
//                .when()
//                .post("/" + ImageID1 + "/like");
//
//        assertEquals(200, response.statusCode(), "Status code should be 200");
//
//        JsonPath jsonResponse = response.jsonPath();
//        boolean isFavorite = jsonResponse.getBoolean("photo.Favorite");
//        assertTrue(isFavorite, "The photo should be marked as Favorite");
//    }
//
//    @Test
//    @Order(3)
//    public void TestUnFavoriteImage() {
//        Response response = RestAssured
//                .given()
//                .baseUri(BaseURL1)
//                .header("Authorization", "Bearer " + Token)  // Authorization header
//                .when()
//                .delete("/" + ImageID1 + "/like");
//
//        assertEquals(200, response.statusCode(), "Status code should be 200");
//
//        JsonPath jsonResponse = response.jsonPath();
//        boolean NotFavorite = jsonResponse.getBoolean("photo.Favorite");
//        assertFalse(NotFavorite, "The photo should be marked as Favorite");
//
//    }

    @Test
    @Order(4)
    public void TestPrivateImage() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("photos", new String[]{ImageID1});

        Response response = RestAssured
                .given()
                .baseUri(URLPrivate)
                .header("Authorization", "Bearer " + Token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();

        assertEquals(200, response.statusCode(), "Status code should be 200");
        JsonPath jsonResponse = response.jsonPath();
        String message = jsonResponse.getString("message");
        assertEquals("Selection marked as private", message, "The response message should match the expected value");
    }

    @Test
    @Order(5)
    public void TestUnprivateImage() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("photos", new String[]{ImageID1});
        requestBody.put("action", "unprivate");

        Response response = RestAssured
                .given()
                .baseUri(URLPrivate)
                .header("Authorization", "Bearer " + Token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();

        assertEquals(200, response.statusCode(), "Status code should be 200");
        JsonPath jsonResponse = response.jsonPath();
        String message = jsonResponse.getString("message");
        assertEquals("Selection marked as private", message, "The response message should match the expected value");
    }

    @Test
    @Order(6)
    public void TestSendToArchive() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("photos", new String[]{ImageID1});
        Response response = RestAssured
                .given()
                .baseUri(URLArchive)
                .header("Authorization", "Bearer " + Token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        assertEquals(200, response.statusCode(), "Status code should be 200");
        JsonPath jsonResponse = response.jsonPath();
        String message = jsonResponse.getString("message");
        assertEquals("Selection archived", message, "The response message should match the expected value");
    }

    @Test
    @Order(7)
    public void TestRestoreFromArchive() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("photos", new String[]{ImageID1});
        Response response = RestAssured
                .given()
                .baseUri(URLRestore)
                .header("Authorization", "Bearer " + Token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        assertEquals(200, response.statusCode(), "Status code should be 200");
        JsonPath jsonResponse = response.jsonPath();
        String message = jsonResponse.getString("message");
        assertEquals("Selection restored", message, "The response message should match the expected value");
    }


}
