package tests;

import dto.ValidUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GenerateTokenTest extends BaseTest {

    String endpoint = "/GenerateToken";
    @Test
    public void successfulGenerateToken() {

        ValidUserRequest requestBody = ValidUserRequest.builder() // передаем сразу
                .userName(fakeLogin)
                .password("Qq12345!")
                .token(token)
                .build();
        Response response = postRequestWithoutToken(endpoint, 200, requestBody);

    }
}
