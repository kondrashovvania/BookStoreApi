package tests;

import dto.ValidUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserAuthorizedTest extends BaseTest {
    String generatedUserName;
    String userId;
    String authToken;
    String endpoint = "Account/v1/User";


    @Test
    public void authorizationtrue() {
        assertTrue(a_successfulCreateUser()); // убрать assert True
        assertTrue(b_successfulGenerateToken());
        assertTrue(c_successfulAuthorized());
        assertTrue(d_getUserInformation());
    }

    @Test
    public void authorizationfalse() {
        assertTrue(a_successfulCreateUser());
        assertTrue(b_successfulGenerateToken());
        assertFalse(c_Unsuccessfulauthorized()); // incorrect password
    }

    @Test
    public void authorizationfalse2() {
        assertTrue(a_successfulCreateUser());
        assertTrue(b_UnsuccessfulGenerateToken()); // whit incorrect data when token generated
        assertTrue(c_successfulAuthorized());
    }

    @Test
    public void deleteUser() {
        assertTrue(a_successfulCreateUser());
        assertTrue(b_successfulGenerateToken());
        assertTrue(c_successfulAuthorized());
        assertTrue(d_getUserInformation());
        assertTrue(e_deleteuser());
    }

    @Test
    public void addListOfBooks() {
        assertTrue(a_successfulCreateUser());
        assertTrue(b_successfulGenerateToken());
        assertTrue(c_successfulAuthorized());
        assertTrue(f_getbookList());
        assertTrue(g_addListOfBooks());
    }


    public boolean a_successfulCreateUser() {


        ValidUserRequest requestBody = ValidUserRequest.builder()
                .userName(fakeLogin)
                .password("Qq12345!")
                .build();
        Response response = postRequest(endpoint, 201, requestBody);
        generatedUserName = fakeLogin;
        userId = response.jsonPath().getString("userID");
        return response.getStatusCode() == 201;
    }

    String endpoint2 = "Account/v1/GenerateToken";

    public boolean b_successfulGenerateToken() {

        ValidUserRequest requestBody = ValidUserRequest.builder()
                .userName(generatedUserName)
                .password("Qq12345!")
                .token(authToken)
                .build();
        Response response = postRequestWithoutToken(endpoint2, 200, requestBody);
        authToken = response.jsonPath().getString("token");
        token = authToken;
        return response.getStatusCode() == 200;
    }

    public boolean b_UnsuccessfulGenerateToken() {

        ValidUserRequest requestBody = ValidUserRequest.builder()
                .userName(generatedUserName)
                .password("Qq12345!Qq12345!") // incorrect password
                .build();
        Response response = postRequest(endpoint2, 200, requestBody);
        authToken = response.jsonPath().getString("token");
        return response.getStatusCode() == 200;
    }

    String endpoint3 = "Account/v1/Authorized";

    public boolean c_successfulAuthorized() {

        ValidUserRequest requestBody = ValidUserRequest.builder() // создание пользователя, генерация токена
                .userName(generatedUserName)
                .password("Qq12345!")
                .build();
        Response response = postRequest(endpoint3, 200, requestBody);
        return response.getStatusCode() == 200 && response.getBody().asString().equals("true");

    }

    public boolean d_getUserInformation() {
        String getUserEndpoint = "/Account/v1/User/" + userId;
        Response response = getRequestwithToken(getUserEndpoint, 200, authToken);
        return response.getStatusCode() == 200;
    }

    public boolean c_Unsuccessfulauthorized() {
        String endpoint3 = "/Authorized";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .userName(generatedUserName)
                .password("Qq12345!Qq12345!")  // incorrect password
                .build();
        Response response = postRequest(endpoint3, 404, requestBody);
        return response.getStatusCode() == 404 && response.getBody().asString().equals("false");
    }

    public boolean e_deleteuser() {
        String deleteUserEndpoint = "Account/v1/User/" + userId;
        Response response = deleteRequest(deleteUserEndpoint, 200); // срабатывает с 401.
        return response.getStatusCode() == 200;
    }
    public boolean f_getbookList(){
        Response response = getRequest("/BookStore/v1/Books", 200);
        return response.getStatusCode() == 200;
    }

    public boolean g_addListOfBooks() {
        String addListOfBooksEndPoint = "BookStore/v1/Books";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .userName(generatedUserName)
                .password("Qq12345!")
                .token(authToken)
                .build();
        Response response = postRequest(addListOfBooksEndPoint, 201, requestBody);
        return response.getStatusCode() == 201;
    }
}





