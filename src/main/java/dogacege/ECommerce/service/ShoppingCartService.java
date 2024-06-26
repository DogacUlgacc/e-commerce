package dogacege.ECommerce.service;

import dogacege.ECommerce.dto.ShoppingCartDto;
import dogacege.ECommerce.entity.Product;
import dogacege.ECommerce.entity.ShoppingCart;
import dogacege.ECommerce.entity.User;
import dogacege.ECommerce.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UserService userService, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.productService = productService;
    }



    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart getShoppingCartById(Long cartId) {
        return shoppingCartRepository.findById(cartId).orElse(null);
    }
    public ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId);
    }

    public ShoppingCart addProductToChart(ShoppingCartDto shoppingCartDto) {
        ShoppingCart cart = new ShoppingCart();


        User user = userService.getUserById(shoppingCartDto.getUserId());
        Product product = productService.getProductById(shoppingCartDto.getProductId());

        cart.setUser(user);
        return shoppingCartRepository.save(cart);
    }

    public void deleteShoppingCart(Long cartId) {
        shoppingCartRepository.deleteById(cartId);
    }

    public ShoppingCart updateShoppingCart(ShoppingCartDto shoppingCartDto, Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getReferenceById(cartId);

        Long userId = shoppingCartDto.getUserId();
        Long productId = shoppingCartDto.getProductId();


        shoppingCart.setUser(userService.getUserById(userId));

        return shoppingCartRepository.save(shoppingCart);

    }


    public ShoppingCart getShoppingCartWithItems(Long cartId) {
        return shoppingCartRepository.findShoppingCartWithItems(cartId);
    }
}
