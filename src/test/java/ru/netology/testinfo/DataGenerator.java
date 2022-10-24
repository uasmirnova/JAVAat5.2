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

    public static String fakerLogin() {
        Faker faker = new Faker();
        return faker.name().firstName().toLowerCase();
    }

    public static String fakerPassword() {
        Faker faker = new Faker();
        return faker.internet().password();
    }

    public static String fakerStatus(boolean status) {
        if (status) {
            return "active";
        } else {
            return "blocked";
        }
    }

    public static Info activeUser() {
        Info user = new Info(fakerLogin(), fakerPassword(), fakerStatus(true));
        regUser(user);
        return user;
    }

    public static Info blockedUser() {
        Info user = new Info(fakerLogin(), fakerPassword(), fakerStatus(false));
        regUser(user);
        return user;
    }

    public static Info notRegisteredUser() {
        Info user = new Info(fakerLogin(), fakerPassword(), fakerStatus(true));
        return user;
    }

    public static Info invalidLoginUser() {
        String login = fakerLogin();
        Info user = new Info(login, fakerPassword(), fakerStatus(true));
        regUser(user);
        return new Info(login, fakerPassword(), fakerStatus(true));
    }

    public static Info invalidPasswordUser() {
        String password = fakerPassword();
        Info user = new Info(fakerLogin(), password, fakerStatus(true));
        regUser(user);
        return new Info(fakerLogin(), password, fakerStatus(true));
    }
}
