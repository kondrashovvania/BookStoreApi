package tests;

import dto.ValidUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DeleteUserTest extends BaseTest{
    String endpoint = "/User/fbf349ac-ee66-494e-8651-6d3c764e8ed5";
    @Test
    public void successfulDeleteUser() {

        ValidUserRequest requestBody = ValidUserRequest.builder()
                .userName("parker.reilly")
                .password("Qq12345!")
                .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InBhcmtlci5yZWlsbHkiLCJwYXNzd29yZCI6IlFxMTIzNDUhIiwiaWF0IjoxNzAxODU3MTc5fQ.SE-b2NYj8jdNkzSNnfL5SQ0IaV8s4AFLz0tmPXM48A4")
                .build();
        Response response = postRequest(endpoint, 200, requestBody);

    }
}
