package live.stoicism.productsampleapi.integration;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Product Integration Tests Epic")
@Feature("Product Integration Test Features")
@DisplayName("Suite to test Product APIs")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    private static final String PRODUCT_POST_PAYLOAD = "{\"productId\":100,\"name\":\"Shoes\",\"shortDesc\":\"shoes\",\"price\":1.0,\"salePrice\":0.0,\"stock\":10,\"sold\":0,\"author\":\"me\"}";
    private static final String PRODUCT_PUT_PAYLOAD = "{\"productId\":100,\"name\":\"Shoes\",\"shortDesc\":\"shoes\",\"price\":1.0,\"salePrice\":0.0,\"stock\":10,\"sold\":0,\"author\":\"you\"}";

    @LocalServerPort
    private int port;

    @Test
    @Story("Create Product Story")
    @Description("Create a product with valid inputs will be successful")
    @DisplayName("Testing the Product creation")
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
    @Story("Get Product Story")
    @Description("Get an existed product with id will be successful ")
    @DisplayName("Testing the Product retrieval")
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
    @Story("Modify Product Story")
    @Description("Modify a product with valid inputs will be successful")
    @DisplayName("Testing the Product modification")
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
    @Story("Delete Product Story")
    @Description("Delete an existed product with id will be successful")
    @DisplayName("Testing the Product deletion")
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
    @Story("Get Non-existed Product Story")
    @Description("Get a product with invalid id will be fail")
    @DisplayName("Testing the Product retrieval with wrong id")
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
