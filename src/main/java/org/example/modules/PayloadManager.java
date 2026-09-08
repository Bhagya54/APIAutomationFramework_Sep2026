package org.example.modules;

import com.google.gson.Gson;
import org.example.pojos.Gson.*;

public class PayloadManager {
    Gson gson =new Gson();
    Booking booking=new Booking();
    BookingDates bookingDates=new BookingDates();
    BookingResponse bookingResponse;
    Auth auth = new Auth();
    AuthResponse authResponse;

    //Serialization - Java object - Json string
    //Create Booking
    public String bookingPayloadAsString(){
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(234);
        booking.setDepositpaid(true);

        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("lunch");

        String bookingPayloadString=gson.toJson(booking);
        return bookingPayloadString;

    }


    //Deserialisation - JSON response string - Java object
    public BookingResponse createBookingResponse(String bookingResponseString){
        bookingResponse=gson.fromJson(bookingResponseString,BookingResponse.class);
        return bookingResponse;
    }
}
