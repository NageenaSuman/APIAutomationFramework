package org.nageena.Modules;


import com.google.gson.Gson;
import org.nageena.POJOs.*;

//
public class PayloadManager {
    //Serialization
    Gson gson;
    public String CreateBooking(){

    // creating instance of class for payload filling
    // Class 1 - Booking
    Booking booking = new Booking();
        booking.setFirstname("Hash Bash Tash");
        booking.setLastname("Brown");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);
    // Class 2 - Bookingdates
    Bookingdates booking_d = new Bookingdates();
        booking_d.setCheckin("2024-09-08");
        booking_d.setCheckout("2024-09-16");
    // Making booking to include booking dates
        booking.setBookingdates(booking_d);


    gson = new Gson();
    String payload = gson.toJson(booking);

    return payload;
    }

    //DeSerialization
    public ResponseBooking GettingResponse(String response){

        Gson gson = new Gson();
        ResponseBooking response_json = gson.fromJson(response, ResponseBooking.class);
        // The main reason to call the ResponseBooking class in method instead of string is
        // because we need the class name(.class) to be mentioned while GSON deserialization
        // so while returning too you will return the class object not a string

        System.out.println("This is response - " + response); // this is response in json
        System.out.println("This is class object - " + response_json); //this is response in class object

        return response_json;

    }

    public String set_token(){
        //call a class of auth pojo
        //Serialization
        gson = new Gson();
        AuthToken auth =new AuthToken();
        auth.setUsername("admin");
        auth.setPassword("password123");
        String auth_payload = gson.toJson(auth);
        return auth_payload;

    }

    public String getting_token(String response1){
        gson = new Gson();
        TokenResponse tokenResponse = gson.fromJson(response1, TokenResponse.class); //from pojo
        System.out.println(tokenResponse);
        return tokenResponse.getToken();


    }

    public String update_booking(){

        Booking booking = new Booking();
        booking.setFirstname("Nageena");
        booking.setLastname("PK");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);

    }

}



