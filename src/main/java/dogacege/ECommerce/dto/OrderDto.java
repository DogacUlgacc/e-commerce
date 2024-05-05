package dogacege.ECommerce.dto;


import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Long userId;
    private Date orderDate;
    private double totalAmount;
    private String status;


}
