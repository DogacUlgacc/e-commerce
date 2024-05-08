package dogacege.ECommerce.controller;

import dogacege.ECommerce.dto.ShoppingCartDto;
import dogacege.ECommerce.entity.ShoppingCart;
import dogacege.ECommerce.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    @GetMapping("/all")
    public List<ShoppingCart> getAllShoppingCart(){
        return shoppingCartService.getAllShoppingCart();
    }

    @GetMapping("/{cartId}")
    public ShoppingCart getShoppingCartWithItems(@PathVariable Long cartId) {
        return shoppingCartService.getShoppingCartWithItems(cartId);
    }

    @PostMapping("/add")
    public ShoppingCart addProductToChart(@RequestBody ShoppingCartDto shoppingCartDto){
        return shoppingCartService.addProductToChart(shoppingCartDto);
    }

    @PutMapping("/{cartId}")
    public ShoppingCart updateShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto,@PathVariable Long cartId){
        return shoppingCartService.updateShoppingCart(shoppingCartDto,cartId);
    }

    @DeleteMapping("/{cartId}")
    public void deleteShoppingCart(@PathVariable Long cartId){
        shoppingCartService.deleteShoppingCart(cartId);
    }


}


