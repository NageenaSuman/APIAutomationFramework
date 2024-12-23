package org.nageena.TestsFiles.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;

import org.testng.annotations.Test;

import org.nageena.BaseTest.BasicTesting;
import org.nageena.POJOs.ResponseBooking;



public class CreateBooking_POST extends BasicTesting {


    @Owner("Nageena")
    @Description("Verify that POST request is working fine.")
    @Test(groups = "qa")
    public void testCreateBookingPOST01() {
        r.basePath(urls.POST_URL);

        re = r.when().body(pm.CreateBooking()).post(); // calling the method of payload manager
        // class object which we declared in base test


        re.then().log().all().statusCode(200);

        // Calling the class ResponseBooking as the return value of getting response is object
        ResponseBooking vr1 = pm.GettingResponse(re.asString());
        System.out.println(vr1);

        //ResponseBooking rp = pm.GettingResponse(vr.asString());
        //Calling the class pojo-Booking from Payloadmanager (BaseTest-->Payloadmanager-->POJO))  which has instance of ir
        assertac.verifykeys(vr1.getBooking().getFirstname(), "Hash Bash Tash");

        int id = vr1.getBookingid();

    }



    }




