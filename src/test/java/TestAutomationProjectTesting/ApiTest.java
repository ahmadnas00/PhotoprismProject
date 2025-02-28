package TestAutomationProjectTesting;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import org.TestAutomationProject.Landingpage;
import org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v129.network.model.Request;

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
    private String Token = "3f133532dde495a728148069c7a78768bd7914f8343b2cb1";
    private String ImageID1 = "psse85db4e1t1du6";

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

    @Test
    @Order(2)
    public void TestFavoriteImage() {
        Response response = RestAssured
                .given()
                .baseUri(BaseURL1)
                .header("Authorization", "Bearer " + Token)  // Authorization header
                .when()
                .post("/" + ImageID1 + "/like");

        assertEquals(200, response.statusCode(), "Status code should be 200");

        JsonPath jsonResponse = response.jsonPath();
        boolean isFavorite = jsonResponse.getBoolean("photo.Favorite");
        assertTrue(isFavorite, "The photo should be marked as Favorite");
    }

    @Test
    @Order(3)
    public void TestUnFavoriteImage() {
        Response response = RestAssured
                .given()
                .baseUri(BaseURL1)
                .header("Authorization", "Bearer " + Token)  // Authorization header
                .when()
                .delete("/" + ImageID1 + "/like");

        assertEquals(200, response.statusCode(), "Status code should be 200");

        JsonPath jsonResponse = response.jsonPath();
        boolean NotFavorite = jsonResponse.getBoolean("photo.Favorite");
        assertFalse(NotFavorite, "The photo should be marked as Favorite");

    }

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
