package org.example.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationTC {
     /*
    Create a booking
    Create a token
    Get the booking
    Updtae the booking- patch/put
    Delete the booking

     */

    @Test(groups="qa",priority=1)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 1.Verify the booking is created")
    public void createBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups="qa",priority=2)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 2.Verify the booking details")
    public void getBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups="qa",priority=3)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 3.Verify the update booking ")
    public void updateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups="qa",priority=2)
    @Owner("Bhagya")
    @Description("TCINT#1 - Step 4.Verify the delete booking ")
    public void deleteBooking(){
        Assert.assertTrue(true);
    }

}
