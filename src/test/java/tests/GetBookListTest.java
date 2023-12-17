package tests;

import dto.ValidUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GetBookListTest extends BaseTest{
    String endpoint = "/BookStore/v1/Books";

    @Test
    public void getBookList() {


        Response response = getRequest("/BookStore/v1/Books", 200);

    }

}
