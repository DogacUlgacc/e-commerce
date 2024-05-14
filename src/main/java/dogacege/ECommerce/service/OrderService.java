package dogacege.ECommerce.service;

import dogacege.ECommerce.dto.OrderDto;
import dogacege.ECommerce.dto.ShoppingCartDto;
import dogacege.ECommerce.entity.*;
import dogacege.ECommerce.repository.OrderRepository;
import dogacege.ECommerce.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService, ShoppingCartService shoppingCartService, CartItemService cartItemService) {
        this.orderRepository = orderRepository;
        this.userService = userService;

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order addOrder(OrderDto orderDto) {
        Order order = new Order();

        User user = userService.getUserById(orderDto.getUserId());
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : orderDto.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        order.setTotalAmount(orderDto.getTotalAmount());

        return orderRepository.save(order);
    }


    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Order newOrder) {

        Long orderId = newOrder.getOrderId();


        Optional<Order> existinOrderOptional = orderRepository.findById(orderId);
        if (existinOrderOptional.isPresent()) {

            Order existingOrder = existinOrderOptional.get();


            existingOrder.setTotalAmount(newOrder.getTotalAmount());

            return orderRepository.save(existingOrder);
        } else {

            return null;
        }
    }

    public List<Order> getOrderByUserId(Long userId) {

        return orderRepository.findByUserId(userId);

    }
}
