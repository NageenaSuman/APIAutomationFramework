package org.nageena.TestsFiles.IntegrationTest.Scn3_Scn4_Create_Update_Delete;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
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
        System.out.println("This would fail as the following id has been" +
                        " already deleted in previous step");
        r.basePath(urls.POST_URL + "/" + Booking_id);
        re = r.given().cookie("token", token).delete();


        // so get any booking id
        System.out.println("Getting all bookings");
        r.basePath(urls.GET_URL);
        re = r.when().get();

        Integer booking_id = re.then().extract().path("bookingid[0]");
        System.out.println(booking_id);
        r.basePath(urls.GET_URL + "/" + booking_id);
        r.basePath(urls.GET_URL + "/" + booking_id).given().cookie("token", token).delete().then().log().all();
//
        //verifying it has been deleted
        r.basePath(urls.POST_URL + "/" + Booking_id).get().then().log().all().statusCode(404);

        //trying to update the deleted id which would fail as "Method Not allowed" due to
        // non existance of booking id
        r.basePath(urls.POST_URL + "/" + booking_id);
        r.given().cookie("token", token).body(pm.update_booking()).put().then().log().all();

    }
}