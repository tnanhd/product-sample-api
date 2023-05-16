package live.stoicism.productsampleapi.model;

import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private String name;
    private String shortDesc;
    private Double price;
    private Double salePrice;
    private Long stock;
    private Long sold;
    private String author;
}
