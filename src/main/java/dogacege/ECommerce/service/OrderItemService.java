package dogacege.ECommerce.service;


import dogacege.ECommerce.entity.OrderItem;
import dogacege.ECommerce.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem getOrderItemById(Long id) {
     return orderItemRepository.findById(id).orElse(null);
    }

    public List<OrderItem> getAllOrderItem() {
        return orderItemRepository.findAll();
    }


    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id" + id));

        // Güncelleme işlemleri
        existingOrderItem.setProduct(updatedOrderItem.getProduct());
        existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
        existingOrderItem.setPrice(updatedOrderItem.getPrice());


        return orderItemRepository.save(existingOrderItem);
    }

}

