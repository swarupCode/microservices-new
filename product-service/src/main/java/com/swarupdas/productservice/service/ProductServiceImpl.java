package com.swarupdas.productservice.service;

import com.swarupdas.productservice.dto.ProductRequest;
import com.swarupdas.productservice.exception.IdNotFoundException;
import com.swarupdas.productservice.exception.ProductNotFoundException;
import com.swarupdas.productservice.model.Product;
import com.swarupdas.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public List<Product> getAllProducts()
    {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) throw new ProductNotFoundException("No product found.");
        return productList;
    }

    public Optional<Product> getProductById(String id)
    {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty())
        {
            throw new IdNotFoundException("Product ID did not match any.");
        }

        return productOptional;
    }

    public void saveProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("Product {} is saved.", product.getId());
    }
}
