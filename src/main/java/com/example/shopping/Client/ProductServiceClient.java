package com.example.shopping.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.example.shopping.Model.Product;
import com.example.shopping.Model.Request.ProductQuantityValidatorRequest;

import reactor.core.publisher.Mono;



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
    
    public List<Product> validateProduct (List<ProductQuantityValidatorRequest> productValidatorRequestBody){
    	return webClient.post()
    			.uri("/product/validate")
    			.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
    			.bodyValue(productValidatorRequestBody.toArray())
    			.retrieve()
    			.bodyToMono(new ParameterizedTypeReference<List<Product>>() {
				})
    			.block();
    	//return Arrays.asList(response.bodyToMono(Product[].class).block());
    	

    }

	public void changeStockLevels(List<ProductQuantityValidatorRequest> productValidatorRequestBody) {
	
		webClient.patch()
		.uri("/product/sold")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(productValidatorRequestBody.toArray())
		.retrieve()
		.bodyToMono(new ParameterizedTypeReference<List<Product>>() {
		})
		.block();
		
	}

}
