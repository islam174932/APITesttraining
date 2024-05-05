package spotify;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class BaseTest {
    private RequestSpecBuilder specBuilder;
    private ResponseSpecBuilder responseSpecBuilder;
    protected static RequestSpecification requested;
    protected static ResponseSpecification response;



    @BeforeMethod
    public void setUp() throws FileNotFoundException {
        String accessToken = TokenManager.getAccessToken();

        PrintStream fileLog = new PrintStream(new File("restassured.log"));
        specBuilder = new RequestSpecBuilder();
        specBuilder.addFilter(new RequestLoggingFilter(fileLog)).addFilter(new ResponseLoggingFilter(fileLog));
        //specBuilder.setBaseUri(System.getProperty("Base_URI"));
        specBuilder.setBaseUri("https://api.spotify.com/v1");
        specBuilder.addHeader("Authorization", "Bearer " + accessToken);
        specBuilder.setContentType("application/json;charset=utf-8");
        requested = specBuilder.build();

        responseSpecBuilder = new ResponseSpecBuilder();
        response = responseSpecBuilder.build();
    }


}