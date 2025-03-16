package com.swarupdas.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItemsRequest> orderLineItemsList;
}
