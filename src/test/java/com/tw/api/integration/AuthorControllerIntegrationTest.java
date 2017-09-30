package com.tw.api.integration;

import com.tw.api.contract.AuthorRequest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AuthorControllerIntegrationTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Value(value = "${api.version}")
    private String apiVersion;

    @Test
    public void createAuthor() throws IOException {
        given()
                .port(port)
                .when()
                .contentType(ContentType.JSON)
                .body(AuthorRequest.builder()
                        .name("Yang")
                        .age(30)
                        .build())
                .post(String.format("/api/%s/author", apiVersion))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .and().body("id.length()", is(8))
                .and().body("name", is("Yang"))
                .and().body("age", is(30));
    }
}
