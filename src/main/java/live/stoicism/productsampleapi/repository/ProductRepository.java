package live.stoicism.productsampleapi.repository;

import live.stoicism.productsampleapi.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
