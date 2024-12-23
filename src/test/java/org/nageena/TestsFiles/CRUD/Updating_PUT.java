package org.nageena.TestsFiles.CRUD;

import org.nageena.BaseTest.BasicTesting;
import org.nageena.POJOs.ResponseBooking;
import org.nageena.endPoints.APIConstants_Common;
import org.testng.annotations.Test;
import org.nageena.TestsFiles.CRUD.GetBooking_GET;


public class Updating_PUT extends BasicTesting {

    // we need token to update
    @Test
    public void test_update(){

             r.basePath(urls.POST_URL + "/" + "158");
             re = r.given().cookie("token", getToken()).body(pm.update_booking()).put();

        vr = re.then().log().all();
                //ResponseBooking rp = pm.GettingResponse(re.asString()); //need to troubleshoot
             assertac.verifystatuscode(re,200);
             //assertac.verifykeys(rp.getBooking().getFirstname(), "Nageena");
             //
        // assertac.verifykeys();
    }
}
