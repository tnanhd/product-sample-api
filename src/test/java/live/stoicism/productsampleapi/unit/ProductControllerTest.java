package live.stoicism.productsampleapi.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import live.stoicism.productsampleapi.controller.ProductController;
import live.stoicism.productsampleapi.model.Product;
import live.stoicism.productsampleapi.model.ProductDto;
import live.stoicism.productsampleapi.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    private static final Long PRODUCT_ID = 100L;
    private static final String PRODUCT_POST_PAYLOAD = "{\"productId\":100,\"name\":\"Shoes\",\"shortDesc\":\"shoes\",\"price\":1,\"salePrice\":0,\"stock\":10,\"sold\":0,\"author\":\"me\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private static Product productPostObject;

    @BeforeAll
    public static void setup() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        productPostObject = objectMapper.readValue(PRODUCT_POST_PAYLOAD, Product.class);
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(productService.createProduct(any(ProductDto.class))).thenReturn(productPostObject);

        var result = mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PRODUCT_POST_PAYLOAD))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        JSONAssert.assertEquals(PRODUCT_POST_PAYLOAD, result.getContentAsString(), false);
    }

    @Test
    public void testGetProduct() throws Exception {
        when(productService.findById(PRODUCT_ID)).thenReturn(productPostObject);

        var result = mockMvc.perform(get("/api/products/{productId}", PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        JSONAssert.assertEquals(PRODUCT_POST_PAYLOAD, result.getContentAsString(), false);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(PRODUCT_ID);

        var result = mockMvc.perform(delete("/api/products/{productId}", PRODUCT_ID))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }

}
