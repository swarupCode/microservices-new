package com.swarupdas.inventoryservice.service;

import com.swarupdas.inventoryservice.dto.InventoryRequest;
import com.swarupdas.inventoryservice.model.Inventory;
import com.swarupdas.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void addInventory(InventoryRequest inventoryRequest)
    {
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
        inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventories()
    {
        return inventoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode)
    {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }



}
