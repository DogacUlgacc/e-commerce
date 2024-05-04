package dogacege.ECommerce.dto;

import lombok.Data;

@Data
public class ShoppingCartDto {

        private Long userId;
        private Long productId;
        private int quantity;

}
