package live.stoicism.productsampleapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.stoicism.productsampleapi.model.Product;
import live.stoicism.productsampleapi.model.ProductDto;
import live.stoicism.productsampleapi.model.ResponseContent;
import live.stoicism.productsampleapi.model.ResponseContentWithMetadata;
import live.stoicism.productsampleapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Controller", description = "API endpoints to manage products")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productForm) {
        Product product = productService.createProduct(productForm);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> modifyProduct(@RequestBody ProductDto productForm,
                                                                  @PathVariable Long productId) {
        productForm.setProductId(productId);
        Product product = productService.modifyProduct(productForm);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Find products by category id or Get all (pageable)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found data with >= 0 records"
            )
    })
    @GetMapping
    public ResponseEntity<ResponseContentWithMetadata<List<Product>>> findAllProductsPageable(
            @ParameterObject @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> products = productService.findAllProducts(pageable);
        ResponseContentWithMetadata.Metadata metadata = new ResponseContentWithMetadata
                .Metadata(pageable.getPageNumber(), pageable.getPageSize(), products.getTotalElements());
        return ResponseEntity.ok()
                .body(new ResponseContentWithMetadata<>(
                        ResponseContent.STATUS_SUCCESS, products.getContent(), "Success", metadata)
                );
    }
}
