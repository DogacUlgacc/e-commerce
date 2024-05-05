package dogacege.ECommerce.service;

import dogacege.ECommerce.dto.OrderDto;
import dogacege.ECommerce.entity.Order;
import dogacege.ECommerce.entity.User;
import dogacege.ECommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService) {
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
        order.setOrderDate(orderDto.getOrderDate());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());
        User user = userService.getUserById(orderDto.getUserId());
        order.setUser(user);
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Order newOrder) {
        // Orderın kimliğini al
        Long orderId = newOrder.getOrderId();

        // Veritabanından mevcut Orderın al
        Optional<Order> existinOrderOptional = orderRepository.findById(orderId);
        if (existinOrderOptional.isPresent()) {
            // Mevcut Orderın al
            Order existingOrder = existinOrderOptional.get();

            // Yeni bilgilerle mevcut Orderın güncelle
            existingOrder.setTotalAmount(newOrder.getTotalAmount());
            // Güncellenmiş Orderın veritabanına kaydet
            return orderRepository.save(existingOrder);
        } else {
            // Orderın bulunamadı, null dön
            return null;
        }
    }
}
