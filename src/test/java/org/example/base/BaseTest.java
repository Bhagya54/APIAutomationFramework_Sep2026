package org.example.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.asserts.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseTest {
    //Common for all the testcases
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public AssertActions assertActions;


    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

//        requestSpecification=given()
//                .baseUri(APIConstants.BASE_URL)
//                .contentType(ContentType.JSON);

        requestSpecification=new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build();


    }
}
