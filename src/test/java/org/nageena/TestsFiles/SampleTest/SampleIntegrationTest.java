package org.nageena.TestsFiles.SampleTest;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleIntegrationTest {

    @Test(groups = "qa", priority = 1)
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void createBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 2)
    @Description("TC#INT2 - Step 2. Verify that the Booking ID is Created")
    public void verifyBooking(){
        Assert.assertTrue(true);
    }


    @Test(groups = "qa", priority = 3)
    @Description("TC#INT3 - Step 3. Verify that the Booking can be Patched/put")
    public void updateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 4)
    @Description("TC#INTd - Step 4. Verify that the Booking can be deleted")
    public void deleteBooking(){
        Assert.assertTrue(true);
    }






}
