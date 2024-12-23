package org.nageena.BaseTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.nageena.Modules.PayloadManager;
import org.nageena.POJOs.AuthToken;
import org.nageena.POJOs.TokenResponse;
import org.nageena.endPoints.APIConstants_Common;
import org.nageena.Assertions.AssertActions;
import org.testng.annotations.BeforeTest;


public class BasicTesting {

    // CommonToAll Testcase
    // Base URL, Content Type - json - common
    //Initialising classes
    public PayloadManager pm;
    public AuthToken token;
    public APIConstants_Common urls ;
    public AssertActions assertac;

    public RequestSpecification r; /// declare it as public as it need to be called in other classes
    public Response re;
    public ValidatableResponse vr;

    @BeforeTest
    public void setUp(){
        pm = new PayloadManager();
        assertac = new AssertActions();
        urls = new APIConstants_Common();
        token = new AuthToken();
        // 1. Using RestAssured
        r = RestAssured.given().baseUri(APIConstants_Common.baseURL)
                .contentType(ContentType.JSON).log().all();
        // can check only till base URL as base path will vary for each http methods
        //2. Using RequestBuilder
//        RequestSpecification r = new RequestSpecBuilder().setBaseUri(APIConstants_Common.baseURL)
//                .addHeader("content-type", "application/json")
//                .build().log().all();


    }
    public String getToken() {
        pm = new PayloadManager();
        assertac = new AssertActions();
        urls = new APIConstants_Common();
        token = new AuthToken();
        // 1. Using RestAssured
        r = RestAssured.given().baseUri(urls.baseURL)
                .basePath(urls.AUTH_URL);



        // Set the Token payload
        re = r.contentType(ContentType.JSON).body(pm.set_token()).when().post();
        // String Extraction
        String token = pm.getting_token(re.asString());
        System.out.println(token);
        return token;




    }

}
