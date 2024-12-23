package org.nageena.TestsFiles.IntegrationTest.Scn1_Create_Delete_Verify;

import org.nageena.POJOs.ResponseBooking;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import org.testng.annotations.Test;

import org.nageena.BaseTest.BasicTesting;
import org.nageena.POJOs.ResponseBooking;

public class Exe20_12_2024 extends BasicTesting {

    @Test(groups = "qa")
    public void test_Create_Delete_Verify(ITestContext iTestContext) { // Using Listners
        // Creating Booking
        r.basePath(urls.POST_URL);
        re = r.when().body(pm.CreateBooking()).post();
        re.then().log().all().statusCode(200);

        ResponseBooking vr1 = pm.GettingResponse(re.asString());
        System.out.println(vr1);

        assertac.verifykeys(vr1.getBooking().getFirstname(), "Hash Bash Tash");

        int id = vr1.getBookingid();
        //passing the booking id to another test by listners
        iTestContext.setAttribute("booking_id", id);

        //Delete the booking
        r.basePath(urls.POST_URL + "/" + id);
        re = r.given().cookie("token", getToken()).delete();
        vr = re.then().log().all().statusCode(201);

        //verifying it has been deleted
        r.basePath(urls.POST_URL + "/" + id).get().then().log().all().statusCode(404);

    }
}