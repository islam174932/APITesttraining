package spotify;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import spotify.utils.Statuscode;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests extends BaseTest {
    @BeforeMethod
    public void beforeMethod(Method method) {
        System.out.println("Start testing " + method.getName());
        System.out.println("ThreadID: " + Thread.currentThread().getId());
    }
    @Test(description = "Create a new playlist")
    public void createPlaylist() throws FileNotFoundException {

        String data = "{\n" +
                "    \"name\": \"New Playlist for songs 01032388\",\n" +
                "    \"description\": \"New playlist islam i am here to test \",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .spec(requested)
                .body(data)
                .when()
                .post("/users/31fqvnb6azttv7s27yrxagplcice/playlists")
                .then()
                .spec(response)
                .statusCode(Statuscode.CODE_201.code)
                .statusLine(equalTo(Statuscode.CODE_201.message)); // Using both code and message from the enum
    }

    /*
    @Test(description = "Delete a playlist")
    public void deletePlaylist() {
        given()
                .spec(requested)
                .when()
                .delete("/playlists/5E0bzoC1DzMddAKrQ7VFdA")
                .then()
                .spec(response)
                .statusCode(Statuscode.CODE_400.code)
                .statusLine(equalTo(Statuscode.CODE_400.message)); // Using both code and message from the enum
    }


     */
    @Test(description = "Retrieve a playlist")
    public void retrievePlaylist() {
        given()
                .spec(requested)
                .when()
                .get("/playlists/3UUmk3u7atzc4hvOOrp2VP")
                .then()
                .spec(response)
                .statusCode(Statuscode.CODE_200.code)
                .statusLine(equalTo(Statuscode.CODE_200.message)); // Using both code and message from the enum
    }

    @Test(description = "Retrieve a playlist by ID")
    public void retrievePlaylistByID() {
        given()
                .spec(requested)
                .when()
                .get("https://api.spotify.com/v1/playlists/5E0bzoC1DzMddAKrQ7VFdA")
                .then()
                .log().body() // Log the response body
                .spec(response)
                .assertThat()
                .body("name", equalTo("Updated Playlist for songs 2010"))
                .statusCode(Statuscode.CODE_200.code)
                .statusLine(equalTo(Statuscode.CODE_200.message)); // Using both code and message from the enum
    }

    @Test(description = "Update a playlist")
    public void updatePlaylist() {
        String data = "{\n" +
                "    \"name\": \"Updated Playlist for songs 2034\",\n" +
                "    \"description\": \"Updated total playlist description mmmmmmmmmmmmmmmmmmmmm\",\n" +
                "    \"public\": true\n" +
                "}";
        given()
                .spec(requested)
                .body(data)
                .when()
                .put("/playlists/3qmR5zAVv6daEZ4bO6sl5r")
                .then()
                .spec(response)
                .statusCode(Statuscode.CODE_200.code)
                .statusLine(equalTo(Statuscode.CODE_200.message)); // Using both code and message from the enum
    }
}


