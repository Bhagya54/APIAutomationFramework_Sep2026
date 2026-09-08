package org.example.tests.crud;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.example.pojos.Gson.BookingResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class testCreateBooking extends BaseTest {
    //All positive and negative

    @Description("Verify Create Booking - Positive")
    @Test(groups = {"qa"})
    public void verifyCreateBooking_POST01(){
        response=given()
                .spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_GET)
                .body(payloadManager.bookingPayloadAsString())
                .log().all()
                .when()
                .post();

        validatableResponse=response.then().log().all();

        //extract the response
        BookingResponse bookingResponse = payloadManager.createBookingResponse(response.asString());
        assertActions.verifyStatusCode(response,200);
        assertActions.verifyKeys(bookingResponse.getBooking().getFirstname(),"Jim");
        assertActions.verifyKeys(bookingResponse.getBooking().getLastname(),"Brown");

    }
}
