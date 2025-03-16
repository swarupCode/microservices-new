package com.swarupdas.productservice.controller;

import com.swarupdas.productservice.dto.ProductReponse;
import com.swarupdas.productservice.dto.ProductRequest;
import com.swarupdas.productservice.model.Product;
import com.swarupdas.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", "Adding one product.");
//        return new ResponseEntity<>(productRequest, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductReponse>> getAllProducts()
    {
        List<Product> allProducts = productService.getAllProducts();
        List<ProductReponse> productReponses = allProducts.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("info", "Getting all products");
        httpHeaders.add("desc", "Retrieve products from database");
//        return new ResponseEntity<>(productReponses,httpHeaders, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(productReponses);
        return ResponseEntity.ok(productReponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReponse> getProductById(@PathVariable("id") String productId)
    {
        Optional<Product> productOptional = productService.getProductById(productId);
        Optional<ProductReponse> productReponseOptional = productOptional.map(this::mapToProductResponse);
        return productReponseOptional.map(product -> ResponseEntity.ok().body(product)).orElseGet(()->ResponseEntity.notFound().build());
    }

    private ProductReponse mapToProductResponse(Product product) {
        ProductReponse productReponse = ProductReponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
        return productReponse;
    }
}
