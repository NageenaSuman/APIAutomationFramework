package org.nageena.POJOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseBooking {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private Booking booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }
    //Mentioning class in booking as the response will have json which needs to be converted
    // into objects
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}

