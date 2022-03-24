package com.store.back.services.impl;

import com.similar.products.model.ProductDetail;
import com.store.back.services.ProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:3001";
    
    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public ArrayList<String> getSimilarProducts(String productId) {
        String url = BASE_URL + "/product/{productId}/similarids";
        
        return restTemplate.getForObject(url,ArrayList.class,productId);
    }

    @Override
    public ProductDetail getProduct(String productId) {
        return null;
    }
}
