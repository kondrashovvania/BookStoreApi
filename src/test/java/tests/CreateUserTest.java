package tests;

import dto.ValidUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class CreateUserTest extends BaseTest{
    String endpoint = "/User";
    @Test
    public void successfulCreateUser() {
   
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .userName(fakeLogin)
                .password("Qq12345!")
                .build();


        Response response = postRequest(endpoint, 201, requestBody);

    }
}
