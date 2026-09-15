package org.example.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.example.asserts.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.example.utils.ExcelReader;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseTest {
    //Common for all the testcases
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public AssertActions assertActions;
    public ExcelReader excel=new ExcelReader(".\\src\\test\\resources\\data\\testData.xlsx");
    public Logger log= Logger.getLogger(BaseTest.class);
    public FileInputStream fis;
    public Properties dataProp=new Properties();
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            fis=new FileInputStream("./src/test/resources/properties/log4j.properties");
            PropertyConfigurator.configure(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            fis = new FileInputStream("./src/test/resources/properties/data.properties");
            dataProp.load(fis);
            log.info("Data Properties file has been loaded");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

//        requestSpecification=given()
//                .baseUri(APIConstants.BASE_URL)
//                .contentType(ContentType.JSON);

        log.info("API Test has been started");
        requestSpecification = new RequestSpecBuilder()
                //.setBaseUri(APIConstants.BASE_URL)
                .setBaseUri(dataProp.getProperty("URL"))
                .addHeader("Content-Type", "application/json")
                .build();

    }

    public String getToken() {
        requestSpecification = given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL)
                .contentType(ContentType.JSON)
                .body(payloadManager.authPayloadAsString());
        response = requestSpecification.log().all().when().post();
        validatableResponse = response.then().statusCode(200);
        String token = payloadManager.getAuthResponse(response.asString());
        return token;

    }
}
