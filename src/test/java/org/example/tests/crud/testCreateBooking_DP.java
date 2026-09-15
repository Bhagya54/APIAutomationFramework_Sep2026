package org.example.tests.crud;

import io.qameta.allure.Description;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.pojos.Gson.CreateBookingResponse;
import org.example.utils.DataUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class testCreateBooking_DP extends BaseTest {
    //All positive and negative

    @Description("Verify Create Booking - Positive")
    @Test(groups = {"qa"},dataProviderClass = DataUtils.class,dataProvider = "dp")
        public void verifyCreateBooking_POST01(String firstname,String lastname,String depositPaid,String totalPrice,String checkin,String checkout,String additionalNeeds){
        boolean deposit_paid=Boolean.parseBoolean(depositPaid);
        int total_price=(int)Double.parseDouble(totalPrice);
        response=given()
                .spec(requestSpecification)
                .basePath(APIConstants.CREATE_UPDATE_GET)
                .body(payloadManager.bookingPayloadAsJsonString(firstname,lastname,deposit_paid,total_price,checkin,checkout,additionalNeeds))
                .log().all()
                .when()
                .post();

        validatableResponse=response.then().log().all();

        //extract the response
        CreateBookingResponse bookingResponse = payloadManager.createBookingResponse(response.asString());
        assertActions.verifyStatusCode(response,200);
        assertActions.verifyKeys(bookingResponse.getBooking().getFirstname(),firstname);
        assertActions.verifyKeys(bookingResponse.getBooking().getLastname(),lastname);

    }
}
