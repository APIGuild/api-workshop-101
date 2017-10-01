package com.tw.api.integration;

import com.tw.api.contract.ArticleRequest;
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
public class ArticleControllerIntegrationTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Value(value = "${api.version}")
    private String apiVersion;

    @Test
    public void updateArticlePageView() throws IOException {
        String authorId = createAuthor();

        ArticleRequest articleRequest = ArticleRequest.builder()
                .title("title")
                .content("content")
                .tag("tag")
                .build();
        String articleId = given()
                .port(port)
                .when()
                .contentType(ContentType.JSON)
                .body(articleRequest)
                .post(String.format("/api/%s/%s/article", apiVersion, authorId))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .and().body("title", is("title"))
                .and().body("tag", is("tag"))
                .and().body("content", is("content"))
                .and().body("pageView", is(0))
                .and().body("author.id", is(authorId))
                .extract()
                .path("id");

        given()
                .port(port)
                .when()
                .contentType(ContentType.JSON)
                .put(String.format("/api/%s/article/%s/pageview", apiVersion, articleId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .and().body("id", is(articleId))
                .and().body("title", is("title"))
                .and().body("tag", is("tag"))
                .and().body("content", is("content"))
                .and().body("pageView", is(1));
    }

    private String createAuthor() {
        AuthorRequest authorRequest = AuthorRequest.builder()
                .name("Yang")
                .age(30)
                .build();
        return given()
                .port(port)
                .when()
                .contentType(ContentType.JSON)
                .body(authorRequest)
                .post(String.format("/api/%s/author", apiVersion))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .path("id");
    }
}
