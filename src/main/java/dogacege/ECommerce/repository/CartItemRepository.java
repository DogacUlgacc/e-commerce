package dogacege.ECommerce.repository;

import dogacege.ECommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.shoppingCart.shoppingCartId = :shoppingCartId")
    List<CartItem> findByShoppingCartId(Long shoppingCartId);
}
