package com.swarupdas.inventoryservice.controller;

import com.swarupdas.inventoryservice.dto.InventoryRequest;
import com.swarupdas.inventoryservice.dto.InventoryResponse;
import com.swarupdas.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

//    public ResponseEntity<InventoryResponse> addInventory(@RequestBody InventoryRequest inventoryRequest)
//    {
//
//    }
    @GetMapping("/{sku-code}")
    public boolean isInStock(@PathVariable String skuCode)
    {
        return inventoryService.isInStock(skuCode);
    }



}
