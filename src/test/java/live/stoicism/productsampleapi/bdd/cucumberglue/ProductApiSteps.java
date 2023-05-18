package live.stoicism.productsampleapi.bdd.cucumberglue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class ProductApiSteps {

    @Given("user wants to query the details of the product with id {int}")
    public void the_user_query_product_with_id(int productId) {
        String getStudentUrl = String.format("/api/products/%s", productId);
        System.out.println(getStudentUrl);
    }

    @Then("the result {string}")
    public void result(String expectedResult) {
//        Response response = testContext().getResponse();

//        switch (expectedResult) {
//            case "FAILS":
//                assertThat(response.statusCode()).isBetween(400, 504);
//                break;
//            default:
//                fail("Unexpected error");
//        }
    }

}
