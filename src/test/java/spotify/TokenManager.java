package spotify;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import spotify.utils.ConfigLoader;

import java.util.Date;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class TokenManager {
    private static String accessToken;
    private static long expirationTimeMillis;

    public synchronized static String getAccessToken() {
        if (accessToken == null || System.currentTimeMillis() > expirationTimeMillis) {
            accessToken = generateNewAccessToken();
        }
        return accessToken;
    }

    private static String generateNewAccessToken() {
        HashMap<String, String> formParam = new HashMap<>();
        formParam.put("client_id", ConfigLoader.getInstance().getClientId());
        formParam.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParam.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParam.put("grant_type", ConfigLoader.getInstance().getGrantType());

        Response response = given().baseUri("https://accounts.spotify.com")
                .contentType(ContentType.URLENC)
                .formParams(formParam)
                .when().post("/api/token")
                .then().extract().response();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to generate access token");
        }

        long expiresIn = response.jsonPath().getLong("expires_in"); // Expiration time in seconds
        expirationTimeMillis = System.currentTimeMillis() + expiresIn * 1000; // Convert seconds to milliseconds

        // Print expiration time
        System.out.println("Access Token will expire at: " + new Date(expirationTimeMillis));

        return response.jsonPath().getString("access_token");
    }
}

