package org.example.modules;

import com.google.gson.Gson;
import org.example.pojos.Gson.*;

public class PayloadManager {
    Gson gson =new Gson();
    Booking booking=new Booking();
    BookingDates bookingDates=new BookingDates();
    CreateBookingResponse createBookingResponse;

    Auth auth = new Auth();
    AuthResponse authResponse;

    //Serialization - Java object - Json string
    //Create Booking
    public String bookingPayloadAsJsonString(String firstName, String lastName, boolean DepositPaid, int totalPrice, String checkin, String checkout, String additionalNeeds) {
        booking.setFirstname(firstName);
        booking.setLastname(lastName);
        booking.setDepositpaid(DepositPaid);
        booking.setTotalprice(totalPrice);
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds(additionalNeeds);
        String bookingPayloadString = gson.toJson(booking);
        return bookingPayloadString;
    }

    public String bookingPayloadAsJsonString() {
        booking.setFirstname("Hari");
        booking.setLastname("Komal");
        booking.setDepositpaid(true);
        booking.setTotalprice(123);
        bookingDates.setCheckin("2025-01-01");
        bookingDates.setCheckout("2026-01-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("dinner");
        String bookingPayloadString = gson.toJson(booking);
        return bookingPayloadString;
    }


    //Deserialisation - JSON response string - Java object
    public CreateBookingResponse createBookingResponse(String bookingResponseString){
        createBookingResponse=gson.fromJson(bookingResponseString,CreateBookingResponse.class);
        return createBookingResponse;
    }

    //AuthPayload - Serialization - java object to jsonString
    public String authPayloadAsString(){
        auth.setUsername("admin");
        auth.setPassword("password123");
        String jsonPayload=gson.toJson(auth);
        return jsonPayload;
    }

    //Deserialization - extract the token
    public String getAuthResponse(String responseAsString){
        authResponse=gson.fromJson(responseAsString,AuthResponse.class);
        String token=authResponse.getToken();
        return token;
    }

    public String updateBookingPayloadAsString(String firstName, String lastName, boolean DepositPaid, int totalPrice, String checkin, String checkout, String additionalNeeds){
        booking.setFirstname(firstName);
        booking.setLastname(lastName);
        booking.setDepositpaid(DepositPaid);
        booking.setTotalprice(totalPrice);
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds(additionalNeeds);

        String updatedbookingPayloadString=gson.toJson(booking);
        return updatedbookingPayloadString;

    }

    public String updateBookingPayloadAsString(){
        booking.setFirstname("Govardhan");
        booking.setLastname("hhh");
        booking.setDepositpaid(false);
        booking.setTotalprice(234);
        bookingDates.setCheckin("2025-01-01");
        bookingDates.setCheckout("2026-01-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("uuu");

        String updatedbookingPayloadString=gson.toJson(booking);
        return updatedbookingPayloadString;

    }

    public Booking updateGetResponse(String responseString){
        booking=gson.fromJson(responseString,Booking.class);
        return booking;
    }
}
