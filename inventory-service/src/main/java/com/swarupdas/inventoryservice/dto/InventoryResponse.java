package com.swarupdas.inventoryservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryResponse {
    private String skuCode;
    private Integer quantity;
}
