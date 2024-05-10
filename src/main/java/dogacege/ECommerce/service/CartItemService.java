package dogacege.ECommerce.service;


import dogacege.ECommerce.dto.CartItemDto;
import dogacege.ECommerce.entity.CartItem;
import dogacege.ECommerce.entity.ShoppingCart;
import dogacege.ECommerce.repository.CartItemRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        CartItem cartItem = new CartItem();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(userId);

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setProduct(productService.getProductById(cartItemDto.getProductId()));
        cartItemRepository.save(cartItem);

        return cartItem;
    }

    public CartItem createItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(quantity);
            return cartItemRepository.save(cartItem);
        } else {
            return null;
        }
    }


    public void deleteCartItemsByShoppingCartId(Long shoppingCartId) {
        List<CartItem> cartItems = cartItemRepository.findByShoppingCartId(shoppingCartId);
        cartItemRepository.deleteAll(cartItems);
    }


    public void deleteCartItemById(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
