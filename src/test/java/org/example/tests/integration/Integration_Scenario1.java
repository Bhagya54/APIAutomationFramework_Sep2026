package org.example.tests.integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.example.pojos.Gson.Booking;
import org.example.pojos.Gson.CreateBookingResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Integration_Scenario1 extends BaseTest {
    /*
    Create a booking -bookingid
    Create a token - token
    Get the booking - /booking/bookingid
    Updtae the booking- patch/put - token,bookingid - update
    Delete the booking-token/bookingid
     */
    @Test(groups="qa",priority=1)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 1.Verify the booking is created")
    public void createBooking(ITestContext iTestContext){
        response=given()
                .spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_GET)
                .body(payloadManager.bookingPayloadAsJsonString())
                .when().post();
        validatableResponse=response.then().log().all().statusCode(200);

        CreateBookingResponse createBookingResponse=payloadManager.createBookingResponse(response.asString());
        String firstname=createBookingResponse.getBooking().getFirstname();
        int bookingId=createBookingResponse.getBookingid();
        iTestContext.setAttribute("b_id",bookingId);

    }

    @Test(groups="qa",priority=2)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 2.Verify the booking details")
    public void getBooking(ITestContext iTestContext){
//{{prod_baseURL}}/booking/1
        Integer bookingId=(Integer)iTestContext.getAttribute("b_id");
        response=given()
                .spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_GET+"/"+bookingId)
                .log().all()
                .when().get();
        validatableResponse=response.then().log().all().statusCode(200);
        Booking booking=payloadManager.getUpdateResponse(response.asString());
        String firstName=booking.getFirstname();//actual Result
        String lastName=booking.getLastname();//actual Result
        assertActions.verifyKeys(firstName,"Jim");
        assertActions.verifyResponseBody(lastName,"Brown","Last name is not matching");
    }

    @Test(groups="qa",priority=3)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 3.Verify the update booking ")
    public void updateBooking(ITestContext iTestContext){
//{{prod_baseURL}}/booking/613
        //token
        Integer bookingId=(Integer)iTestContext.getAttribute("b_id");
        response =given().spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_GET+"/"+bookingId)
                .body(payloadManager.updateBookingPayloadAsJsonString())
                .cookie("token",getToken())
                .log().all()
                .when()
                .put();

        validatableResponse=response.then().statusCode(200);
        Booking booking=payloadManager.getUpdateResponse(response.asString());
        String firstName=booking.getFirstname();//actual Result
        String lastName=booking.getLastname();//actual Result
        assertActions.verifyKeys(firstName,"Kaira");
        assertActions.verifyResponseBody(lastName,"Jeswal","Last name is not matching");

    }

    @Test(groups="qa",priority=4)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 4.Verify the delete booking ")
    public void deleteBooking(ITestContext iTestContext){
//token
        Integer bookingId=(Integer)iTestContext.getAttribute("b_id");
        response=given()
                .spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_GET+"/"+bookingId)
                .cookie("token",getToken())
                .log().all()
                .when().delete();
        validatableResponse=response.then().log().all().statusCode(201);
    }

}
