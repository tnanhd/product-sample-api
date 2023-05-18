package live.stoicism.productsampleapi.service;

import live.stoicism.productsampleapi.model.Product;
import live.stoicism.productsampleapi.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product createProduct(ProductDto product);

    Product modifyProduct(ProductDto product);

    Product findById(Long productId);

    void deleteProduct(Long productId);

    Page<Product> findAllProducts(Pageable pageable);
}
