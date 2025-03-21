package com.swarupdas.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsRequest {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
