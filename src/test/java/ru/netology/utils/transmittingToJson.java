package ru.netology.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import ru.netology.data.RegistrationUserInfo;

import static io.restassured.RestAssured.given;

@UtilityClass
public class transmittingToJson {

    @UtilityClass
    public static class Registrator {

        private RequestSpecification requestSpec;

        public static void registrateUser(RegistrationUserInfo user) {
            given().spec(requestSpec)
                    .body(user)
                    .when()
                    .post("/api/system/users")
                    .then()
                    .statusCode(200);
        }

        public static void setUpAll() {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri("http://localhost")
                    .setPort(9999)
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();
        }
    }
}

