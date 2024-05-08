    package dogacege.ECommerce.entity;

    import com.fasterxml.jackson.annotation.JsonIdentityInfo;
    import com.fasterxml.jackson.annotation.ObjectIdGenerators;
    import jakarta.persistence.*;
    import lombok.Data;

    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cartItemId")
    @Data
    public class CartItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long cartItemId;

        @ManyToOne
        @JoinColumn(name = "shopping_cart_id")
        private ShoppingCart shoppingCart;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @Column(name = "quantity")
        private int quantity;
    }