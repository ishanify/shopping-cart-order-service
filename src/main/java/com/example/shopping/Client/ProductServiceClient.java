package com.example.shopping.Client;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.shopping.Model.Product;



@Component
public class ProductServiceClient {
	
    private final WebClient webClient;
    
    public ProductServiceClient(@Value("${services.product.baseUri}") String productServiceBaseUri) {
		this.webClient = WebClient.builder()
				.baseUrl("http://localhost:8080")
				.build();
	}

    public Product getProduct(Integer productId) {
    	return webClient.get()
    	.uri("/product/" + productId)
    	.retrieve()
    	.bodyToMono(Product.class)
    	.block();

    }

}
