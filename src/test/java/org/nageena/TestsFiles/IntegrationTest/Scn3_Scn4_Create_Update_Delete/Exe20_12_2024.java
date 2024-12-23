package org.nageena.TestsFiles.IntegrationTest.Scn3_Scn4_Create_Update_Delete;

import io.qameta.allure.Description;
import org.nageena.BaseTest.BasicTesting;
import org.nageena.POJOs.ResponseBooking;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Exe20_12_2024 extends BasicTesting {

    @Test(groups = "qa")
    public void test_Create_Update_Delete(ITestContext iTestContext) { // Using Listners
        // Creating Booking
        r.basePath(urls.POST_URL);
        re = r.when().body(pm.CreateBooking()).post();
        re.then().log().all().statusCode(200);

        ResponseBooking vr1 = pm.GettingResponse(re.asString());
        System.out.println(vr1);

        assertac.verifykeys(vr1.getBooking().getFirstname(), "Hash Bash Tash");

        int id = vr1.getBookingid();
        //passing the booking id to another test by listners
        iTestContext.setAttribute("bookingid", id);

        //Updating the booking
        String Token = getToken();
        //passing the booking id to another test by listners
        iTestContext.setAttribute("token", Token);

        r.basePath(urls.POST_URL + "/" + id);
        re = r.given().cookie("token", Token ).body(pm.update_booking()).put();
        re.then().log().all();
        ResponseBooking vr2 = pm.GettingResponse(re.asString());
        assertac.verifystatuscode(re,200);
        //assertac.verifykeys(vr2.getBooking().getFirstname(),"Nageena"); //need to troubleshoot

        //Delete the booking
        r.basePath(urls.POST_URL + "/" + id);
        re = r.given().cookie("token", getToken()).delete();
        vr = re.then().log().all().statusCode(201);

        //verifying it has been deleted
        r.basePath(urls.POST_URL + "/" + id).get().then().log().all().statusCode(404);

    }
    @Description ("Scenario-4")
    @Test(groups = "qa")
    public void test_Delete_Update(ITestContext iTestContext) { // Using Listners

        //Delete the booking
        Integer Booking_id = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println("Booking id from listeners = "+ Booking_id);
        String token = (String) iTestContext.getAttribute("token");
        System.out.println("Token from listeners " + token);
        //Verifying token and id is not null
        if (Booking_id == null && token == null) {
            System.out.println("Cant Proceeed");
            Assert.fail("Due to null values cant proceed with deletion");
        }
        //This would fail as the following id has been already deleted in previous step
        re = r.given().cookie("token", token).delete();
        vr = re.then().log().all().statusCode(201);

        //verifying it has been deleted
        r.basePath(urls.POST_URL + "/" + Booking_id).get().then().log().all().statusCode(404);

    }
}