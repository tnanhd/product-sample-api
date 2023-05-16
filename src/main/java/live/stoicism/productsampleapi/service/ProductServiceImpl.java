package live.stoicism.productsampleapi.service;

import live.stoicism.productsampleapi.exception.DatabaseInvalidException;
import live.stoicism.productsampleapi.model.Product;
import live.stoicism.productsampleapi.model.ProductDto;
import live.stoicism.productsampleapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto form) {

        Product product = Product.builder()
                .name(form.getName())
                .shortDesc(form.getShortDesc())
                .price(form.getPrice())
                .salePrice(form.getSalePrice())
                .stock(form.getStock())
                .sold(form.getSold())
                .author(form.getAuthor())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product modifyProduct(ProductDto form) {
        Long productId = form.getProductId();
        Product oldProduct = productRepository.findById(productId)
                .orElseThrow(() -> new DatabaseInvalidException("Wrong product id provided: " + productId));
        oldProduct.setName(form.getName());
        oldProduct.setShortDesc(form.getShortDesc());
        oldProduct.setPrice(form.getPrice());
        oldProduct.setSalePrice(form.getSalePrice());
        oldProduct.setStock(form.getStock());
        oldProduct.setSold(form.getSold());
        oldProduct.setAuthor(form.getAuthor());
        return productRepository.save(oldProduct);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DatabaseInvalidException("Wrong product id provided"));
    }

    @Override
    public void deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseInvalidException("Wrong product id provided");
        }
    }

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
