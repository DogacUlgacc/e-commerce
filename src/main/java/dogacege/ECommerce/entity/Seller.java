package dogacege.ECommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seller")
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Column(name = "seller_name")
    private String sellerName;

}