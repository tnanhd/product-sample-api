package live.stoicism.productsampleapi.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    private Long productId;

    private String name;
    private String shortDesc;
    private Double price;
    private Double salePrice;
    private Long stock;
    private Long sold;
    private String author;

    // TODO: Add these fields and run integration tests successfully
//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    private LocalDateTime lastModifiedDate;

}
