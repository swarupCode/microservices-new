package com.swarupdas.orderservice.service;

import com.swarupdas.orderservice.dto.OrderLineItemsRequest;
import com.swarupdas.orderservice.dto.OrderRequest;
import com.swarupdas.orderservice.model.Order;
import com.swarupdas.orderservice.model.OrderLineItems;
import com.swarupdas.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest)
    {
        log.info("Order is placed in service layer...");
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsList().stream()
                .map(this::mapOrderLineItems).collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapOrderLineItems(OrderLineItemsRequest orderLineItemsRequest) {
        return OrderLineItems.builder()
                .price(orderLineItemsRequest.getPrice())
                .skuCode(orderLineItemsRequest.getSkuCode())
                .quantity(orderLineItemsRequest.getQuantity())
                .build();
    }
}
