package dogacege.ECommerce.service;


import dogacege.ECommerce.dto.CartItemDto;
import dogacege.ECommerce.entity.CartItem;
import dogacege.ECommerce.entity.ShoppingCart;
import dogacege.ECommerce.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getAllCartItemsById(Long cartItemsId) {
        return cartItemRepository.getReferenceById(cartItemsId);
    }

    public CartItem createCartItem(CartItemDto cartItemDto) {
        CartItem cartItem  = new CartItem();
        ShoppingCart shoppingCart = new ShoppingCart();
        cartItem.setQuantity(cartItem.getQuantity());
   /*     cartItem.setShoppingCart();
        cartItem.setProduct();*/
    }
}
