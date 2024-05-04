package dogacege.ECommerce.controller;

import dogacege.ECommerce.dto.OrderDto;
import dogacege.ECommerce.entity.Order;
import dogacege.ECommerce.entity.User;
import dogacege.ECommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/add")
    public Order addOrder(@RequestBody OrderDto orderDto){
        return orderService.addOrder(orderDto);
    }
}
