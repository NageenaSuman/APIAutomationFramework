package org.nageena.TestsFiles.CRUD;

import org.nageena.BaseTest.BasicTesting;
import org.nageena.POJOs.ResponseBooking;
import org.nageena.endPoints.APIConstants_Common;
import org.testng.annotations.Test;

public class GetBooking_GET extends BasicTesting {

    @Test
    public void test_getbookings(){
        //Getting all bookings
        r.basePath(urls.GET_URL);
        re = r.when().get();
        re.then().log().all();

        //ResponseBooking rp = pm.GettingResponse(re.asString());
       // System.out.println(rp);

        //Getting single booking
        Integer booking_id = re.then().extract().path("bookingid[0]");
        System.out.println(booking_id);
        assertac.verifyResponseBody(booking_id,4334, "Verifying bookingid");


    }



}
