package ru.netology.testinfo;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private DataGenerator() {
    }

    static void regUser(Info user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    private static String fakerLogin() {
        return faker.name().firstName().toLowerCase();
    }

    private static String fakerPassword() {
        return faker.internet().password();
    }

    private static String fakerStatus(boolean status) {
        if (status) {
            return "active";
        } else {
            return "blocked";
        }
    }

    public Info activeUser() {
        Info user = new Info(fakerLogin(), fakerPassword(), fakerStatus(true));
        regUser(user);
        return user;
    }
}
