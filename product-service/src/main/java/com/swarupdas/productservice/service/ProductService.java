package com.swarupdas.productservice.service;

import com.swarupdas.productservice.dto.ProductRequest;
import com.swarupdas.productservice.exception.ProductNotFoundException;
import com.swarupdas.productservice.exception.IdNotFoundException;
import com.swarupdas.productservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getAllProducts() throws ProductNotFoundException;
    public Optional<Product> getProductById(String id) throws IdNotFoundException;
    public void saveProduct(ProductRequest productRequest);
}
