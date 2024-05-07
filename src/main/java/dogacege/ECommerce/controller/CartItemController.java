package dogacege.ECommerce.controller;

import dogacege.ECommerce.dto.CartItemDto;
import dogacege.ECommerce.entity.CartItem;
import dogacege.ECommerce.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItem")
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

    @PostMapping("/add")
    public CartItem createCartItem(@RequestBody CartItemDto cartItemDto){
        return cartItemService.createCartItem(cartItemDto);
    }
}
