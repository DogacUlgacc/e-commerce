package dogacege.ECommerce.controller;

import dogacege.ECommerce.dto.CartItemDto;
import dogacege.ECommerce.entity.CartItem;
import dogacege.ECommerce.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {

    private final CartItemService cartItemService ;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/cartItems")
    public List<CartItem> getAllCartItems(){
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{cartItemsId}")
    public CartItem getCartItemById(@PathVariable Long cartItemsId){
        return cartItemService.getAllCartItemsById(cartItemsId);
    }

    @PostMapping("/add/{userId}")
    public CartItem createCartItem(@RequestBody CartItemDto cartItemDto,@PathVariable Long userId){
        return cartItemService.createCartItem(cartItemDto,userId);
    }
    @PostMapping("/add")
    public CartItem createItem(@RequestBody CartItem cartItem){
        return cartItemService.createItem(cartItem);
    }

    @PutMapping("/updateQuantity/{cartItemId}")
    public CartItem updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity){
        return cartItemService.updateCartItemQuantity(cartItemId, quantity);
    }


    @DeleteMapping("/deleteAll/{shoppingCartId}")
    public boolean deleteAllCartItemsByShoppingCartId(@PathVariable Long shoppingCartId) {
        cartItemService.deleteCartItemsByShoppingCartId(shoppingCartId);
        return true;
    }
    @DeleteMapping("/delete/{cartItemId}")
    public void deleteCartItemById(@PathVariable Long cartItemId){
        cartItemService.deleteCartItemById(cartItemId);
    }
}
