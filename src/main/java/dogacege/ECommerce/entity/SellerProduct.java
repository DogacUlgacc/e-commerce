package dogacege.ECommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seller_product")
@Data
public class SellerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerProductId;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}