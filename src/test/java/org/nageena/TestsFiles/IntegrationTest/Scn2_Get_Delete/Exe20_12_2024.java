package org.nageena.TestsFiles.IntegrationTest.Scn2_Get_Delete;

import org.nageena.BaseTest.BasicTesting;
import org.nageena.POJOs.ResponseBooking;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Exe20_12_2024 extends BasicTesting {

    @Test(groups = "qa")
    public void test_Get_Delete_Verify(ITestContext iTestContext) { // Using Listners
        //Getting all bookings
        r.basePath(urls.GET_URL);
        re = r.when().get();
        re.then().log().all();


        //Getting single booking
        Integer id = re.then().extract().path("bookingid[0]");
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