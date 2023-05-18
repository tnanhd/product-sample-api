package live.stoicism.productsampleapi.bdd.cucumberglue;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import live.stoicism.productsampleapi.model.Product;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ProductApiStepDefinitions extends AbstractSteps {

    @DataTableType
    public Product productEntryTransformer(Map<String, String> entry) {
        return Product.builder()
                .productId(Long.valueOf(entry.get("productId")))
                .name(entry.get("name"))
                .shortDesc(entry.get("shortDesc"))
                .price(Double.valueOf(entry.get("price")))
                .salePrice(Double.valueOf(entry.get("salePrice")))
                .stock(Long.valueOf(entry.get("stock")))
                .sold(Long.valueOf(entry.get("sold")))
                .author(entry.get("author"))
                .build();
    }

    @Given("a new product with the following attributes")
    public void aNewProductWithTheFollowingAttributes(List<Product> products) {
        testContext().reset();
        super.testContext().setPayload(products.get(0));
    }

    @When("user create new product")
    public void userCreateNewProduct() {
        String createStudentUrl = "/api/products";
        executePost(createStudentUrl);
    }

    @When("user wants to query the details of the product with id {int}")
    public void userQueryProductWithId(int productId) {
        String getStudentUrl = String.format("/api/products/%s", productId);
        executeGet(getStudentUrl);
    }

    @When("user wants to delete the product with id {int}")
    public void userDeleteProductWithId(int productId) {
        String deleteStudentUrl = String.format("/api/products/%s", productId);
        executeDelete(deleteStudentUrl);
    }

    @Then("the result {string}")
    public void result(String expectedResult) {
        Response response = testContext().getResponse();

        switch (expectedResult) {
            case "IS SUCCESSFUL":
                assertThat(response.statusCode()).isIn(200, 201);
                if (!response.asString().isEmpty()) {
                    assertThat(response.as(Product.class).equals(testContext().getPayload()));
                }
                break;
            case "FAILS":
                assertThat(response.statusCode()).isBetween(400, 504);
                break;
            default:
                fail("Unexpected error");
        }
    }

}
