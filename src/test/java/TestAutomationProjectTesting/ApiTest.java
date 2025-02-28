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
    private static final String URLSearch = Landingpage.URL + "api/v1/photos?count=120&offset=0&merged=true&country=&camera=0&lens=0&label=&latlng=&year=0&month=0&color=&order=newest&q=Will&public=true&quality=3";
    private static final String URLPreview = Landingpage.URL + "api/v1/photos/view?count=600&offset=0&merged=true&country=&camera=0&lens=0&label=&latlng=&year=0&month=0&color=&order=newest&q=&public=true&quality=3";
    private static final String URLFILTER = Landingpage.URL + "api/v1/photos?count=120&offset=0&merged=true&country=sa&camera=0&lens=0&label=&latlng=&year=2025&month=1&color=&order=newest&q=&public=true&quality=3";
    private static final String URLArchive = Landingpage.URL + "api/v1/batch/photos/archive";
    private static final String URLPrivate = Landingpage.URL + "api/v1/batch/photos/private";
    private static final String URLRestore = Landingpage.URL + "api/v1/batch/photos/restore";

    private String Token = "032a6580ec98060d740a05aca4d768a60c72668173fbc256";
    private String ImageID1 = "psq67z7mnbi77zgy";


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
        assertEquals(14, photos.size(), "The size of the photos list should be 14");
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

//    @Test
//    @Order(4)
//    public void TestSendSearch() {
//        Response response = RestAssured
//                .given()
//                .header("Authorization", "Bearer " + Token)
//                .when()
//                .get(URLSearch);
//        assertEquals(200, response.statusCode(), "Status code should be 200");
//        JsonPath jsonResponse = response.jsonPath();
//        List<Map<String, Object>> photos = jsonResponse.getList("$");
//        assertEquals(1, photos.size(), "There should be exactly one photo in the response");
//        String title = photos.getFirst().get("Title").toString();
//        assertEquals("Will Shrek", title, "The title of the photo should match the search query");
//    }
//
//    @Test
//    @Order(5)
//    void TestSendFilters() {
//        Response response = RestAssured
//                .given()
//                .header("Authorization", "Bearer " + Token)
//                .when()
//                .get(URLFILTER);
//        assertEquals(200, response.statusCode(), "Status code should be 200");
//        JsonPath jsonResponse = response.jsonPath();
//        List<Map<String, Object>> photos = jsonResponse.getList("$");
//        assertEquals(2, photos.size(), "There should be exactly one photo in the response");
//        String title = photos.getFirst().get("Title").toString();
//        assertEquals("El-classico Win", title, "The title of the photo should match the search query");
//
//
//    }

    @Test
    @Order(6)
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
    @Order(7)
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
    @Order(8)
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
    @Order(9)
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
