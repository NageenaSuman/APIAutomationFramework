package org.nageena.Assertions;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {
    // Using TestNG Assertions
    public void verifystatuscode(Response response, int expected){
            assertEquals(response.getStatusCode(), expected);
    }
    public void verifyResponseBody(String act, String expected, String descr ){
            assertEquals(act,expected);
    }
    public void verifyResponseBody(int act, int expected, String descr){
        assertEquals(act,expected);

    }
    // Using AssertJ 
    public void verifykeys(String act, String expected){
        assertThat(expected).isNotNull().isNotEmpty().isEqualTo(act);
    }
}
