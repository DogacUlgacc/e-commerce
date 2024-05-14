package dogacege.ECommerce.dto;


import dogacege.ECommerce.entity.CartItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long userId;
    private List<CartItem> cartItems;
    private Long totalAmount;
}
