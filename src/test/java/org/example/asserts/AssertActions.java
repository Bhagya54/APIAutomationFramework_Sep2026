package org.example.asserts;

import io.restassured.response.Response;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;

//TestNG and AssertJ
public class AssertActions {

    public void verifyResponseBody(String actual,String expected,String description){
        Assert.assertEquals(actual,expected,description);
    }

    public void verifyResponseBody(int actual,int expected,String description){
        Assert.assertEquals(actual,expected,description);
    }

    public void verifyStatusCode(Response response,int expectedStatusCode){
        Assert.assertEquals(response.getStatusCode(),expectedStatusCode,"Status Code did not match");
    }

    public void verifyKeys(int actualKey,int expectKey){
        assertThat(actualKey).isEqualTo(expectKey).isNotNegative().isNotZero().isPositive();
    }

    public void verifyKeys(String actualKey,String expectKey){
        assertThat(actualKey).isEqualTo(expectKey).isNotNull().isNotEmpty().isNotBlank().isAlphanumeric();
    }

}
