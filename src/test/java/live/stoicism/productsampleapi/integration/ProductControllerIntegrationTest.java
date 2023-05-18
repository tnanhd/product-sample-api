package live.stoicism.productsampleapi.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    private static final String PRODUCT_POST_PAYLOAD = "{\"productId\":100,\"name\":\"Shoes\",\"shortDesc\":\"shoes\",\"price\":1.0,\"salePrice\":0.0,\"stock\":10,\"sold\":0,\"author\":\"me\"}";
    private static final String PRODUCT_PUT_PAYLOAD = "{\"productId\":100,\"name\":\"Shoes\",\"shortDesc\":\"shoes\",\"price\":1.0,\"salePrice\":0.0,\"stock\":10,\"sold\":0,\"author\":\"you\"}";

    @LocalServerPort
    private int port;

    @Test
    public void testCreateProduct() {
        var requestSpecification = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(PRODUCT_POST_PAYLOAD);

        var response = requestSpecification.post(createURLWithPort("/api/products"));
        assertEquals(200, response.getStatusCode());
        assertEquals(PRODUCT_POST_PAYLOAD, response.asString());
    }

    @Test
    public void testGetProduct() {
        var requestSpecification = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        var response = requestSpecification.get(createURLWithPort("/api/products/100"));
        assertEquals(200, response.getStatusCode());
        assertEquals(PRODUCT_POST_PAYLOAD, response.asString());
    }

    @Test
    public void testModifyProduct() {
        var requestSpecification = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(PRODUCT_PUT_PAYLOAD);

        var response = requestSpecification.put(createURLWithPort("/api/products/100"));
        assertEquals(200, response.getStatusCode());
        assertEquals(PRODUCT_PUT_PAYLOAD, response.asString());
    }


    @Test
    public void testDeleteProduct() {
        var requestSpecification = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept","application/json");

        var response = requestSpecification.delete(createURLWithPort("/api/products/100"));
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testGetProductNoData() {
        var requestSpecification = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        var response = requestSpecification.get(createURLWithPort("/api/products/100"));
        assertEquals(400, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
