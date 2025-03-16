package com.swarupdas.orderservice.controller;

import com.swarupdas.orderservice.dto.OrderRequest;
import com.swarupdas.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController{

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest)
    {
        log.info("Order is placed in controller layer...");
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<OrderRequest>(orderRequest, HttpStatus.CREATED);
    }
}
