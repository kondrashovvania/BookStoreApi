package tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
    final static String BASE_URI = "https://demoqa.com";
    Faker faker = new Faker();
    String fakeLogin = faker.name().username();
    String authToken;
    String token;


    static RequestSpecification specification = new RequestSpecBuilder()
            .setUrlEncodingEnabled(false)
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();

    public static Response getRequest(String endPoint, Integer responseCode) {
        Response response = RestAssured.given()
                .spec(specification)
                .when()
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }
    public static Response getRequestwithToken(String endPoint, Integer responseCode, String token) {
        Response response = RestAssured.given()
                .spec(specification)
                .header("Authorization", "Bearer " + token)
                .when()
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response getRequestWithParam(String endPoint, Integer responseCode, String paramName, int id) {
        Response response = RestAssured.given()
                .spec(specification)
                .when()
                .pathParam(paramName, id)
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response postRequest(String endPoint, Integer responseCode, Object body) { // с токеном // второй вариант без токена
        Response response = RestAssured.given()
                .spec(specification)
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }
    public Response postRequestWithoutToken(String endPoint, Integer responseCode, Object body) { // с токеном // второй вариант без токена
        Response response = RestAssured.given()
               .spec(specification)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response putRequest(String endPoint, Integer responseCode, Object body) {
        Response response = RestAssured.given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .put(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response deleteRequest(String endPoint, Integer responseCode) {

        Response response = RestAssured.given()
                .spec(specification)
                .header("Authorization", "Bearer " + token)
                .when()
                .log().all()
                .delete(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

}
