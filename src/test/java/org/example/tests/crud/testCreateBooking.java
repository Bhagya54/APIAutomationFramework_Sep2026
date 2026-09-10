package org.example.tests.crud;

import io.qameta.allure.Description;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.pojos.Gson.CreateBookingResponse;
import org.example.utils.DataUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class testCreateBooking extends BaseTest {
    //All positive and negative

    @Description("Verify Create Booking - Positive")
    @Test(groups = {"qa"},dataProviderClass = DataUtils.class,dataProvider = "dp")
        public void verifyCreateBooking_POST01(String firstName, String lastName, String DepositPaid, String totalPrice, String checkin, String checkout, String additionalNeeds){
        boolean depositPaid=Boolean.parseBoolean(DepositPaid);
        int totalPaid=(int)Double.parseDouble(totalPrice);
        response=given()
                .spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_GET)
                .body(payloadManager.bookingPayloadAsJsonString(firstName,lastName,depositPaid,totalPaid,checkin,checkout,additionalNeeds))
                .log().all()
                .when()
                .post();
        log.info("Payload has been sent:");
        validatableResponse=response.then().log().all();

        //extract the response
        CreateBookingResponse bookingResponse = payloadManager.createBookingResponse(response.asString());
        assertActions.verifyStatusCode(response,200);
        assertActions.verifyKeys(bookingResponse.getBooking().getFirstname(),firstName);
        assertActions.verifyKeys(bookingResponse.getBooking().getLastname(),lastName);
        log.info("Create Booking Positive Test Passed");
    }
}
