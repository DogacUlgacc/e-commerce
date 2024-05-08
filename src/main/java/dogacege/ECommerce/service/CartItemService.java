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
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public CartItemService(CartItemRepository cartItemRepository, ShoppingCartService shoppingCartService, ProductService productService) {
        this.cartItemRepository = cartItemRepository;

        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getAllCartItemsById(Long cartItemsId) {
        return cartItemRepository.getReferenceById(cartItemsId);
    }

    public CartItem createCartItem(CartItemDto cartItemDto, Long userId) {
        CartItem cartItem  = new CartItem();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingChartById(userId);

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setProduct(productService.getProductById(cartItemDto.getProductId()));

        return cartItemRepository.save(cartItem);
    }

    public CartItem createItem(CartItem cartItem) {
    return cartItemRepository.save(cartItem);
    }
}
