package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import org.kore.blueprint.publishingcompany.boundary.jaxrs.BookModel;
import org.kore.blueprint.publishingcompany.boundary.jaxrs.AuthorModel;
import org.kore.blueprint.publishingcompany.boundary.jaxrs.BlogPostModel;
import org.kore.blueprint.publishingcompany.entity.author.Currency;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class AuthorsResourceIT {

    @Test
    public void testGetAllEndpoint() {
        given()
                .when().get("/authors")
                .then()
                .statusCode(200)
                .body(containsString("Darth Plagueis was a Dark Lord of the Sith"));
    }

    @Test
    public void testGetDarthSidiousEndpoint() {
        given()
                .when().get("/authors/6e723a3c-bd25-471c-8d1c-6c019b36d357")
                .then()
                .statusCode(200)
                .body(containsString("The Tragedy of Darth Plagueis The Wise"));
    }

    @Test
    public void testNotFoundEndpoint() {
        given()
                .when().get("/authors/ae723a3c-bd25-471c-8d1c-6c019b36d357")
                .then()
                .statusCode(404);
    }

    @Test
    public void testUpdateBookEndpoint() throws Exception {
        BookModel bookTestdata = createBookTestdata("4563865801239");
        given().contentType(ContentType.JSON)
                .body(bookTestdata)
                .when()
                .put("/authors/5f2e352e-a391-46fe-9a60-865cd615d6eb/books/4563865801239")
                .then()
                .assertThat()
                .statusCode(200);

        given().config(RestAssuredConfig.config()
                .jsonConfig(JsonConfig.jsonConfig()
                        .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)))
                .when()
                .get("/authors/5f2e352e-a391-46fe-9a60-865cd615d6eb/books/4563865801239")
                .then()
                .statusCode(200)
                .body("price",
                        equalTo(new BigDecimal("100.00")),
                        "currency",
                        equalTo(Currency.STONE.name()));
    }

    @Test
    public void testCreateTestauthorEndpoint() throws Exception {
        String locationOfNewOne = given().contentType(ContentType.JSON)
                .body(createTestdata())
                .when()
                .post("/authors")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .header("Location", is(not(nullValue())))
                .extract()
                .header("Location");

        given().config(RestAssuredConfig.config()
                .jsonConfig(JsonConfig.jsonConfig()
                        .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)))
                .when()
                .get(URI.create(locationOfNewOne))
                .then()
                .statusCode(200)
                .body(containsString("Hallo Welt!"));

    }

    @Test
    public void testCreateIncorrectTestauthorEndpoint() throws Exception {
        AuthorModel createTestdata = createTestdata();
        createTestdata.firstname = null;

        given().contentType(ContentType.JSON)
                .body(createTestdata)
                .when()
                .post("/authors")
                .then()
                .assertThat()
                .statusCode(400);
    }

    AuthorModel createTestdata() throws MalformedURLException {
        AuthorModel author = new AuthorModel();
        author.firstname = "Max";
        author.lastname = "Mustermann";

        BlogPostModel blog = new BlogPostModel();
        blog.blogId = "1893865801929";
        blog.description = "Hallo Welt!";
        blog.title = "Hallo";
        blog.publishingDate = LocalDateTime.of(2008, Month.MARCH, 24, 19, 0);
        blog.location = new URL("https://de.wikipedia.org/wiki/Mustermann");

        author.blogPosts.add(blog);

        return author;
    }

    BookModel createBookTestdata(String isbn) throws MalformedURLException {
        BookModel book = new BookModel();
        book.isbn = "7893865801929";
        book.description = "Hallo Welt!";
        book.title = "Hallo";
        book.publishingDate = LocalDateTime.now();
        book.price = new BigDecimal("10.00");
        book.currency = Currency.GCRDT.name();
        return book;
    }
}
