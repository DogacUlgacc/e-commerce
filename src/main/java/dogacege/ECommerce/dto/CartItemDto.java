package dogacege.ECommerce.dto;

import lombok.Data;

@Data
public class CartItemDto {

    private Long  shoppingCartId;
    private Long productId;
    private int quantity;
}
